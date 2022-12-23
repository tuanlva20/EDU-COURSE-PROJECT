package com.poly.controller;

import com.poly.service.SecuriryService;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("security")
public class SecurityController {
    @Autowired
    SecuriryService userService;

    @RequestMapping("/login/form")
    public String formLogin(){
        return "authenticate/login";
    }

    @RequestMapping("/login/success")
    public String success(Model model){
        model.addAttribute("message","Đăng nhập thành công");
       
        return "forward:/security/login/form";
    }

    @RequestMapping("/login/error")
    public String error(Model model){
        System.out.println("-----------------------------------");
        model.addAttribute("message","Đăng nhập không thành công");
        return "forward:/security/login/form";
    }

    @RequestMapping("/logoff/success")
    public String logoffSuccess(Model model){
        model.addAttribute("message","Đăng xuất thành công");
        return "forward:/security/login/form";
    }
    @RequestMapping("/oauth2/login/success")
    public String loginOAuth2(OAuth2AuthenticationToken oauth2){
        userService.loginFormOAuth2(oauth2);
        return "forward:/security/login/success";
    }
    @RequestMapping("/access/denied")
    public String accessDenied(Model model){
        model.addAttribute("message","Vui lòng truy cập để sử dụng tính năng này");
        return "forward:/security/login/form";
    }
}
