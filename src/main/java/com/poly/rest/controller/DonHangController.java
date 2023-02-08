package com.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.bean.Donhang;
import com.poly.service.DonHangService;
import com.poly.service.impl.DonhangServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/donhang")
public class DonHangController {

    @Autowired
    DonHangService dh;

    @GetMapping()
    public List<Donhang> getDonhang() {
        return dh.getalldonhang();
    }

    @PutMapping()
    public Donhang update(@RequestBody Donhang donhang) {
        return dh.update(donhang);
    }   

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer idOrder) {
        System.out.println(idOrder);
        dh.delete(idOrder);
    }

}
