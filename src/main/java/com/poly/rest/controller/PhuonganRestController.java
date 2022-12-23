package com.poly.rest.controller;

import java.util.List;
import java.util.Optional;

import com.poly.bean.Phuongan;
import com.poly.service.PhuonganService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/phuongan")
public class PhuonganRestController {
    @Autowired
    PhuonganService phuonganService;

    @GetMapping()
    public List<Phuongan> getAll(){
        return phuonganService.getAll();
    }

    @GetMapping("{id}")
    public Optional<Phuongan> getOne(@PathVariable("id") Integer id){
        return phuonganService.getOneById(id);
    }

    @PostMapping("")
    public Phuongan create(@RequestBody Phuongan phuongan){
        return phuonganService.create(phuongan);
    }

    @PutMapping("")
    public Phuongan update(@RequestBody Phuongan phuongan){
        return phuonganService.update(phuongan);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id){
        phuonganService.delete(id);
    }

}
