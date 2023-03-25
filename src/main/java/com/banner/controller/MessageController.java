package com.banner.controller;


import com.banner.dto.UserDto;
import com.banner.po.Message;
import com.banner.service.MessageService;
import com.banner.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author rjj
 * @date 2022/10/15 - 8:35
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    /**
     * 查看具体消息列表
     */

    @GetMapping
    public R<List<Message>> queryMessageList(Long toId, Integer page, Integer rows) {

        return R.success(messageService.queryMessageList(toId, page, rows));
    }

    /**
     * 查询私信列表
     */
    @GetMapping("/user")
    private R<List<UserDto>> queryUserList(){
        return messageService.queryUserList();
    }

    /**
     * 删除聊天记录
     * @param toId
     * @return
     */
    @DeleteMapping
    public R<String> deleteMessage(@RequestParam Long toId){
        return messageService.deleteMessageWithSomeone(toId);
    }


}
