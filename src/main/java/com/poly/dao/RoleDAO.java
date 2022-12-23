package com.poly.dao;

import com.poly.bean.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, String>{
    
}
