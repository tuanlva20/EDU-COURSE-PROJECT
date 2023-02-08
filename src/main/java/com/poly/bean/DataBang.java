package com.poly.bean;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataBang {
    String username;
    String tenkhoahoc;
    Date ngayhoanthanh = new Date();
    int sizeQuizByProduct;
    int sizeQuizBySucccess;
}
