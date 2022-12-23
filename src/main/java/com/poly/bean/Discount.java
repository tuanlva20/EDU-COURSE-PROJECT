package com.poly.bean;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuppressWarnings("serial")
@Table(name="discounts")
@NamedQuery(name="Discount.findAll", query="SELECT d FROM Discount d")
public class Discount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
    @Column(name = "createdate")
    Date createdate=new Date();

	@Temporal(TemporalType.DATE)
    @Column(name = "enddate")
    Date enddate=new Date();

	private boolean isactive;

	private String name;

	private int saleoff;

	@Temporal(TemporalType.DATE)
    @Column(name = "startdate")
    Date startdate=new Date();

	@JsonIgnore
	@OneToMany(mappedBy="discount")
	private List<Product> products;

	

}