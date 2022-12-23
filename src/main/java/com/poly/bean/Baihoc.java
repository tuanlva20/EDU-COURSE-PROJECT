package com.poly.bean;
import java.io.Serializable;
import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

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
@Table(name="baihoc")
@NamedQuery(name="Baihoc.findAll", query="SELECT b FROM Baihoc b")
public class Baihoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int baihocid;

	@Temporal(TemporalType.DATE)
    @Column(name = "createdate")
    Date createdate=new Date();

	@JsonProperty
	private String link;

	private String noidung;

	private String tenbh;

	private String cauhoi;



	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="chuonghocid")
	private Chuonghoc chuonghoc;

	@OneToMany(mappedBy="baihoc")
	@JsonIgnoreProperties("baihoc")
	private List<Quiz> quizs;

	

	@JsonIgnore
	@OneToMany(mappedBy="baihoc")
	private List<Test> tests;

	
	@JsonIgnore
	@OneToMany(mappedBy="baihoc")
	private List<Thaoluan> thaoluans;

	

}