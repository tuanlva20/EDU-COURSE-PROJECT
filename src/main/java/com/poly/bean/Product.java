package com.poly.bean;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuppressWarnings("serial")
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private boolean available;

	private String description;

	private String image;

	private String name;

	private double price;

	@JsonIgnore
	@OneToMany(mappedBy="product")
	private List<Chuonghoc> chuonghocs;

	@JsonIgnore
	@OneToMany(mappedBy="product")
	private List<Order> oders;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="categoryid")
	private Category category;

	//bi-directional many-to-one association to Discount
	@ManyToOne
	@JoinColumn(name="discountid")
	private Discount discount;

	
}