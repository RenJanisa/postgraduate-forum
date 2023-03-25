package com.banner.utils;

import com.banner.po.Message;
import com.mongodb.client.result.UpdateResult;
import org.apache.ibatis.annotations.Mapper;
import org.bson.types.ObjectId;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * @author rjj
 * @date 2023/3/14 - 16:29
 */
public interface MessageMapper {
    /**
     * 查询点对点聊天记录
     * @param fromId
     * @param toId
     * @param page
     * @param rows
     * @return
     */
    List<Message> findListByFromAndTo(Long toId, Integer page, Integer rows);


    /**
     * 更改消息状态
     * @param Id
     * @param status
     * @return
     */
    UpdateResult updateMessageStatus(ObjectId Id, Integer status);

    /**
     * 新增消息
     * @param message
     * @return
     */
    Message saveMessage(Message message);




    /**
     * 删除消息
     * @return
     */
    boolean deleteAllMessage(Long myId, Long toId);

    /**
     * 查看与好友是否有聊天记录
     * @return
     */
    boolean isMessage(Long myId, Long otherId);
}
