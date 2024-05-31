package com.shopping.mall.discountsapi.app.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TypeDiscount {
	
	PROD("PROD"),
	CAT("CAT"),
	SUBCAT("SUBCAT");
	
	private final String type;
	
}
