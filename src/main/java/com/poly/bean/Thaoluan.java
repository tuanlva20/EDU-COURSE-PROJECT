package com.poly.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuppressWarnings("serial")
@Table(name="thaoluan")
@NamedQuery(name="Thaoluan.findAll", query="SELECT t FROM Thaoluan t")
public class Thaoluan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Timestamp datecomment;

	private String noidung;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="username")
	private Account account;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="baihocid")
	private Baihoc baihoc;

	//bi-directional many-to-one association to Quiz


}