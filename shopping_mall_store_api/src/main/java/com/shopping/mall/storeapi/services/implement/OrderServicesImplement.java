package com.shopping.mall.storeapi.services.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.shopping.mall.storeapi.config.LoggerConfig;
import com.shopping.mall.storeapi.exception.ResourceNotFoundException;
import com.shopping.mall.storeapi.exception.ValidationException;
import com.shopping.mall.storeapi.feignclients.ProductFeign;
import com.shopping.mall.storeapi.feignclients.UserFeign;
import com.shopping.mall.storeapi.mapper.OrderDetailsMapper;
import com.shopping.mall.storeapi.mapper.OrderMapper;
import com.shopping.mall.storeapi.model.Order;
import com.shopping.mall.storeapi.model.OrderDetails;
import com.shopping.mall.storeapi.model.dto.ListResponseOrderDTO;
import com.shopping.mall.storeapi.model.dto.RequestOrderDTO;
import com.shopping.mall.storeapi.model.dto.RequestOrderDetailsDTO;
import com.shopping.mall.storeapi.model.dto.ResponseOrderDTO;
import com.shopping.mall.storeapi.model.dto.ResponseOrderDetailsDTO;
import com.shopping.mall.storeapi.model.dto.ResponseProductShoppingDTO;
import com.shopping.mall.storeapi.model.dto.product.ResponseProductDTO;
import com.shopping.mall.storeapi.model.dto.user.ResponseUserDTO;
import com.shopping.mall.storeapi.repository.OrderRepository;
import com.shopping.mall.storeapi.services.OrderServices;

@Component
public class OrderServicesImplement implements OrderServices {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private ProductFeign productFeign;

	@Autowired
	private UserFeign userFeign;

	private Order order;

	private OrderDetails orderDetails;

	private ResponseEntity<ResponseProductDTO> responseProductDTO;

	private List<OrderDetails> listOrderDetails;

	private List<Order> listOrder;

	private ResponseOrderDTO responseOrderDTO;

	private ListResponseOrderDTO listResponseOrderDTO;

	private ResponseOrderDetailsDTO responseOrderDetailsDTO;

	private List<ResponseOrderDTO> listResponseOrder;

	private ResponseEntity<ResponseUserDTO> responseUserDTO;

	@Override
	public ResponseEntity<Object> create(RequestOrderDTO requestOrderDTO) {
		listOrderDetails = new ArrayList<>();

		order = OrderMapper.toModel(requestOrderDTO);

		for (RequestOrderDetailsDTO requestOrderDetailsDTO : requestOrderDTO.getOrderDetails()) {
			orderDetails = OrderDetailsMapper.toModel(requestOrderDetailsDTO);
			responseProductDTO = productFeign.findById(requestOrderDetailsDTO.getIdProduct());
			orderDetails.setSubtotal(orderDetails.getAmount() * responseProductDTO.getBody().getValueUnitary());
			order.setTotal(order.getTotal() + orderDetails.getSubtotal());
			listOrderDetails.add(orderDetails);
		}

		order.setListOrderDetails(listOrderDetails);
		
		for(RequestOrderDetailsDTO requestOrderDetailsDTO : requestOrderDTO.getOrderDetails()) {
			ResponseEntity<Object> product = productFeign.subtraction(requestOrderDetailsDTO.getIdProduct(), 
					requestOrderDetailsDTO.getAmount());
			
			if(!product.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
				System.out.println(product.getBody().toString());
				throw new ValidationException(product.getBody().toString());
			}
		}

		repository.save(order);

		LoggerConfig.LOGGER_ORDER.info("Order salved successfully!");

		return new ResponseEntity<Object>(HttpStatus.CREATED);

	}

