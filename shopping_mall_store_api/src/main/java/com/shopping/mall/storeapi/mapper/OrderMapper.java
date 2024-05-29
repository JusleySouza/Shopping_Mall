package com.shopping.mall.storeapi.mapper;

import java.time.LocalDate;

import com.shopping.mall.storeapi.model.Order;
import com.shopping.mall.storeapi.model.dto.RequestOrderDTO;
import com.shopping.mall.storeapi.model.dto.ResponseOrderDTO;

public class OrderMapper {
	
	public static Order toModel(RequestOrderDTO requestOrderDTO) {
		return Order.builder()
				.idUser(requestOrderDTO.getIdUser())
				.status(1)
				.total(0)
				.created(LocalDate.now())
				.build();
	}
	
	public static ResponseOrderDTO modelToResponseOrderDTO(Order order) {
		return ResponseOrderDTO.builder()
				.changed(order.getChanged())
				.created(order.getCreated())
				.id(order.getId())
				.status(order.getStatus())
				.total(order.getTotal())
				.build();
	}
	
	public static Order orderDelete(Order order) {
		order.setStatus(5);
		order.setChanged(LocalDate.now());
		return order;
	}

}
