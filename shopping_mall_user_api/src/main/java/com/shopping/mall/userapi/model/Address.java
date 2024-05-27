package com.shopping.mall.userapi.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false)
	private UUID id;
	@Column(nullable = false)
	private String street;
	@Column(nullable = false)
	private int number;
	@Column(nullable = false)
	private String cep;
	@Column(nullable = false)
	private String complement;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String state;
	@Column(nullable = false)
	private String neighborhood;
	@Column(nullable = false)
	private Boolean defaultAddress;
	@Column(nullable = false, unique = true)
	private String nickName;
	@Column(nullable = true)
	private String reference;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;		
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "countryId")
	private Country country;

}
