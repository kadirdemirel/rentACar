package com.kodlamaio.rentACar.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.AddressService;
import com.kodlamaio.rentACar.business.request.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/addresses")
public class AddressesController {
	@Autowired
	AddressService addressService;

	@PostMapping("/addsameaddress")
	public Result addSameAddress(@RequestBody @Valid CreateAddressRequest createAddressRequest) {
		return this.addressService.addSameAddress(createAddressRequest);
	}

	@PostMapping("/adddifferentaddress")
	public Result addDifferentAddress(@RequestBody @Valid CreateAddressRequest createAddressRequest) {
		return this.addressService.addDifferentAddress(createAddressRequest);
	}
}
