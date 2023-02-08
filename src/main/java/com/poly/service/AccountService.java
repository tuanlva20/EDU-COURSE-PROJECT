package com.poly.service;

import java.util.List;

import com.poly.bean.Account;
import com.poly.bean.Test;

public interface AccountService {

    Account findByUsername(String username);
    List<Account> findByKeyword(String key);

    List<Account> getAdministrators();

    List<Account> getAll();

    Account update(Account Account);
    Account update1(Account Account);
    Account create(Account account);
    void delete(String username);
    
}
