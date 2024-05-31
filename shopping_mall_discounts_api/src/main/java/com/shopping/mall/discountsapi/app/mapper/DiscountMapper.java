package com.shopping.mall.discountsapi.app.mapper;

import java.util.List;

import com.shopping.mall.discountsapi.app.dto.RequestDiscountDTO;
import com.shopping.mall.discountsapi.app.dto.ResponseDiscountDTO;
import com.shopping.mall.discountsapi.domain.model.Discount;

public interface DiscountMapper {
	
	public Discount toModel(RequestDiscountDTO requestDiscountDTO);
	
	public ResponseDiscountDTO modelToResponseDiscountDTO(Discount discount);
	
	public List<ResponseDiscountDTO> modelToListResponseDiscountDTO(List<Discount> listDiscount);

}
