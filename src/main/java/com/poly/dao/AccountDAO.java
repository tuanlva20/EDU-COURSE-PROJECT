package com.poly.dao;

import java.util.List;

import com.poly.bean.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AccountDAO extends JpaRepository<Account, String> {

    @Query("SELECT DISTINCT ar.account  FROM Authority ar WHERE ar.role.id IN ('DIRE', 'STAF')")
    List<Account> getAdministrators();

    @Query(value = "SELECT * FROM account WHERE username or address or fullname LIKE '%?%'", nativeQuery = true)
    List<Account> search(String key);

    @Query(value = "select count(date(ac.createdate)) as count FROM accounts ac WHERE Month(ac.createdate) = ?1", nativeQuery = true)
    Integer getCountAccountByMonth(int month);

    @Query(value = "select count(date(ac.createdate)) as count FROM accounts ac WHERE Month(ac.createdate) = ?1 and year(ac.createdate)"
    , nativeQuery = true)
    Integer getCountAccountMonthInYear(int month, int year);
    
    @Query("SELECT count(*) as count  FROM Account ac ")
    Integer getCountAllAccount();

    // @Modifying(clearAutomatically = true)
    // @Transactional
    // @Query(value = "update accounts set diem = ?1 and heart = ?2 where username = ?3", nativeQuery = true)
    // void update(int diem, int heart, String username);
}
