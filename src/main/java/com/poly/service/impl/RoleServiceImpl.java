package com.poly.service.impl;

import java.util.List;
import java.util.Optional;

import com.poly.bean.Role;
import com.poly.dao.RoleDAO;
import com.poly.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleDAO rdao;

    @Override
    public List<Role> getAll() {
        return rdao.findAll();
    }

    @Override
    public Optional<Role> getOneById(String id) {
        // TODO Auto-generated method stub
        return rdao.findById(id);
    }

    @Override
    public Role create(Role role) {
        // TODO Auto-generated method stub
        return rdao.save(role);
    }

    @Override
    public Role update(Role role) {
        // TODO Auto-generated method stub
        return rdao.save(role);
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        rdao.deleteById(id);;
    }

}
