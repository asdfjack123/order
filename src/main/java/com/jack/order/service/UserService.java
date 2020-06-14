package com.jack.order.service;

import com.jack.order.autil.MD5Utils;
import com.jack.order.dao.UserRepository;
import com.jack.order.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User checkUser(String username, String password) {
        System.out.println("*****************--------------****************");
        System.out.println(userRepository);
        System.out.println("*****************--------------****************");
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

}
