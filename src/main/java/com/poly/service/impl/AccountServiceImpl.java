package com.poly.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.poly.EnumItem.PROVIDER_ENUM;
import com.poly.bean.Account;
import com.poly.bean.Test;
import com.poly.dao.AccountDAO;
import com.poly.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountDAO aDAO;

    @Autowired
    BCryptPasswordEncoder pe;

    @Override
    public Account findByUsername(String username) {
        Account acc=aDAO.findById(username).get();
        // acc.setPassword(pe.encode(acc.getPassword()));
        return acc;
    }

    @Override
    public List<Account> getAdministrators() {
        return aDAO.getAdministrators();
    }

    @Override
    public List<Account> getAll() {
        return aDAO.findAll();
    }

    @Override
    public Account update(Account account) {
        account.setRecoveryheart( new Timestamp(123456787));
        return aDAO.save(account);
    }
    @Override
    public Account update1(Account account) {
        return aDAO.save(account);
    }

    @Override
    public Account create(Account account) {
        account.setRecoveryheart( new Timestamp(123456787));
        return aDAO.save(account);
    }

    @Override
    public void delete(String username){
        aDAO.deleteById(username);
    }


    @Override
    public void processOAuthPostLogin(DefaultOidcUser account) {
        // User existUser = repo.getUserByUsername(username);
        // username = "user1";
        String email = account.getAttribute("email");
        Optional<Account> existUser = aDAO.findById(email);
        if (existUser.isEmpty()) {
            Account newAccount = new Account();
            newAccount.setUsername(email);
            newAccount.setFullname(account.getAttribute("name"));
            newAccount.setPhoto(account.getAttribute("picture"));
            newAccount.setProvider(PROVIDER_ENUM.GOOGLE.toString());
            aDAO.save(newAccount);
        }

    }

    private Account setAccountInfor(Object DataItem){
        Account acc = new Account();
        // Account accCreated = DataItem;
        acc.setUsername(null);
        // acc.setFullname();
        return null;
    }

}
