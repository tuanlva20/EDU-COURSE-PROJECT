package com.poly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.poly.bean.Account;

public interface MailService {
    void sendMail();

    void sendMailRegister(String emailfrom,int code);
}
