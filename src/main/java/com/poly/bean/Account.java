package com.poly.bean;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name="accounts")
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	private String address;

	@Temporal(TemporalType.DATE)
    @Column(name = "createdate")
    Date createdate=new Date();

	private String email;

	private String fullname;

	private String password;

	private String phone;

	private String photo;

	private int diem;

	private int heart;

	private String provider;

	private String sub;

	private Timestamp recoveryheart;
	@JsonIgnore
	@OneToMany(mappedBy="account",fetch= FetchType.EAGER)
	private List<Authority> authorities;



	@JsonIgnore
	@OneToMany(mappedBy="account")
	private List<Lambai> lambais;

	@JsonIgnore
	@OneToMany(mappedBy="account")
	private List<Order> orders;



	@JsonIgnore
	@OneToMany(mappedBy="account")
	private List<Thaoluan> thaoluans;


	@JsonIgnore
	@OneToMany(mappedBy="account")
	private List<Test> tests;



	
	

}