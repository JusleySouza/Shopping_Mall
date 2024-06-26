package com.shopping.mall.discountsapi.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.shopping.mall.discountsapi.cross.constants.Conf;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(notes = "Code coupon", example = "30OFF", required = true)
	private String code;
	@NotNull(message = "{percentage.not.null}")
	@ApiModelProperty(notes = "Percentage coupon", example = "0.1", required = true)
	private Double percentage;
	@ApiModelProperty(notes = "Minimum quantity products", example = "2", required = true)
	private int minQuantityProducts;
	@ApiModelProperty(notes = "Minimum order value", example = "300.00", required = true)
	private Double minOrderValue;
	@ApiModelProperty(notes = "Maximum discount", example = "80.00", required = true)
	private Double maxDiscount;
	@DateTimeFormat(pattern = Conf.dateFormat)
	@ApiModelProperty(notes = "Expiration date", example = "2024-09-30", required = true)
	private LocalDate expiration;

}
