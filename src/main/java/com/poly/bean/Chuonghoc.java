package com.poly.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuppressWarnings("serial")
@Table(name="chuonghoc")
@NamedQuery(name="Chuonghoc.findAll", query="SELECT c FROM Chuonghoc c")
public class Chuonghoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tieude;
    private String chuonghocimg;
    @ManyToOne
	@JoinColumn(name="productid")
	private Product product;

    @JsonIgnore
    @OneToMany(mappedBy = "chuonghoc",fetch= FetchType.EAGER)
    private List<Baihoc> baihocs;

}
