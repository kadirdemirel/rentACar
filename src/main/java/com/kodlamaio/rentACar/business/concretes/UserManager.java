package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.UserService;
import com.kodlamaio.rentACar.business.request.users.CreateUserRequest;
import com.kodlamaio.rentACar.business.response.users.GetAllUserResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.UserRepository;
import com.kodlamaio.rentACar.entities.concretes.User;

@Service
public class UserManager implements UserService {

	ModelMapperService modelMapperService;
	UserRepository userRepository;

	@Autowired
	public UserManager(UserRepository userRepository, ModelMapperService modelMapperService) {
		super();
		this.userRepository = userRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateUserRequest createUserRequest) {
		User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);

		this.userRepository.save(user);
		return new SuccessResult("ADDED.USER");

	}

	@Override
	public DataResult<List<GetAllUserResponse>> getAll(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

		List<User> users = this.userRepository.findAll(pageable).getContent();
		List<GetAllUserResponse> response = users.stream()
				.map(user -> this.modelMapperService.forResponse().map(user, GetAllUserResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllUserResponse>>(response);
	}

	@Override
	public User getByUserId(int id) {
		return checkIfUserExistsById(id);
	}

	private User checkIfUserExistsById(int id) {
		User currentUser;
		try {
			currentUser = this.userRepository.findById(id).get();

		} catch (Exception e) {
			throw new BusinessException("USER.NOT.EXISTS");
		}
		return currentUser;

	}

}
