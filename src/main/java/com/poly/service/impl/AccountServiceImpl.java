package com.poly.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.poly.bean.Account;
import com.poly.bean.Test;
import com.poly.dao.AccountDAO;
import com.poly.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

   

    
    
}
