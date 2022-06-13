package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.UserService;
import com.kodlamaio.rentACar.business.request.users.CreateUserRequest;
import com.kodlamaio.rentACar.business.response.rentals.GetAllRentalResponse;
import com.kodlamaio.rentACar.business.response.users.GetAllUserResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.User;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	@Autowired
	UserService userService;

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateUserRequest createUserRequest) {
		return this.userService.add(createUserRequest);
	}

	@GetMapping("/getall/{pageNumber}/{pageSize}")
	public DataResult<List<GetAllUserResponse>> getAll(@PathVariable Integer pageNumber,
			@PathVariable Integer pageSize) {
		return this.userService.getAll(pageNumber, pageSize);
	}

}
