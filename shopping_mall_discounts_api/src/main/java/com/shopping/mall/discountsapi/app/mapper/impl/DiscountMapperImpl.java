package com.shopping.mall.discountsapi.app.mapper.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.app.dto.RequestDiscountDTO;
import com.shopping.mall.discountsapi.app.dto.ResponseDiscountDTO;
import com.shopping.mall.discountsapi.app.enums.TypeDiscount;
import com.shopping.mall.discountsapi.app.mapper.DiscountMapper;
import com.shopping.mall.discountsapi.domain.model.Discount;

import lombok.Generated;

@Generated
@Component
public class DiscountMapperImpl implements DiscountMapper {

	@Override
	public Discount toModel(RequestDiscountDTO requestDiscountDTO) {
		return Discount.builder()
				.typeDiscount(requestDiscountDTO.getTypeDiscount().toString())
				.idObjectDiscount(requestDiscountDTO.getIdObjectDiscount())
				.percentageDiscount(requestDiscountDTO.getPercentageDiscount())
				.expiration(requestDiscountDTO.getExpiration())
				.created(LocalDate.now())
				.active(Boolean.TRUE)
				.build();
	}

	@Override
	public ResponseDiscountDTO modelToResponseDiscountDTO(Discount discount) {
		return ResponseDiscountDTO.builder()
				.id(discount.getId())
				.typeDiscount(TypeDiscount.valueOf(discount.getTypeDiscount().toString()))
				.idObjectDiscount(discount.getIdObjectDiscount())
				.percentageDiscount(discount.getPercentageDiscount())
				.created(discount.getCreated().toString().split(" ")[0])
				.expiration(discount.getExpiration().toString().split(" ")[0])
				.active(discount.getActive())
				.build();
	}

	@Override
	public List<ResponseDiscountDTO> modelToListResponseDiscountDTO(List<Discount> listDiscount) {
		List<ResponseDiscountDTO> listResponse = new ArrayList<>();
		for(Discount discount : listDiscount) {
			listResponse.add(this.modelToResponseDiscountDTO(discount));
		}
		return listResponse;
	}

}