	@Override
	public ListResponseOrderDTO findByIdUser(UUID idUser) {
		
		responseUserDTO = userFeign.findById(idUser);
		
		if(responseUserDTO.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			throw new ResourceNotFoundException("Sorry, it was not possible to return the order list. "
			+ "The specified user does not exist.");
		}

		listOrder = repository.findByIdUser(idUser);

		listResponseOrderDTO = this.createResponseDTO(listOrder);

		LoggerConfig.LOGGER_ORDER.info("Order list successfully executed!");
		return listResponseOrderDTO;
	}

	@Override
	public ListResponseOrderDTO findByStatus(int status) {
		listOrder = repository.findByStatus(status);
		
		if (status < 1 || status > 5) {
			throw new ResourceNotFoundException("Sorry, we couldn't find orders with that status.");
		}
		
		listResponseOrderDTO = this.createResponseDTO(listOrder);

		LoggerConfig.LOGGER_ORDER.info("Order list successfully executed!");
		return listResponseOrderDTO;

	}

	@Override
	public ListResponseOrderDTO findByIdOrder(UUID idOrder) {
		listResponseOrderDTO = new ListResponseOrderDTO();
		listResponseOrder = new ArrayList<>();

		order = repository.findById(idOrder)
				.orElseThrow(() -> new ResourceNotFoundException("Sorry, we were unable to find this order."));

		responseOrderDTO = OrderMapper.modelToResponseOrderDTO(order);
		responseOrderDTO.setListOrderDetails(this.createResponseDetailsDTO(order));
		listResponseOrder.add(responseOrderDTO);

		listResponseOrderDTO.setOrder(listResponseOrder);

		LoggerConfig.LOGGER_ORDER.info("Order found successfully!");
		return listResponseOrderDTO;

	}

	@Override
	public ResponseEntity<Object> delete(UUID idOrder) {
		order = repository.findById(idOrder)
				.orElseThrow(() -> new ResourceNotFoundException("Sorry, we were unable to find this order."));

		if (order.getStatus() == 5) {
			throw new ValidationException("This order is already cancelled.");
		}
		else if(order.getStatus() != 1) {
			throw new ValidationException("Order cannot be canceled");
		}
		
		order = OrderMapper.orderDelete(order);
		
		for(OrderDetails orderDetails : order.getListOrderDetails()) {
			ResponseEntity<Object> product = productFeign.addition(orderDetails.getIdProduct(), orderDetails.getAmount());
		
			if(!product.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
				throw new ValidationException(product.getBody().toString());
			}
		}
		
		repository.save(order);

		LoggerConfig.LOGGER_ORDER.info("Order data deleted successfully!");
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

	private ListResponseOrderDTO createResponseDTO(List<Order> listOrder) {
		listResponseOrderDTO = new ListResponseOrderDTO();
		listResponseOrder = new ArrayList<>();

		for (Order order : listOrder) {
			responseOrderDTO = OrderMapper.modelToResponseOrderDTO(order);
			responseOrderDTO.setListOrderDetails(this.createResponseDetailsDTO(order));
			listResponseOrder.add(responseOrderDTO);
		}
		listResponseOrderDTO.setOrder(listResponseOrder);

		return listResponseOrderDTO;

	}

	private List<ResponseOrderDetailsDTO> createResponseDetailsDTO(Order order) {
		List<ResponseOrderDetailsDTO> listResponseOrderDetails = new ArrayList<>();
		ResponseProductShoppingDTO responseProductShoppingDTO = new ResponseProductShoppingDTO();

		for (OrderDetails orderDetails : order.getListOrderDetails()) {
			responseProductDTO = productFeign.findById(orderDetails.getIdProduct());
			responseProductShoppingDTO.setNameProduct(responseProductDTO.getBody().getName());
			responseProductShoppingDTO.setValueUnitary(responseProductDTO.getBody().getValueUnitary());
			responseOrderDetailsDTO = OrderDetailsMapper.modelToResponseOrderDetailsDTO(orderDetails,
					responseProductShoppingDTO);
			listResponseOrderDetails.add(responseOrderDetailsDTO);
		}

		return listResponseOrderDetails;
	}

}
