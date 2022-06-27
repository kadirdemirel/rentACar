package com.kodlamaio.rentACar.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.CorporateCustomerService;
import com.kodlamaio.rentACar.business.request.corporateCustomers.CreateCorporateCustomerRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/corporatecustomers")
public class CorporateCustomersController {

	@Autowired
	private CorporateCustomerService corporateCustomerService;

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		return this.corporateCustomerService.add(createCorporateCustomerRequest);
	}

}
