package com.shopping.mall.storeapi.utils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.shopping.mall.storeapi.model.Order;
import com.shopping.mall.storeapi.model.OrderDetails;
import com.shopping.mall.storeapi.model.dto.ListResponseOrderDTO;
import com.shopping.mall.storeapi.model.dto.RequestOrderDTO;
import com.shopping.mall.storeapi.model.dto.RequestOrderDetailsDTO;
import com.shopping.mall.storeapi.model.dto.ResponseOrderDTO;
import com.shopping.mall.storeapi.model.dto.ResponseOrderDetailsDTO;
import com.shopping.mall.storeapi.model.dto.ResponseProductShoppingDTO;
import com.shopping.mall.storeapi.model.dto.product.RequestProductDTO;
import com.shopping.mall.storeapi.model.dto.product.ResponseProductDTO;
import com.shopping.mall.storeapi.model.dto.user.AddressDTO;
import com.shopping.mall.storeapi.model.dto.user.CountryDTO;
import com.shopping.mall.storeapi.model.dto.user.ResponseUserDTO;

public class ClassBuilder {
	
	private static UUID id = UUID.randomUUID();
	
	public static Order orderBuilder() {
		Order order = new Order();
		order.setId(id);
		order.setIdUser(id);
		order.setStatus(1);
		order.setTotal(1324.98);
		order.setCreated(LocalDate.now());
		order.setChanged(LocalDate.now());
		order.setListOrderDetails(List.of(orderDetailsBuilder()));
		return order;
	}
	
	public static OrderDetails orderDetailsBuilder() {
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setId(id);
		orderDetails.setIdProduct(id);
		orderDetails.setAmount(3);
		orderDetails.setSubtotal(1324.98);
		return orderDetails;
	}
	
	public static RequestOrderDTO requestOrderDTOBuilder() {
		RequestOrderDTO requestOrderDTO = new RequestOrderDTO();
		requestOrderDTO.setIdUser(id);
		requestOrderDTO.setOrderDetails(List.of(requestOrderDetailsDTOBuilder()));
		return requestOrderDTO;
	}
	
	public static RequestOrderDetailsDTO requestOrderDetailsDTOBuilder() {
		RequestOrderDetailsDTO requestOrderDetailsDTO = new RequestOrderDetailsDTO();
		requestOrderDetailsDTO.setAmount(3);
		requestOrderDetailsDTO.setIdProduct(id);
		return requestOrderDetailsDTO;
	}
	
	public static ResponseOrderDTO responseOrderDTOBuilder() {
		ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO();
		responseOrderDTO.setId(id);
		responseOrderDTO.setStatus(1);
		responseOrderDTO.setTotal(1324.98);
		responseOrderDTO.setCreated(LocalDate.now());
		responseOrderDTO.setChanged(LocalDate.now());
		responseOrderDTO.setListOrderDetails(List.of(responseOrderDetailsDTOBuilder()));
		return responseOrderDTO;
	}
	
	public static ResponseOrderDetailsDTO responseOrderDetailsDTOBuilder() {
		ResponseOrderDetailsDTO responseOrderDetailsDTO = new ResponseOrderDetailsDTO();
		responseOrderDetailsDTO.setId(id);
		responseOrderDetailsDTO.setProduct(responseProductShoppingDTOBuilder());
		responseOrderDetailsDTO.setAmount(3);
		responseOrderDetailsDTO.setSubtotal(1324.98);
		return responseOrderDetailsDTO;
	}
	
	public static ResponseProductShoppingDTO responseProductShoppingDTOBuilder() {
		ResponseProductShoppingDTO responseProductShoppingDTO = new ResponseProductShoppingDTO();
		responseProductShoppingDTO.setNameProduct("MacBook Pro");
		responseProductShoppingDTO.setValueUnitary(441.66);
		return responseProductShoppingDTO;
	}
	
	public static ListResponseOrderDTO listResponseOrderDTOBuilder() {
		ListResponseOrderDTO listResponseOrderDTO = new ListResponseOrderDTO();
		listResponseOrderDTO.setOrder(List.of(responseOrderDTOBuilder()));
		return listResponseOrderDTO;
	}
	
	public static RequestProductDTO requestProductDTOBuilder() {
		RequestProductDTO requestProductDTO = new RequestProductDTO();
		requestProductDTO.setName("televisão");
		requestProductDTO.setDescription("produtos novos, portateis e de facil manuseio");
		requestProductDTO.setSku(123);
		requestProductDTO.setValueUnitary(1324.99);
		requestProductDTO.setStock(5);
		requestProductDTO.setIdSubCategory(id);
		requestProductDTO.setIdCategory(id);
		return requestProductDTO;
	}
	
	public static ResponseProductDTO responseProductDTOBuilder() {
		ResponseProductDTO responseProductDTO = new ResponseProductDTO();
		responseProductDTO.setId(id);
		responseProductDTO.setName("televisão");
		responseProductDTO.setDescription("produtos novos, portateis e de facil manuseio");
		responseProductDTO.setSku(123);
		responseProductDTO.setValueUnitary(1324.99);
		responseProductDTO.setStock(5);
		responseProductDTO.setCreated(LocalDate.now());
		responseProductDTO.setIdSubCategory(id);
		responseProductDTO.setIdCategory(id);
		return responseProductDTO;
	}
	
	public static ResponseUserDTO responseUserDTOBuilder() {
		ResponseUserDTO responseUserDTO = new ResponseUserDTO();
		responseUserDTO.setId(UUID.randomUUID());
		responseUserDTO.setName("Paulo");
		responseUserDTO.setLastName("Souza");
		responseUserDTO.setCpf("111.300.458-46");	
		responseUserDTO.setPhone("+055(12)99765-4321");
		responseUserDTO.setCreated(LocalDate.now());
		responseUserDTO.setChanged(LocalDate.now());
		responseUserDTO.setAddressDTO(addressDTOBuilder());
		return responseUserDTO;
	}
	
	public static AddressDTO addressDTOBuilder() {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setStreet("Rua Pedro Maciel");
		addressDTO.setNumber(50);
		addressDTO.setCep("08997-065");
		addressDTO.setComplement("Casa 2");
		addressDTO.setCity("São Paulo");
		addressDTO.setState("SP");
		addressDTO.setNickName("Rua 7");
		addressDTO.setCountryDTO(countryDTOBuilder());
		return addressDTO;	
		
	}
	
	public static CountryDTO countryDTOBuilder() {
		CountryDTO countryDTO = new CountryDTO();
		countryDTO.setName("Brasil");
		countryDTO.setCode("+055");
		return countryDTO;
	}

}
