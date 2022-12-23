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
@Table(name="lambai")
@NamedQuery(name="Lambai.findAll", query="SELECT l FROM Lambai l")
public class Lambai implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;


	private String active;

	private Timestamp endat;

	private Timestamp startat;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="username")
	private Account account;

	//bi-directional many-to-one association to Baihoc
	@ManyToOne
	@JoinColumn(name="quizid")
	private Quiz quiz;


	@ManyToOne
	@JoinColumn(name="idphuongan")
	private Phuongan phuongan;
	//bi-directional many-to-one association to Test


	

}