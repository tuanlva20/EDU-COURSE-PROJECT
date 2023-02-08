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
        try {
            Account acc=aDAO.findById(username).get();
            return acc;
        } catch (Exception e) {
           return null;
        }
        // acc.setPassword(pe.encode(acc.getPassword()));
        return aDAO.findById(username).get();
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
        account.setHeart(3);
        account.setDiem(30);
        account.setPhoto("https://png.pngtree.com/png-vector/20210128/ourmid/pngtree-flat-default-avatar-png-image_2848906.jpg");
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
        Account newAccount = new Account();
        String email = account.getAttribute("email");
        Optional<Account> existUser = aDAO.findById(email);
        newAccount.setUsername(email);
        newAccount.setFullname(account.getAttribute("name"));
        newAccount.setPhoto(account.getAttribute("picture"));
        newAccount.setSub(account.getAttribute("sub"));
        newAccount.setEmail(email);
        if (!existUser.isPresent()) {
            newAccount.setDiem(30);
            newAccount.setHeart(3);
            newAccount.setRecoveryheart(new Timestamp(System.currentTimeMillis()));
            newAccount.setProvider(PROVIDER_ENUM.GOOGLE.toString());
        }else{
            newAccount.setDiem(existUser.get().getDiem());
            newAccount.setHeart(existUser.get().getHeart());
            newAccount.setRecoveryheart(existUser.get().getRecoveryheart());
            newAccount.setPhoto(account.getAttribute("picture"));
        }
        aDAO.save(newAccount);
    }

    private Account setAccountInfor(Object DataItem){
        Account acc = new Account();
        // Account accCreated = DataItem;
        acc.setUsername(null);
        // acc.setFullname();
        return null;
    }

}
