package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.UserCheckService;
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
	@Autowired
	UserRepository userRepository;
	@Autowired
	ModelMapperService modelMapperService;
	@Autowired
	private UserCheckService userCheckService;

	public UserManager(UserCheckService userCheckService) {
		this.userCheckService = userCheckService;
	}

	@Override
	public Result add(CreateUserRequest createUserRequest) {
		checkIfUserExistsByNationalityId(createUserRequest.getNationality());
		User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
		if (this.userCheckService.checkIfRealPerson(user)) {
			this.userRepository.save(user);
			return new SuccessResult("ADDED.USER");
		} else {
			throw new BusinessException("Eklenmedi");
		}

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

	private void checkIfUserExistsByNationalityId(String nationality) {
		User currentNationalityId = this.userRepository.findByNationality(nationality);
		if (currentNationalityId != null) {
			throw new BusinessException("USER.EXİST");
		}

	}

}
