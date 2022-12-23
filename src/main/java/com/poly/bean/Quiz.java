package com.poly.bean;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuppressWarnings("serial")
@Table(name="quiz")
@NamedQuery(name="Quiz.findAll", query="SELECT q FROM Quiz q")
public class Quiz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idquiz;

	private String noidung;

	@JsonIgnoreProperties("quiz")
	@OneToMany(mappedBy="quiz")
	private List<Phuongan> phuongans;

	//bi-directional many-to-one association to Baihocchitiet
	
	@ManyToOne
	@JsonIgnoreProperties("quizs")  // lấy dữ liệu kèm theo bên file json
	@JoinColumn(name="idbaihoc")
	private Baihoc baihoc;

	@JsonIgnore // loại trừ đối tượng này khi convert data sang Json
	@OneToMany(mappedBy="quiz") // FetchType.EAGER lấy từ các đối tượng liên quan từ database
	private List<Lambai> lambais;
}