package com.banner.utils.impl;

import cn.hutool.core.util.StrUtil;
import com.banner.mapper.FollowRelationMapper;
import com.banner.utils.MessageMapper;
import com.banner.po.Message;
import com.banner.utils.BaseContext;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.zip.DataFormatException;

import static com.banner.utils.RedisConstants.MESSAGE_FLAG_KEY;

/**
 * @author rjj
 * @date 2023/3/14 - 16:48
 */
@Component
public class MessageMapperImpl implements MessageMapper {
    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public List<Message> findListByFromAndTo(Long toId, Integer page, Integer rows) {

        Criteria criteria = conditions(BaseContext.getUserId(), toId);
        PageRequest pageRequest = PageRequest.of(page - 1, rows, Sort.by(Sort.Direction.ASC, "send_date"));
        //设置查询条件,分页
        Query query = Query.query(criteria).with(pageRequest);

        return mongoTemplate.find(query, Message.class);
    }


    @Override
    public UpdateResult updateMessageStatus(ObjectId id, Integer status) {
        Query query = Query.query(Criteria.where("id").is(id));
        Update update = Update.update("status", status);
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        if (status == 1) {
            update.set("send_date", date);
        } else if (status == 0) {
            update.set("read_date", date);
        }

        return mongoTemplate.updateFirst(query, update, Message.class);
    }

    @Override
    public Message saveMessage(Message message) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        message.setSendDate(date);
        message.setStatus(1);
        return mongoTemplate.save(message);
    }

    @Override
    public boolean deleteAllMessage(Long myId, Long toId) {

        Criteria criteria = conditions(myId, toId);
        Query query = Query.query(criteria.andOperator());
        DeleteResult remove = mongoTemplate.remove(query, Message.class);
        return remove.getDeletedCount() != 0;
    }

    @Override
    public boolean isMessage(Long myId, Long otherId) {
        Criteria conditions = conditions(myId, otherId);
        Query query = new Query(conditions);
        return mongoTemplate.exists(query, Message.class);
    }


    private Criteria conditions(Long fromId, Long toId){
        //条件一:用户A发送给用户B的条件
        Criteria criteriaFrom = new Criteria().andOperator(
                Criteria.where("from").is(fromId),
                Criteria.where("to").is(toId)
        );
        //条件二:用户B发送给用户A的条件
        Criteria criteriaTo = new Criteria().andOperator(
                Criteria.where("from").is(toId),
                Criteria.where("to").is(fromId)
        );

        return new Criteria().orOperator(criteriaFrom, criteriaTo);
    }

}
