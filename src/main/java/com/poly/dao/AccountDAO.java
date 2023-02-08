package com.poly.dao;

import java.util.List;
import java.util.Optional;

import com.poly.bean.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AccountDAO extends JpaRepository<Account, String> {

    @Query("SELECT DISTINCT ar.account  FROM Authority ar WHERE ar.role.id IN ('DIRE', 'STAF')")
    List<Account> getAdministrators();

    @Query(value = "SELECT DISTINCT *  FROM accounts acc WHERE acc.sub=?1", nativeQuery = true)
    Optional<Account> getAccountBySub(String sub);

    @Query(value = "select DISTINCT * from accounts acc where acc.fullname = ?1", nativeQuery = true)
    Optional<Account> getAccountByName(String name);

    @Query(value = "select acc.username from accounts acc where acc.sub = ?1", nativeQuery = true)
    String parseSubToUsername(String sub);

    //select * from accounts acc, authorities auth where acc.username = auth.username and auth.roleid = 'DIRE' and auth.username = 'user1'
    @Query(value = "select 'Y' from accounts acc, authorities auth where acc.username = auth.username and auth.roleid = 'DIRE' and auth.username = ?1", nativeQuery = true)
    String isDire(String username);
    // @Modifying(clearAutomatically = true)
    // @Transactional
    // @Query(value = "update accounts set diem = ?1 and heart = ?2 where username = ?3", nativeQuery = true)
    // void update(int diem, int heart, String username);
}
