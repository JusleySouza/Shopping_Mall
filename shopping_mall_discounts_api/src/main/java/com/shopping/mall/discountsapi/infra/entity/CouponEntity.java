package com.shopping.mall.discountsapi.infra.entity;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(name = "coupon", uniqueConstraints={@UniqueConstraint(columnNames={"code"})})
public class CouponEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false)
	private UUID id;
	@Column(nullable = false, unique = true)
	private String code;
	@Column(nullable = false)
	private Double percentage;
	@Column(nullable = true)
	private int minQuantityProducts;
	@Column(nullable = true)
	private Double minOrderValue;
	@Column(nullable = true)
	private Double maxDiscount;
	@Column(nullable = false)
	@DateTimeFormat(pattern = Conf.dateFormat)
	private LocalDate created;
	@Column(nullable = false)
	@DateTimeFormat(pattern = Conf.dateFormat)
	private LocalDate expiration;
	@Column(nullable = false)
	private Boolean active;

}
