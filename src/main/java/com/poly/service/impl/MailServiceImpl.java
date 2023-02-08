package com.poly.service.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.poly.bean.Account;
import com.poly.dao.AccountDAO;
import com.poly.service.MailService;
import com.poly.service.ThymeleafService;

@Service
public class MailServiceImpl implements MailService{
    private static final String CONTENT_TYPE_TEXT_HTML = "text/html;charset=\"utf-8\"";

    @Value("${config.mail.host}")
    private String host;
    @Value("${config.mail.port}")
    private String port;
    @Value("${config.mail.username}")
    private String email;
    @Value("${config.mail.password}")
    private String password;

    @Autowired
    ThymeleafService thymeleafService;

    @Autowired
    HttpServletRequest request;
    @Autowired
    AccountDAO aDao;

    @Override
    public void sendMail() {
        String username = request.getRemoteUser();
        if(!aDao.existsById(username)){
            username=aDao.parseSubToUsername(username);
        }
        Account account = aDao.findById(username).get();
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });
        Message message = new MimeMessage(session);
        try {
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(account.getEmail())});

            message.setFrom(new InternetAddress(email));
            message.setSubject("Mail Kích Hoạt Khóa Học");
            message.setContent(thymeleafService.getContent(account), CONTENT_TYPE_TEXT_HTML);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
