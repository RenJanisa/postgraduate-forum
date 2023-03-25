package com.banner.service;

import com.banner.dto.UserDto;
import com.banner.po.Message;
import com.banner.utils.R;

import java.util.List;

/**
 * @author rjj
 * @date 2023/3/14 - 16:59
 */
public interface MessageService {
    List<Message> queryMessageList(Long toId, Integer page, Integer rows);

    R<List<UserDto>> queryUserList();

    R<String> deleteMessageWithSomeone(Long toId);
}
