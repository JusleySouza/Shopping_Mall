package com.shopping.mall.discountsapi.infra.entity;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.shopping.mall.discountsapi.cross.constants.Conf;

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
@Table(name = "discount")
public class DiscountEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false)
	private UUID id;
	@Column(nullable = false)
	private String typeDiscount;
	@Column(nullable = false)
	private UUID idObjectDiscount;
	@Column(nullable = false)
	private Double percentageDiscount;
	@Column(nullable = false)
	private Boolean active;
	@Column(nullable = false)
	@DateTimeFormat(pattern = Conf.dateFormat)
	private LocalDate created;
	@Column(nullable = false)
	@DateTimeFormat(pattern = Conf.dateFormat)
	private LocalDate expiration;

}
