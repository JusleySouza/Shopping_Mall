package com.shopping.mall.userapi.services.implement;

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

import com.shopping.mall.userapi.config.LoggerConfig;
import com.shopping.mall.userapi.exception.ResourceNotFoundException;
import com.shopping.mall.userapi.exception.ValidationException;
import com.shopping.mall.userapi.mapper.AddressMapper;
import com.shopping.mall.userapi.model.Address;
import com.shopping.mall.userapi.model.User;
import com.shopping.mall.userapi.model.dto.AddressDTO;
import com.shopping.mall.userapi.model.dto.error.ResponseError;
import com.shopping.mall.userapi.repository.AddressRepository;
import com.shopping.mall.userapi.repository.UserRepository;
import com.shopping.mall.userapi.services.AddressServices;

@Component
public class AddressServicesImplement implements AddressServices {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private Validator validator;
	
	private User user;
	private Address address;
	private AddressDTO addressDTO;
	private List<Address> listAddress;
	private List<AddressDTO> listResponse;

	@Override
	public ResponseEntity<Object> create(AddressDTO addressDTO, UUID userID) {
		listAddress = new ArrayList<>();
		Set<ConstraintViolation<AddressDTO>> violations = validator.validate(addressDTO);
		
		if(!violations.isEmpty()) {
			LoggerConfig.LOGGER_ADDRESS.error("Validation error!");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		duplicateNickNameValidator(addressDTO.getNickName());
		
		address = AddressMapper.toModel(addressDTO);
		
		user = userRepository.findByIdAndActiveTrue(userID);
		
		if(user.getListAddress().isEmpty()) {
			address.setDefaultAddress(Boolean.TRUE);
		}
		else if(addressDTO.getDefaultAddress()){
			for(Address addressEntity : user.getListAddress()) {
				addressEntity.setDefaultAddress(Boolean.FALSE);
			}
		}
		
		listAddress.addAll(user.getListAddress());
		listAddress.add(address);
		
		user.setListAddress(listAddress);

		userRepository.save(user);
		
		LoggerConfig.LOGGER_ADDRESS.info(" Address User " + user.getFullName() + " salved successfully!");
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	
	@Override
	public ResponseEntity<Object> update(AddressDTO addressDTO, UUID addressId) {
		listAddress = new ArrayList<>();
		Set<ConstraintViolation<AddressDTO>> violations = validator.validate(addressDTO);
		
		if(!violations.isEmpty()) {
			LoggerConfig.LOGGER_ADDRESS.error("Violation error!");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		address = addressRepository.findById(addressId).orElseThrow(() -> 
			new ResourceNotFoundException("Sorry, we could not find a address with this id. Check and try again.")
		);
		
		user = userRepository.findByIdAndActiveTrue(address.getUser().getId());
		
		if(addressDTO.getDefaultAddress()){
			for(Address address : user.getListAddress()) {
				if(!address.getId().equals(addressId)) {
					address.setDefaultAddress(Boolean.FALSE);	
				}
				else {
					address = AddressMapper.updateAddress(address, addressDTO);
				}
			}
		}
		
		listAddress.addAll(user.getListAddress());
		
		user.setListAddress(listAddress);

		userRepository.save(user);
		
		addressDTO = AddressMapper.toDTO(address);
		
		LoggerConfig.LOGGER_ADDRESS.info("Address data salved successfully!");
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@Override
	public ResponseEntity<Object> delete(UUID id) {
		listAddress = new ArrayList<>();
		address = addressRepository.findById(id).orElseThrow(() ->
			new ResourceNotFoundException("Sorry, we could not find a address with this id. Check and try again.")
		);
		
		user = userRepository.findByIdAndActiveTrue(address.getUser().getId());
		
		
		for(Address addressEntity : user.getListAddress()) {
			if(addressEntity.getId().equals(id)) {
				addressRepository.delete(address);
			}
			else {
				listAddress.add(addressEntity);
			}
		}
		user.setListAddress(listAddress);
		
		userRepository.save(user);
		
		LoggerConfig.LOGGER_ADDRESS.info("Address data deleted successfully!");
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}	
	
	
	@Override
	public AddressDTO findByIdAddress(UUID id) {
		address = addressRepository.findById(id).orElseThrow(() ->
			new ResourceNotFoundException("Sorry, we could not find a address with this id. Check and try again.")
		);
		
		addressDTO = AddressMapper.toDTO(address);
		
		LoggerConfig.LOGGER_ADDRESS.info("Address found successfully!");
		return addressDTO;
	}
	
	
	@Override
	public List<AddressDTO> findByIdUser(UUID id) {
		listResponse = new ArrayList<>();
		listAddress = addressRepository.findByUserId(id);
		
		for (Address address : listAddress) {
			addressDTO = AddressMapper.toDTO(address);
			listResponse.add(addressDTO);
		}
		
		LoggerConfig.LOGGER_ADDRESS.info("Address found successfully!");
		return listResponse;
	}
	
	
	private void duplicateNickNameValidator(String nickName) {		
		Address addressEntityNickName =  addressRepository.findByNickName(nickName);
	
		if(addressEntityNickName != null) {
			throw new ValidationException("Unable to register address. "
				+ "There is already a registered address with this nickName. Check and try again.");
		}
	}


}
