package com.poly.rest.controller;

import java.util.List;
import java.util.Optional;

import com.poly.bean.Authority;
import com.poly.service.AuthorityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin("*")
@RequestMapping("/rest/authorities")
public class AuthoriryRestController {
    @Autowired
    AuthorityService authorityService;

    @GetMapping("")
    public List<Authority> findAll(@RequestParam("admin") Optional<Boolean> admin){
        if(admin.orElse(false)){
            return authorityService.getAuthoritiesOfAdministrators();
        }
        return authorityService.findAll();
    }    

    @PostMapping
    public Authority post(@RequestBody Authority authority){
        return authorityService.create(authority);
    }

    @DeleteMapping("{id}")
    public void deleteAuthority(@PathVariable("id") Integer id){
        authorityService.delete(id);
    }
}
