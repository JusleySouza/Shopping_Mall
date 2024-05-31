package com.shopping.mall.discountsapi.domain.usecase;

import java.util.UUID;

import com.shopping.mall.discountsapi.domain.model.Discount;

public interface UpdateDiscount {
	
	Discount update(Discount discount, UUID idDiscount);

}
