package com.shopping.mall.discountsapi.app.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.shopping.mall.discountsapi.cross.constants.Conf;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCouponDTO {
	
	@NotEmpty(message = "{code.not.empty}")
	private String code;
	@NotNull(message = "{percentage.not.null}")
	private Double percentage;
	private int minQuantityProducts;
	private Double minOrderValue;
	private Double maxDiscount;
	@DateTimeFormat(pattern = Conf.dateFormat)
	private LocalDate expiration;

}