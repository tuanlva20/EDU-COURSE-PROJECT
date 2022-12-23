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

    // @Modifying(clearAutomatically = true)
    // @Transactional
    // @Query(value = "update accounts set diem = ?1 and heart = ?2 where username = ?3", nativeQuery = true)
    // void update(int diem, int heart, String username);
}
