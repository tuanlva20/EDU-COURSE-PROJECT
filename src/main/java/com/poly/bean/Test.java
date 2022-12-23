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
@Table(name="test")
@NamedQuery(name="Test.findAll", query="SELECT t FROM Test t")
public class Test implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int testid;

	private String codes;

	

	@ManyToOne
	@JoinColumn(name = "username")
	private Account account;

	//bi-directional many-to-one association to Baihoc
	@ManyToOne
	@JoinColumn(name="baihocid")
	private Baihoc baihoc;

	//bi-directional many-to-one association to Product
	

	
}