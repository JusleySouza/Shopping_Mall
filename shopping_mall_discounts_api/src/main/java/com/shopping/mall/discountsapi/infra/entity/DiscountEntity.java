package com.shopping.mall.discountsapi.infra.entity;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.shopping.mall.discountsapi.cross.constants.Conf;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
