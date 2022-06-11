package com.kodlamaio.rentACar.business.abstracts;

import com.kodlamaio.rentACar.business.request.users.CreateUserRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface UserService {
	Result add(CreateUserRequest createUserRequest);

}
