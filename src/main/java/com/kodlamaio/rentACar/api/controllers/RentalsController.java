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

import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.request.rentals.CreateCorporateCustomerRentalRequest;
import com.kodlamaio.rentACar.business.request.rentals.CreateIndividualCustomerRentalRequest;
import com.kodlamaio.rentACar.business.request.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.request.rentals.UpdateCorporateCustomerRentalRequest;
import com.kodlamaio.rentACar.business.request.rentals.UpdateIndividualCustomerRentalRequest;
import com.kodlamaio.rentACar.business.response.rentals.GetAllRentalResponse;
import com.kodlamaio.rentACar.business.response.rentals.ReadRentalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
	@Autowired
	RentalService rentalService;

	@PostMapping("/addIndividualCustomer")
	public Result addIndividualCustomer(
			@RequestBody @Valid CreateIndividualCustomerRentalRequest createIndividualCustomerRentalRequest) {

		return this.rentalService.addIndividualCustomerRental(createIndividualCustomerRentalRequest);

	}

	@PostMapping("/addcorporatecustomer")
	public Result addCorporateCustomer(
			@RequestBody @Valid CreateCorporateCustomerRentalRequest createCorporateCustomerRentalRequest) {
		return this.rentalService.addCorporateCustomerRental(createCorporateCustomerRentalRequest);
	}

	@PostMapping("/updateindividualcustomer")
	Result updateIndividualCustomer(
			@RequestBody @Valid UpdateIndividualCustomerRentalRequest updateIndividualCustomerRentalRequest) {
		return this.rentalService.updateIndividualCustomerRental(updateIndividualCustomerRentalRequest);
	}

	@PostMapping("/updatecorporatecustomer")
	Result updateCorporateCustomer(
			@RequestBody @Valid UpdateCorporateCustomerRentalRequest updateCorporateCustomerRentalRequest) {
		return this.rentalService.updateCorporateCustomerRental(updateCorporateCustomerRentalRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody DeleteRentalRequest deleteRentalRequest) {
		return this.rentalService.delete(deleteRentalRequest);

	}

	@GetMapping("/getbyid")
	public DataResult<ReadRentalResponse> getById(@RequestParam int id) {
		return this.rentalService.getById(id);
	}

	@GetMapping("/getall")
	public DataResult<List<GetAllRentalResponse>> getAll() {
		return this.rentalService.getAll();
	}
}
