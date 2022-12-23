package com.poly.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.bean.Thaoluan;
import com.poly.service.ThaoluanService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/thaoluan")
public class ThaoluanRestController {
    @Autowired
    ThaoluanService thaoluanService;

    @GetMapping(value="/baihoc/{idbaihoc}")
    public List<Thaoluan> getMethodName(@PathVariable("idbaihoc") int idbaihoc) {
        return thaoluanService.getByBaihoc(idbaihoc);
    }
    @PostMapping(value="/{username}/{baihocid}")
    public Thaoluan postMethodName(@RequestBody Thaoluan thaoluan,
    @PathVariable("username") String username,
    @PathVariable("baihocid") int baihocid) {        
        return thaoluanService.save(thaoluan);
    }



    @GetMapping()
    public List<Thaoluan> getThaoluan() {
        return thaoluanService.getAll();
    }

    @GetMapping("{id}")
    public Optional<Thaoluan> getOneByID(@PathVariable("id") Integer id) {
        return thaoluanService.getOneById(id);
    }

    @PostMapping("")
    public Thaoluan create(@RequestBody Thaoluan thaoluan) {
        return thaoluanService.create(thaoluan);
    } 

    @PutMapping("")
    public Thaoluan update(@RequestBody Thaoluan thaoluan) {
        return thaoluanService.update(thaoluan);
    }   

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        thaoluanService.delete(id);
    }
    
}
