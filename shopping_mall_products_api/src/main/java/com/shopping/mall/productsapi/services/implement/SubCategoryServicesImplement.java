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
import com.shopping.mall.productsapi.mapper.ResponseSuccessMapper;
import com.shopping.mall.productsapi.mapper.SubCategoryMapper;
import com.shopping.mall.productsapi.model.Category;
import com.shopping.mall.productsapi.model.SubCategory;
import com.shopping.mall.productsapi.model.dto.RequestSubCategoryDTO;
import com.shopping.mall.productsapi.model.dto.ResponseSubCategoryDTO;
import com.shopping.mall.productsapi.model.dto.ResponseSuccess;
import com.shopping.mall.productsapi.model.dto.error.ResponseError;
import com.shopping.mall.productsapi.repository.CategoryRepository;
import com.shopping.mall.productsapi.repository.SubCategoryRepository;
import com.shopping.mall.productsapi.services.SubCategoryServices;

@Component
public class SubCategoryServicesImplement implements SubCategoryServices {
	
	@Autowired
	private SubCategoryRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private Validator validator;
	
	private SubCategory subCategory;
	private Category category;
	private ResponseSubCategoryDTO responseSubCategoryDTO;
	private List<SubCategory> listSubCategory;
	private List<ResponseSubCategoryDTO> listResponse;
	private ResponseSuccess responseSuccess;

	@Override
	public ResponseEntity<Object> create(RequestSubCategoryDTO requestSubCategoryDTO) {
		Set<ConstraintViolation<RequestSubCategoryDTO>> violations = validator.validate(requestSubCategoryDTO);
		
		if(!violations.isEmpty()) {
			LoggerConfig.LOGGER_SUB_CATEGORY.error("Validation error!");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		category = categoryRepository.findById(requestSubCategoryDTO.getIdCategory()).orElseThrow(() -> 
		new ResourceNotFoundException("Sorry, we could not find a category with this id. Check and try again."));
		
		subCategory = SubCategoryMapper.toModel(requestSubCategoryDTO, category);
		
		String message = duplicateSubCategoryValidator(subCategory);
		if(!message.isEmpty()) {
			throw new DuplicateCategoryException(message);
		}
		
		repository.save(subCategory);
		
		responseSuccess = ResponseSuccessMapper.buildResponseSuccess(Messages.created);
		
		LoggerConfig.LOGGER_SUB_CATEGORY.info("SubCategory " + subCategory.getNameSubCategory() + " salved successfully!");
		return new ResponseEntity<Object>(responseSuccess, HttpStatus.CREATED);
	}

	@Override
	public List<ResponseSubCategoryDTO> findAll() {
		listResponse = new ArrayList<>();
		listSubCategory = repository.findAll();
		
		for (SubCategory subCategory : listSubCategory) {
			responseSubCategoryDTO = SubCategoryMapper.modelToResponseSubCategoryDTO(subCategory);
			listResponse.add(responseSubCategoryDTO);
		}
		
		LoggerConfig.LOGGER_SUB_CATEGORY.info("SubCategory list successfully executed!");
		return listResponse;
	}

	@Override
	public ResponseSubCategoryDTO findById(UUID id) {
		subCategory = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sorry, we could not find a sub_category with this id. Check and try again."));
		
		responseSubCategoryDTO = SubCategoryMapper.modelToResponseSubCategoryDTO(subCategory);
		
		LoggerConfig.LOGGER_SUB_CATEGORY.info("SubCategory found successfully!");
		return responseSubCategoryDTO;
	}

	@Override
	public ResponseEntity<Object> update(RequestSubCategoryDTO requestSubCategoryDTO, UUID subCategoryId) {
		Set<ConstraintViolation<RequestSubCategoryDTO>> violations = validator.validate(requestSubCategoryDTO);
		
		if(!violations.isEmpty()) {
			LoggerConfig.LOGGER_SUB_CATEGORY.error("Violation error!");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		subCategory = repository.findById(subCategoryId).orElseThrow(() ->
			new ResourceNotFoundException("Sorry, we could not find a sub_category with this id. Check and try again.")
		);
		
		category = categoryRepository.findById(requestSubCategoryDTO.getIdCategory()).orElseThrow(() -> 
		new ResourceNotFoundException("Sorry, we could not find a category with this id. Check and try again."));
		
		subCategory = SubCategoryMapper.updateSubCategory(subCategory, requestSubCategoryDTO, category);
		repository.save(subCategory);
		
		responseSubCategoryDTO = SubCategoryMapper.modelToResponseSubCategoryDTO(subCategory);
		
		responseSuccess = ResponseSuccessMapper.buildResponseSuccess(Messages.updated);
		
		LoggerConfig.LOGGER_SUB_CATEGORY.info("SubCategory data " + subCategory.getNameSubCategory() + " salved successfully!");
		return new ResponseEntity<Object>(responseSuccess, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> delete(UUID id) {

		subCategory = repository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Sorry, we could not find a sub_category with this id. Check and try again.")
		);
		
		repository.delete(subCategory);
		
		responseSuccess = ResponseSuccessMapper.buildResponseSuccess(Messages.deleted);
		
		LoggerConfig.LOGGER_SUB_CATEGORY.info("SubCategory data " + subCategory.getNameSubCategory() + " deleted successfully!");
		return new ResponseEntity<Object>(responseSuccess, HttpStatus.OK);
	}
	
	
	@Override
	public List<ResponseSubCategoryDTO> findByCategory(UUID id) {
		listResponse = new ArrayList<>();
		listSubCategory = repository.findByCategoryId(id);
		
		for (SubCategory subCategory : listSubCategory) {
			responseSubCategoryDTO = SubCategoryMapper.modelToResponseSubCategoryDTO(subCategory);
			listResponse.add(responseSubCategoryDTO);
		}
		
		LoggerConfig.LOGGER_SUB_CATEGORY.info("SubCategory found successfully!");
		return listResponse;
	}
	
	private String duplicateSubCategoryValidator(SubCategory subCategory) {
		String message = "";
		
		SubCategory subCategoryEntityName = repository.findByNameSubCategory(subCategory.getNameSubCategory());
		
		if(subCategoryEntityName != null) {
			message = "Could not register sub_category. There is already a sub_category registered with this name. Check and try again.";	
		}
		return message;
	}
	
}
