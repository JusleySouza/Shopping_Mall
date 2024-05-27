package com.shopping.mall.productsapi.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false)
	private UUID id;
	@Column(nullable = false)
	private String nameCategory;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCategory")
	private List<SubCategory> listSubCategory;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCategory")
	private List<Product> listProduct;
	
}
