package com.shopping.mall.discountsapi.domain.usecase;

import com.shopping.mall.discountsapi.domain.model.Discount;

public interface SaveDiscount {
	
	Discount save(Discount discount);

}
