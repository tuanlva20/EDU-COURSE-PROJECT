package com.poly.rest.controller;

import java.util.List;

import com.poly.bean.Role;
import com.poly.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.PutMapping;
    import java.util.Optional;
    import org.springframework.web.bind.annotation.RequestBody;
@RestController
@CrossOrigin("*")
@RequestMapping("/rest/roles")
public class RoleRestControlller {
    @Autowired
    RoleService roleService;

    @GetMapping()
    public List<Role> getAll(){
        return roleService.getAll();
    }
    @GetMapping("{id}")
    public Optional<Role> getOne(@PathVariable("id") String id){
        return roleService.getOneById(id);
    }

    @PostMapping()
    public Role create(@RequestBody Role Role){
        return roleService.create(Role);
    }

    @PutMapping("{id}")
    public Role update(@RequestBody Role role, @PathVariable("id") String id){
        return roleService.update(role);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id){
        roleService.delete(id);
    }
}
