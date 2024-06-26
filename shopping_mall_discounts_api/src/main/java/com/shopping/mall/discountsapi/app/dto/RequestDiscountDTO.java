package com.shopping.mall.discountsapi.app.dto;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.shopping.mall.discountsapi.app.enums.TypeDiscount;
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
public class RequestDiscountDTO {
	
	@NotNull(message = "{typeDiscount.not.null}")
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(notes = "Type discount", example = "CAT", required = true)
	private TypeDiscount typeDiscount;
	@NotNull(message = "{idObjectDiscount.not.null}")
	@ApiModelProperty(notes = "Id discount", example = "000acde0-4d72-4049-9705-0eb5ce87e2fa", required = true)
	private UUID idObjectDiscount;
	@NotNull(message = "{percentageDiscount.not.null}")
	@ApiModelProperty(notes = "Percentage discount", example = "0.1", required = true)
	private Double percentageDiscount;
	@DateTimeFormat(pattern = Conf.dateFormat)
	@ApiModelProperty(notes = "Expiration date", example = "2024-09-30", required = true)
	private LocalDate expiration;

}
