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
import com.shopping.mall.userapi.exception.DuplicateDocumentsException;
import com.shopping.mall.userapi.exception.ResourceNotFoundException;
import com.shopping.mall.userapi.exception.UpdateNotAllowedException;
import com.shopping.mall.userapi.exception.ValidationException;
import com.shopping.mall.userapi.feignclients.UserAuthClient;
import com.shopping.mall.userapi.feignclients.UserCredentialDTO;
import com.shopping.mall.userapi.mapper.UserMapper;
import com.shopping.mall.userapi.model.User;
import com.shopping.mall.userapi.model.dto.RequestUserDTO;
import com.shopping.mall.userapi.model.dto.ResponseUserDTO;
import com.shopping.mall.userapi.model.dto.error.ResponseError;
import com.shopping.mall.userapi.repository.UserRepository;
import com.shopping.mall.userapi.services.UserServices;

@Component
public class UsersServicesImplement implements UserServices {
	
	@Autowired
	private UserRepository repository;
		
	@Autowired
	private Validator validator;
	
	@Autowired
	private UserAuthClient userAuthClient;
	
	private User user;
	private ResponseUserDTO responseUserDTO;
	private List<User> listUser;
	private List<ResponseUserDTO> listResponse;

	@Override
	public ResponseEntity<Object> create(RequestUserDTO requestUserDTO) {
		
		Set<ConstraintViolation<RequestUserDTO>> violations = validator.validate(requestUserDTO);
		
		if(!violations.isEmpty()) {
			LoggerConfig.LOGGER_USER.error("Validation error!");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		userAuthClient.createUser(new UserCredentialDTO(requestUserDTO.getEmail(), requestUserDTO.getPassword()));
		
		user = UserMapper.toModel(requestUserDTO);
		
		duplicateDocumentValidator(user);
		
		repository.save(user);
		
		LoggerConfig.LOGGER_USER.info("User " + user.getFullName() + " salved successfully!");
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}

	
	@Override
	public ResponseUserDTO findByCpf(String cpf) {

		user = repository.findByCpfAndActiveTrue(cpf);
		
		if (user == null) {
			throw new ResourceNotFoundException("Sorry, we could not find a user with this cpf. Check and try again.!");
		}
		
		responseUserDTO = UserMapper.modelToResponseUserDTO(user);
		LoggerConfig.LOGGER_USER.info("User found successfully!");
		return responseUserDTO;
	}
	
	
	@Override
	public List<ResponseUserDTO>findAll() {
		listResponse = new ArrayList<>();
		listUser = repository.findAllByActiveTrue();
		
		for (User user : listUser) {
			responseUserDTO = UserMapper.modelToResponseUserDTO(user);
			listResponse.add(responseUserDTO);
		}
		LoggerConfig.LOGGER_USER.info("User list successfully executed!");
		return listResponse;
	}
	
	
	@Override
	public ResponseUserDTO findByName(String name) {
		
		if (name.length() < 2) {
			throw new ValidationException("Sorry, it was not possible to search by name. The name entered must be at least 2 characters long.");
		}
		
		user = repository.findByFullName(name);
		
		if (user == null) {
			throw new ResourceNotFoundException("Could not find a user with this name. Check and try again.");
		}
		
		responseUserDTO = UserMapper.modelToResponseUserDTO(user);
		
		LoggerConfig.LOGGER_USER.info("User found successfully!");
		return responseUserDTO;
	}
	

	@Override
	public ResponseUserDTO findById(UUID id) {

		user = repository.findByIdAndActiveTrue(id);
		
		if (user == null) {
			throw new ResourceNotFoundException("Sorry, we could not find a user with this id. Check and try again.");
		}
		
		responseUserDTO = UserMapper.modelToResponseUserDTO(user);
		
		LoggerConfig.LOGGER_USER.info("User found successfully!");
		return responseUserDTO;
	}
	
	
	@Override
	public ResponseEntity<Object> update(RequestUserDTO requestUserDTO, UUID userId) {

		Set<ConstraintViolation<RequestUserDTO>> violations = validator.validate(requestUserDTO);
		
		if(!violations.isEmpty()) {
			LoggerConfig.LOGGER_USER.error("Violation error!");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		user = repository.findById(userId).orElseThrow(() ->
			new ResourceNotFoundException("Sorry, we could not find a user with this id. Check and try again.")
		);
		
		if(!user.getCpf().equals(requestUserDTO.getCpf())){
			throw new UpdateNotAllowedException("We were unable to update your information. It is not allowed to change the CPF.");
		}
		
		user = UserMapper.updateUser(user, requestUserDTO);
		repository.save(user);
		
		responseUserDTO = UserMapper.modelToResponseUserDTO(user);
		
		LoggerConfig.LOGGER_USER.info("User data " + user.getFullName() + " salved successfully!");
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	
	@Override
	public User delete(UUID id) {
		
		user = repository.findById(id).orElseThrow(() ->
			new ResourceNotFoundException("Sorry, we could not find a user with this id. Check and try again.")
		);
		
		if(user.getActive() == Boolean.FALSE) {
			throw new ValidationException("This user already has their account disabled.");
		}
		
		user = UserMapper.userDelete(user);
		user.setListAddress(null);
		repository.save(user);
		
		LoggerConfig.LOGGER_USER.info("User data " + user.getFullName() + " deleted successfully!");
		return user;
	}
	
	
	@Override
	public ResponseEntity<ResponseUserDTO> reactivate(UUID userId) {

		user = repository.findById(userId).orElseThrow(() ->
			new ResourceNotFoundException("Sorry, we could not find a user with this id. Check and try again.")
		);
		
		if(user.getActive()) {
			throw new ValidationException("This user already has an active account.");
		}
		
		user.setActive(Boolean.TRUE);
		repository.save(user);
		
		LoggerConfig.LOGGER_USER.info("User data " + user.getFullName() + " reactivated successfully!");
		return new ResponseEntity<ResponseUserDTO>(HttpStatus.NO_CONTENT);
	}
	
	
	private void duplicateDocumentValidator(User user) {		
		User userEntityCpf = repository.findByCpfAndActiveTrue(user.getCpf());
		
		if(userEntityCpf != null) {
			throw new DuplicateDocumentsException("Unable to register user. "
					+ "There is already a registered user with this CPF. Check and try again.");	
		}
	}

}
