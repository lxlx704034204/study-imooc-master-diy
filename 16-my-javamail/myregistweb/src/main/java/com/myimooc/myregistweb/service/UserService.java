package com.myimooc.myregistweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myimooc.myregistweb.dao.UserRepository;
import com.myimooc.myregistweb.domain.User;
import com.myimooc.myregistweb.util.MailUtils;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    /**
     * 用户注册的方法
     * @param user
     */
    public void regist(User user){
        // 将数据存入到数据库
        userRepository.save(user);
        
        // 发送一封激活邮件
        try {
            MailUtils.sendMail(user.getEmail(), user.getCode());
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("邮件发送异常");
        }
    }
    
    /**
     * 用户激活的方法
     * @param user
     */
    public boolean registActive(String code){
        
        User user = userRepository.findByCode(code);
        if( null == user){
            return false;
        }
        
        user.setState(1);
        user.setCode("");
        
        userRepository.save(user);
        
        return true;
    }
    
}
