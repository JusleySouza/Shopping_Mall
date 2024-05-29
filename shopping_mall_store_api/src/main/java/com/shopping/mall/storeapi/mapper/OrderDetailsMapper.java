package com.shopping.mall.storeapi.mapper;

import com.shopping.mall.storeapi.model.OrderDetails;
import com.shopping.mall.storeapi.model.dto.RequestOrderDetailsDTO;
import com.shopping.mall.storeapi.model.dto.ResponseOrderDetailsDTO;
import com.shopping.mall.storeapi.model.dto.ResponseProductShoppingDTO;

public class OrderDetailsMapper {
	
	public static OrderDetails toModel(RequestOrderDetailsDTO requestOrderDetailsDTO) {
		return OrderDetails.builder()
				.idProduct(requestOrderDetailsDTO.getIdProduct())
				.amount(requestOrderDetailsDTO.getAmount())
				.build();
	}
	
	public static ResponseOrderDetailsDTO modelToResponseOrderDetailsDTO(OrderDetails orderDetails, ResponseProductShoppingDTO responseProductDTO) {
		return ResponseOrderDetailsDTO.builder()
				.id(orderDetails.getId())
				.product(responseProductDTO)
				.amount(orderDetails.getAmount())
				.subtotal(orderDetails.getSubtotal())
				.build();
	}
	

}
