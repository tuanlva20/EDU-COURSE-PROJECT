package com.poly.service;

import java.util.List;
import java.util.Optional;

import com.poly.bean.Role;

public interface RoleService {

    List<Role> getAll();
    Optional<Role> getOneById(String id);
    Role create(Role role);
    Role update(Role role);
    void delete(String id);
}
