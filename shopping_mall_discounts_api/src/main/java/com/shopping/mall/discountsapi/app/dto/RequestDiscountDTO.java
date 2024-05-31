package com.shopping.mall.discountsapi.app.dto;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.shopping.mall.discountsapi.app.enums.TypeDiscount;
import com.shopping.mall.discountsapi.cross.constants.Conf;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class RequestDiscountDTO {
	
	@NotNull(message = "{typeDiscount.not.null}")
	@Enumerated(EnumType.STRING)
	private TypeDiscount typeDiscount;
	@NotNull(message = "{idObjectDiscount.not.null}")
	private UUID idObjectDiscount;
	@NotNull(message = "{percentageDiscount.not.null}")
	private Double percentageDiscount;
	@DateTimeFormat(pattern = Conf.dateFormat)
	private LocalDate expiration;

}
