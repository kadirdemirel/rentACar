package com.kodlamaio.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.UserService;
import com.kodlamaio.rentACar.business.request.users.CreateUserRequest;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.UserRepository;
import com.kodlamaio.rentACar.entities.concretes.User;
@Service
public class UserManager implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ModelMapperService modelMapperService;
	@Override
	public Result add(CreateUserRequest createUserRequest) {
		User user= this.modelMapperService.forRequest().map(createUserRequest, User.class);
		this.userRepository.save(user);		
		return new SuccessResult("ADDED.USER");
	}

}
