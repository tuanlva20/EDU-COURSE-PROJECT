package com.poly.service.impl;

import java.util.List;

import com.poly.bean.Account;
import com.poly.bean.Authority;
import com.poly.dao.AccountDAO;
import com.poly.dao.AuthorityDAO;
import com.poly.service.AuthorityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService{
    @Autowired
    AuthorityDAO authorityDAO;

    @Autowired
    AccountDAO accountDAO;

    @Override
    public List<Authority> findAll() {
        return authorityDAO.findAll();
    }

    @Override
    public List<Authority> getAuthoritiesOfAdministrators() {
        List<Account> accounts=accountDAO.getAdministrators();
        return authorityDAO.authoritiesOf(accounts);
    }

    @Override
    public Authority create(Authority authority) {
        return authorityDAO.save(authority);
    }

    @Override
    public void delete(Integer id) {
        authorityDAO.deleteById(id);
    }
    

}
