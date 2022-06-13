package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;

import com.kodlamaio.rentACar.business.request.users.CreateUserRequest;
import com.kodlamaio.rentACar.business.response.rentals.GetAllRentalResponse;
import com.kodlamaio.rentACar.business.response.users.GetAllUserResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.User;

public interface UserService {
	Result add(CreateUserRequest createUserRequest);

	DataResult<List<GetAllUserResponse>> getAll(@PathVariable Integer pageNumber, @PathVariable Integer pageSize);
}
