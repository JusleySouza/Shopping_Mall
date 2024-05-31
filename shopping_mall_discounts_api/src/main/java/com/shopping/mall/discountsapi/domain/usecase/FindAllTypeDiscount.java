package com.shopping.mall.discountsapi.domain.usecase;

import java.util.List;

import com.shopping.mall.discountsapi.domain.model.Discount;

public interface FindAllTypeDiscount {
	
	List<Discount> findAll(String typeDiscount);

}
