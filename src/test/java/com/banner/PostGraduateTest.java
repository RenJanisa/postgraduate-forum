package com.banner;

import com.banner.mapper.FollowRelationMapper;
import com.banner.mapper.InstitutionMapper;
import com.banner.po.Institution;
import com.banner.po.User;
import com.banner.po.UserInfo;
import com.banner.service.MessageService;
import com.banner.service.UserInfoService;
import com.banner.utils.BaseContext;
import com.banner.utils.MessageMapper;
import com.banner.po.Message;
import com.banner.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author rjj
 * @date 2023/3/11 - 10:23
 */
@SpringBootTest
@Slf4j
public class PostGraduateTest {

    @Resource
    private UserService userService;
    @Resource
    private UserInfoService userInfoService;

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private FollowRelationMapper followRelationMapper;
    @Resource
    private MessageService messageService;
    @Resource
    private InstitutionMapper institutionMapper;

    @Test
    void dataTest(){
        BaseContext.setUser(1634389816206467074L);
        System.out.println(messageService.queryUserList().toString());
//        Message message = new Message();
//        message.setFrom(111L);
//        message.setTo(112L);
//        message.setMsg("hhhh");
//        messageMapper.saveMessage(message);
//        String a = "dsjnckjdsncknf";
//// 随机生成密钥 [B@4411d970
//        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
//// 构建
//        AES aes = SecureUtil.aes(key);
//// 加密为16进制表示 f2106f5c351f372b7f977022b65ba4df
//        String encryptHex = aes.encryptHex(a);
//// 解密为字符串 test中文
//        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
//        System.out.println(encryptHex);
//        System.out.println(decryptStr);
    }

    @Test
    void addUser(){
        User user = new User();
        user.setEmail("3054897093@qq.com");
        user.setPassword("123456");
        userService.save(user);
    }

    @Test
    void  addUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(18);
        userInfo.setUserId(1636313731342286849L);
        userInfo.setUserName("sxj");
        userInfoService.save(userInfo);
    }

    @Test
    void addInstitution(){
        Institution institution = new Institution();
        institution.setInstitutionName("河南科技学院");
        institution.setInstitutionUrl("https://www.hist.edu.cn/");
        institution.setDependent("河南省教育厅");
        institution.setArea(160);
        institution.setNumber(1001);
        institution.setPeculiarity("农学");
        institution.setDescription("侧重农学");
        institutionMapper.insert(institution);
    }

}
