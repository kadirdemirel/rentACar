package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.AddressService;
import com.kodlamaio.rentACar.business.request.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.business.request.addresses.DeleteAddressRequest;
import com.kodlamaio.rentACar.business.request.addresses.UpdateAddressRequest;
import com.kodlamaio.rentACar.business.response.addresses.GetAllAddressesResponse;
import com.kodlamaio.rentACar.business.response.addresses.ReadAddressResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
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

	@PostMapping("/updatesameaddress")
	public Result updateSameAddress(@RequestBody @Valid UpdateAddressRequest updateAddressRequest) {
		return this.addressService.updateSameAddress(updateAddressRequest);
	}

	@PostMapping("/updatedifferentaddress")
	public Result updateDifferentAddress(@RequestBody @Valid UpdateAddressRequest updateAddressRequest) {
		return this.addressService.updateDifferentAddress(updateAddressRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody DeleteAddressRequest deleteAddressRequest) {
		return this.addressService.delete(deleteAddressRequest);
	}

	@GetMapping("/getbyid")
	public DataResult<ReadAddressResponse> getById(@RequestParam int id) {
		return this.addressService.getById(id);
	}

	@GetMapping("/getall")
	public DataResult<List<GetAllAddressesResponse>> getAll() {
		return this.addressService.getAll();
	}
}
