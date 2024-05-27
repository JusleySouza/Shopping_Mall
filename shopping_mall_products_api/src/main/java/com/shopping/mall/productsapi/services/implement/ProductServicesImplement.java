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
import com.shopping.mall.productsapi.exception.DuplicateSkuException;
import com.shopping.mall.productsapi.exception.ResourceNotFoundException;
import com.shopping.mall.productsapi.exception.UpdateNotAllowedException;
import com.shopping.mall.productsapi.exception.ValidationException;
import com.shopping.mall.productsapi.mapper.ProductMapper;
import com.shopping.mall.productsapi.mapper.ResponseSuccessMapper;
import com.shopping.mall.productsapi.model.Category;
import com.shopping.mall.productsapi.model.Product;
import com.shopping.mall.productsapi.model.SubCategory;
import com.shopping.mall.productsapi.model.dto.RequestProductDTO;
import com.shopping.mall.productsapi.model.dto.ResponseProductDTO;
import com.shopping.mall.productsapi.model.dto.ResponseSuccess;
import com.shopping.mall.productsapi.model.dto.error.ResponseError;
import com.shopping.mall.productsapi.repository.CategoryRepository;
import com.shopping.mall.productsapi.repository.ProductRepository;
import com.shopping.mall.productsapi.repository.SubCategoryRepository;
import com.shopping.mall.productsapi.services.ProductServices;

@Component
public class ProductServicesImplement implements ProductServices {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private Validator validator;
	
	private Product product;
	private SubCategory subCategory;
	private Category category;
	private ResponseProductDTO responseProductDTO;
	private List<Product> listProduct;
	private List<ResponseProductDTO> listResponse;
	private ResponseSuccess responseSuccess;

