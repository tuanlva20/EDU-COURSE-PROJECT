package com.poly.service;

import java.util.List;

import com.poly.bean.Authority;

public interface AuthorityService {

    List<Authority> findAll();

    List<Authority> getAuthoritiesOfAdministrators();

    Authority create(Authority authority);

    void delete(Integer id);
    
}
