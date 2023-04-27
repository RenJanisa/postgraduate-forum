package com.banner.service.impl;

import com.banner.dto.UserDto;
import com.banner.mapper.FollowRelationMapper;
import com.banner.mapper.UserInfoMapper;
import com.banner.utils.MessageMapper;
import com.banner.po.Message;
import com.banner.service.MessageService;
import com.banner.utils.BaseContext;
import com.banner.utils.PostgraduateForumException;
import com.banner.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author rjj
 * @date 2023/3/14 - 17:01
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private FollowRelationMapper followRelationMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<Message> queryMessageList(Long toId, Integer page, Integer rows) {

        List<Message> list = messageMapper.findListByFromAndTo(toId, page, rows);
        if (list.isEmpty())
            return null;
        for (Message message : list) {
            if (message.getStatus() == 1) {
                //修改消息为已读
                messageMapper.updateMessageStatus(message.getId(), 0);
            }
        }
        return list;
    }

    @Override
    public R<List<UserDto>> queryUserList() {
        Long myId = BaseContext.getUserId();
        //获取好友
        List<Long> otherId = followRelationMapper.getOtherId(myId);

        List<UserDto> userDtos = new ArrayList<>();
        for (Long item : otherId) {
            boolean isHave = messageMapper.isMessage(myId, item);
            if (isHave) {
                UserDto userDto = userInfoMapper.getNameAndAvatarById(item);
                userDto.setId(item);
                userDtos.add(userDto);
            }
        }
        return R.success(userDtos);
    }

    @Override
    public R<String> deleteMessageWithSomeone(Long toId) {

        Long myId = BaseContext.getUserId();
        if (messageMapper.deleteAllMessage(myId, toId))
            return R.success("删除成功!");
        else PostgraduateForumException.error(500, "删除失败!");
        return null;
    }
}
