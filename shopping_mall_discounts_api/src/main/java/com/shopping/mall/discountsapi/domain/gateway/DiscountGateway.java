package com.shopping.mall.discountsapi.domain.gateway;

import java.util.List;
import java.util.UUID;

import com.shopping.mall.discountsapi.domain.model.Discount;

public interface DiscountGateway {
	
	Discount saveDiscount(Discount discount);
	
	Discount updateDiscount(Discount discount, UUID idDiscount);
	
	List<Discount> findAllTypeDiscount(String typeDiscount);
	
	Discount findByIdDiscount(UUID idDiscount);
	
	Discount deactivateDiscount(UUID idDiscount);
	
	Discount activateDiscount(UUID idDiscount);

}
