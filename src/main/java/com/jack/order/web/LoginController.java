package com.jack.order.web;

import com.jack.order.po.User;
import com.jack.order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final static String REDIRECT = "redirect:/";

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String loginPage(){
        return "/admin/login";
    }

    @PostMapping("login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes attributes){
        System.out.println(username);
        System.out.println(password);
        System.out.println("*****************--------------****************");
        System.out.println(userService);
        System.out.println("*****************--------------****************");
        User user = userService.checkUser(username,password);
        if(user != null){
            //先把密碼清掉
            user.setPassword(null);
            session.setAttribute("user",user);
            return REDIRECT;
        }
        else{
            attributes.addFlashAttribute("message","帳號密碼錯誤");
            //進到上面的loginPage()方法,最後return "admin/login";
            return "redirect:/admin/login";
        }
    }

    @PostMapping("register")
    public String register(User user,RedirectAttributes attributes){
        attributes.addFlashAttribute("loginMessage","恭喜註冊成功");
        return REDIRECT;
    }

}