	@Override
	public ResponseEntity<Object> create(RequestProductDTO requestProductDTO) {
		Set<ConstraintViolation<RequestProductDTO>> violations = validator.validate(requestProductDTO);
		
		if(!violations.isEmpty()) {
			LoggerConfig.LOGGER_PRODUCT.error("Validation error!");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		if(requestProductDTO.getIdSubCategory() != null) {
			subCategory = subCategoryRepository.findById(requestProductDTO.getIdSubCategory()).orElseThrow(() -> 
			new ResourceNotFoundException("Sorry, we could not find a sub_category with this id. Check and try again."));
		}
		
		category = categoryRepository.findById(requestProductDTO.getIdCategory()).orElseThrow(() -> 
		new ResourceNotFoundException("Sorry, we could not find a category with this id. Check and try again."));
		
		product = ProductMapper.toModel(requestProductDTO, subCategory, category);
		
		String message = duplicateSKUValidator(product);
		if(!message.isEmpty()) {
			throw new DuplicateSkuException(message);
		}
		
		repository.save(product);
		
		responseSuccess = ResponseSuccessMapper.buildResponseSuccess(Messages.created);
		
		LoggerConfig.LOGGER_PRODUCT.info("Product " + product.getName() + " salved successfully!");
		return new ResponseEntity<Object>(responseSuccess, HttpStatus.CREATED);
	}

	@Override
	public List<ResponseProductDTO> findAll() {
		listResponse = new ArrayList<>();
		listProduct = repository.findAllByActiveTrue();
		
		for (Product product : listProduct) {
			responseProductDTO = ProductMapper.modelToResponseProductDTO(product);
			listResponse.add(responseProductDTO);
		}
		
		LoggerConfig.LOGGER_PRODUCT.info("Product list successfully executed!");
		return listResponse;
	}

	@Override
	public ResponseProductDTO findById(UUID id) {
		product = repository.findByIdAndActiveTrue(id);
		
		if (product == null) {
			throw new ResourceNotFoundException("Sorry, we could not find a product with this id. Check and try again.");
		}
				
		responseProductDTO = ProductMapper.modelToResponseProductDTO(product);
		
		LoggerConfig.LOGGER_PRODUCT.info("Product found successfully!");
		return responseProductDTO;
	}

	@Override
	public ResponseProductDTO findByName(String name) {
		if (name.length() < 2) {
			throw new ValidationException("Sorry, unable to search by name. The name entered must be between 2 and 45 characters long.");
		}
		
		product = repository.findByName(name);
		
		if (product == null) {
			throw new ResourceNotFoundException("Could not find a product with this name. Check and try again.");
		}
		
		responseProductDTO = ProductMapper.modelToResponseProductDTO(product);
		
		LoggerConfig.LOGGER_PRODUCT.info("Product found successfully!");
		return responseProductDTO;
	}

	@Override
	public ResponseEntity<Object> update(RequestProductDTO requestProductDTO, UUID productId) {

		Set<ConstraintViolation<RequestProductDTO>> violations = validator.validate(requestProductDTO);
		
		if(!violations.isEmpty()) {
			LoggerConfig.LOGGER_PRODUCT.error("Violation error!");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		product = repository.findById(productId).orElseThrow(() ->
			new ResourceNotFoundException("Sorry, we could not find a product with this id. Check and try again.")
		);
		
		if(product.getSku() != requestProductDTO.getSku()){
			throw new UpdateNotAllowedException("We were unable to update your information. It is not allowed to change the SKU.");
		}
		
		if(requestProductDTO.getIdSubCategory() != null) {
			subCategory = subCategoryRepository.findById(requestProductDTO.getIdSubCategory()).orElseThrow(() -> 
			new ResourceNotFoundException("Sorry, we could not find a sub_category with this id. Check and try again."));
		}
		category = categoryRepository.findById(requestProductDTO.getIdCategory()).orElseThrow(() -> 
		new ResourceNotFoundException("Sorry, we could not find a category with this id. Check and try again."));
		
		product = ProductMapper.updateProduct(product, requestProductDTO, subCategory, category);
		repository.save(product);
		
		responseProductDTO = ProductMapper.modelToResponseProductDTO(product);
		
		responseSuccess = ResponseSuccessMapper.buildResponseSuccess(Messages.updated);
		
		LoggerConfig.LOGGER_PRODUCT.info("Product data " + product.getName() + " salved successfully!");
		return new ResponseEntity<Object>(responseSuccess, HttpStatus.OK);
	}

	@Override
	public Product delete(UUID id) {

		product = repository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Sorry, we could not find a product with this id. Check and try again.")
		);
		
		if(product.getActive() == Boolean.FALSE) {
			throw new ValidationException("This product is already inactive.");
		}
		
		product = ProductMapper.productDelete(product);
		
		repository.save(product);
		
		responseSuccess = ResponseSuccessMapper.buildResponseSuccess(Messages.deleted);
		
		LoggerConfig.LOGGER_PRODUCT.info("Product data " + product.getName() + " deleted successfully!");
		return product;
	}
	
	
	@Override
	public ResponseEntity<ResponseProductDTO> reactivate(UUID productId) {

		product = repository.findById(productId).orElseThrow(() ->
			new ResourceNotFoundException("Sorry, we could not find a product with this id. Check and try again.")
		);
		
		if(product.getActive()) {
			throw new ValidationException("This product is already active.");
		}
		
		product.setActive(Boolean.TRUE);
		repository.save(product);
		
		LoggerConfig.LOGGER_PRODUCT.info("Product data " + product.getName() + " reactivated successfully!");
		return new ResponseEntity<ResponseProductDTO>(HttpStatus.NO_CONTENT);
	}
	
	
	@Override
	public List<ResponseProductDTO> findByCategory(UUID id) {
		
		listResponse = new ArrayList<>();
		listProduct = repository.findByCategoryId(id);
		
		for (Product product : listProduct) {
			responseProductDTO = ProductMapper.modelToResponseProductDTO(product);
			listResponse.add(responseProductDTO);
		}
		
		LoggerConfig.LOGGER_PRODUCT.info("Product found successfully!");
		return listResponse;
	}
	
	@Override
	public List<ResponseProductDTO> findBySubCategory(UUID id) {
		listResponse = new ArrayList<>();
		listProduct = repository.findBySubCategoryId(id);
		
		for (Product product : listProduct) {
			responseProductDTO = ProductMapper.modelToResponseProductDTO(product);
			listResponse.add(responseProductDTO);
		}
		
		LoggerConfig.LOGGER_PRODUCT.info("Product found successfully!");
		return listResponse;
	}
	
	@Override
	public void subtraction(UUID productId, int amount) {
		product = repository.findById(productId).orElseThrow(() ->
		new ResourceNotFoundException("Sorry, we could not find a product with this id. Check and try again.")
	);
		
		if(product.getStock() >= amount) {
			product.setStock(product.getStock() - amount);
			
			if(product.getStock() <= 0) {
				product.setActive(Boolean.FALSE);
			}
			
			repository.save(product);
			LoggerConfig.LOGGER_PRODUCT.info("Product quantity updated successfully!");
		}else {
			throw new ValidationException("Insufficient stock quantity of the product " + product.getName()
		+ ". Available quantity: " + product.getStock() + ".");
		}
		
	}
	
	@Override
	public void addition(UUID productId, int amount) {
		product = repository.findById(productId).orElseThrow(() ->
		new ResourceNotFoundException("Sorry, we could not find a product with this id. Check and try again.")
	);
		
		if(amount > 0) {
			product.setStock(product.getStock() + amount);
			product.setActive(Boolean.TRUE);
			repository.save(product);
			LoggerConfig.LOGGER_PRODUCT.info("Product quantity updated successfully!");
		}else {
			throw new ValidationException("There are no products to return to stock.");
		}
		
	}
	
	
	private String duplicateSKUValidator(Product product) {
		String message = "";
		
		Product productEntitySku = repository.findBySku(product.getSku());
		
		if(productEntitySku != null) {
			message = "Could not register product. There is already a product registered with this sku. Check and try again.";	
		}
		return message;
	}

}
