package com.shopping.mall.productsapi.services.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.shopping.mall.productsapi.config.LoggerConfig;
import com.shopping.mall.productsapi.constants.Messages;
import com.shopping.mall.productsapi.exception.DuplicateCategoryException;
import com.shopping.mall.productsapi.exception.ResourceNotFoundException;
import com.shopping.mall.productsapi.mapper.CategoryMapper;
import com.shopping.mall.productsapi.mapper.ResponseSuccessMapper;
import com.shopping.mall.productsapi.model.Category;
import com.shopping.mall.productsapi.model.dto.RequestCategoryDTO;
import com.shopping.mall.productsapi.model.dto.ResponseCategoryDTO;
import com.shopping.mall.productsapi.model.dto.ResponseSuccess;
import com.shopping.mall.productsapi.model.dto.error.ResponseError;
import com.shopping.mall.productsapi.repository.CategoryRepository;
import com.shopping.mall.productsapi.services.CategoryServices;

@Component
public class CategoryServicesImplement implements CategoryServices {

	@Autowired
	private CategoryRepository repository;
	
	@Autowired
	private Validator validator;
	
	private Category category;
	private ResponseCategoryDTO responseCategoryDTO;
	private List<Category> listCategory;
	private List<ResponseCategoryDTO> listResponse;
	private ResponseSuccess responseSuccess;

	@Override
	public ResponseEntity<Object> create(RequestCategoryDTO requestCategoryDTO) {
		Set<ConstraintViolation<RequestCategoryDTO>> violations = validator.validate(requestCategoryDTO);
		
		if(!violations.isEmpty()) {
			LoggerConfig.LOGGER_CATEGORY.error("Validation error!");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		category = CategoryMapper.toModel(requestCategoryDTO);
		
		String message = duplicateCategoryValidator(category);
		if(!message.isEmpty()) {
			throw new DuplicateCategoryException(message);
		}
		
		repository.save(category);
		
		responseSuccess = ResponseSuccessMapper.buildResponseSuccess(Messages.created);
		
		LoggerConfig.LOGGER_CATEGORY.info("Category " + category.getNameCategory() + " salved successfully!");
		
		return new ResponseEntity<Object>(responseSuccess, HttpStatus.CREATED);
	}

	
	@Override
	public List<ResponseCategoryDTO> findAll() {
		listResponse = new ArrayList<>();
		listCategory = repository.findAll();
		
		for (Category category : listCategory) {
			responseCategoryDTO = CategoryMapper.modelToResponseCategoryDTO(category);
			listResponse.add(responseCategoryDTO);
		}
		
		LoggerConfig.LOGGER_CATEGORY.info("Category list successfully executed!");
		return listResponse;
	}
	

	@Override
	public ResponseCategoryDTO findById(UUID id) {
		category = repository.findById(id).orElseThrow(() -> 
			new ResourceNotFoundException("Sorry, we could not find a category with this id. Check and try again."));
		
		responseCategoryDTO = CategoryMapper.modelToResponseCategoryDTO(category);
		
		LoggerConfig.LOGGER_CATEGORY.info("Category found successfully!");
		return responseCategoryDTO;
	}


	@Override
	public ResponseEntity<Object> update(RequestCategoryDTO requestCategoryDTO, UUID categoryId) {
		Set<ConstraintViolation<RequestCategoryDTO>> violations = validator.validate(requestCategoryDTO);
		
		if(!violations.isEmpty()) {
			LoggerConfig.LOGGER_CATEGORY.error("Violation error!");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		category = repository.findById(categoryId).orElseThrow(() ->
			new ResourceNotFoundException("Sorry, we could not find a category with this id. Check and try again.")
		);
		
		category = CategoryMapper.updateCategory(category, requestCategoryDTO);
		repository.save(category);
		
		responseCategoryDTO = CategoryMapper.modelToResponseCategoryDTO(category);
		
		responseSuccess = ResponseSuccessMapper.buildResponseSuccess(Messages.updated);
		
		LoggerConfig.LOGGER_CATEGORY.info("Category data " + category.getNameCategory() + " salved successfully!");
		return new ResponseEntity<Object>(responseSuccess, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<Object> delete(UUID id) {

		category = repository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Sorry, we could not find a category with this id. Check and try again.")
		);
		
		repository.delete(category);
		
		responseSuccess = ResponseSuccessMapper.buildResponseSuccess(Messages.deleted);
		
		LoggerConfig.LOGGER_CATEGORY.info("Category data " + category.getNameCategory() + " deleted successfully!");
		return new ResponseEntity<Object>(responseSuccess, HttpStatus.OK);
		
		
	}
	
	private String duplicateCategoryValidator(Category category) {
		String message = "";
		
		Category categoryEntityName = repository.findByNameCategory(category.getNameCategory());
		
		if(categoryEntityName != null) {
			message = "Could not register category. There is already a category registered with this name. Check and try again.";	
		}
		return message;
	}
	
}
