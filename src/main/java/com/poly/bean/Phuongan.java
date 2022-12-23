package com.poly.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuppressWarnings("serial")
@Table(name="phuongan")
@NamedQuery(name="Phuongan.findAll", query="SELECT p FROM Phuongan p")
public class Phuongan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private boolean dungsai;

	private String noidung;

	@JsonIgnoreProperties("phuongans")  
	@ManyToOne
	@JoinColumn(name="idquiz")
	private Quiz quiz;

	@JsonIgnore
	@OneToMany(mappedBy = "phuongan")
	List<Lambai> lambais;

	
	

	

}