package com.shopping.mall.discountsapi.app.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.shopping.mall.discountsapi.app.dto.RequestDiscountDTO;
import com.shopping.mall.discountsapi.app.dto.ResponseDiscountDTO;
import com.shopping.mall.discountsapi.app.mapper.DiscountMapper;
import com.shopping.mall.discountsapi.app.service.DiscountService;
import com.shopping.mall.discountsapi.cross.config.LoggerConfig;
import com.shopping.mall.discountsapi.cross.dto.error.ResponseError;
import com.shopping.mall.discountsapi.cross.exception.ValidationException;
import com.shopping.mall.discountsapi.domain.usecase.ActivateDiscount;
import com.shopping.mall.discountsapi.domain.usecase.DeactivateDiscount;
import com.shopping.mall.discountsapi.domain.usecase.FindAllTypeDiscount;
import com.shopping.mall.discountsapi.domain.usecase.FindByIdDiscount;
import com.shopping.mall.discountsapi.domain.usecase.SaveDiscount;
import com.shopping.mall.discountsapi.domain.usecase.UpdateDiscount;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Component
public class DiscountServiceImplement implements DiscountService {
	
	private final SaveDiscount saveDiscount;
	private final UpdateDiscount updateDiscount;
	private final FindAllTypeDiscount findAllTypeDiscount;
	private final FindByIdDiscount findByIdDiscount;
	private final DeactivateDiscount deactivateDiscount;
	private final ActivateDiscount activateDiscount;
    private final DiscountMapper mapper;
	private final Validator validator;
	private ResponseDiscountDTO response;
	private List<ResponseDiscountDTO> responseDiscountDTO;

	@Override
	public ResponseEntity<Object> saveDiscount(RequestDiscountDTO requestDiscountDTO) {
		
		Set<ConstraintViolation<RequestDiscountDTO>> violations = validator.validate(requestDiscountDTO);
		
		if(!violations.isEmpty()) {
			LoggerConfig.LOGGER_DISCOUNT.error("Validation error!");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		if(requestDiscountDTO.getExpiration().isBefore(LocalDate.now())) {
			LoggerConfig.LOGGER_DISCOUNT.error("Validation error!");
			throw new ValidationException("The date must be later than the current date.");
		}
		
		mapper.modelToResponseDiscountDTO(saveDiscount.save(mapper.toModel(requestDiscountDTO)));
		
		LoggerConfig.LOGGER_DISCOUNT.info("Discount salved successfully!");
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	

	@Override
	public ResponseEntity<Object> updateDiscount(RequestDiscountDTO requestDiscountDTO, UUID discountId) {

		Set<ConstraintViolation<RequestDiscountDTO>> violations = validator.validate(requestDiscountDTO);
		
		if(!violations.isEmpty()) {
			LoggerConfig.LOGGER_DISCOUNT.error("Validation error!");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		if(requestDiscountDTO.getExpiration().isBefore(LocalDate.now())) {
			LoggerConfig.LOGGER_DISCOUNT.error("Validation error!");
			throw new ValidationException("The date must be later than the current date.");
		}
		
		mapper.modelToResponseDiscountDTO(updateDiscount.update(mapper.toModel(requestDiscountDTO), discountId));
		
		LoggerConfig.LOGGER_DISCOUNT.info("Discount updated successfully!");
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}


	@Override
	public List<ResponseDiscountDTO> findAllTypeDiscount(String typeDiscount) {
		
		responseDiscountDTO = mapper.modelToListResponseDiscountDTO(findAllTypeDiscount.findAll(typeDiscount));
		
		LoggerConfig.LOGGER_DISCOUNT.info("Discounts found successfully!");
		
		return responseDiscountDTO;
	}


	@Override
	public ResponseDiscountDTO findByIdDiscount(UUID id) {
		
		response = mapper.modelToResponseDiscountDTO(findByIdDiscount.findById(id));
		
		LoggerConfig.LOGGER_DISCOUNT.info("Discount found successfully!");
		
		return response;
	}


	@Override
	public ResponseEntity<ResponseDiscountDTO> deactivateDiscount(UUID discountId) {
		
		mapper.modelToResponseDiscountDTO(deactivateDiscount.deactivate(discountId));
		
		LoggerConfig.LOGGER_DISCOUNT.info("Discount deactivated successfully!");
		
		return new ResponseEntity<ResponseDiscountDTO>(HttpStatus.NO_CONTENT);
	}


	@Override
	public ResponseEntity<ResponseDiscountDTO> activateDiscount(UUID discountId) {
		
		mapper.modelToResponseDiscountDTO(activateDiscount.activate(discountId));
		
		LoggerConfig.LOGGER_DISCOUNT.info("Discount activated successfully!");
		
		return new ResponseEntity<ResponseDiscountDTO>(HttpStatus.NO_CONTENT);
	}

}
