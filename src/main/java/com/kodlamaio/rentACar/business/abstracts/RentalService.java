package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.rentals.CreateCorporateCustomerRentalRequest;
import com.kodlamaio.rentACar.business.request.rentals.CreateIndividualCustomerRentalRequest;
import com.kodlamaio.rentACar.business.request.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.request.rentals.UpdateCorporateCustomerRentalRequest;
import com.kodlamaio.rentACar.business.request.rentals.UpdateIndividualCustomerRentalRequest;
import com.kodlamaio.rentACar.business.response.rentals.GetAllRentalResponse;
import com.kodlamaio.rentACar.business.response.rentals.ReadRentalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Rental;

public interface RentalService {

	Result delete(DeleteRentalRequest deleteRentalRequest);

	DataResult<ReadRentalResponse> getById(int id);

	DataResult<List<GetAllRentalResponse>> getAll();

	Result addIndividualCustomerRental(CreateIndividualCustomerRentalRequest createRentalRequest);

	Result addCorporateCustomerRental(CreateCorporateCustomerRentalRequest createCorporateCustomerRentalRequest);

	Result updateIndividualCustomerRental(UpdateIndividualCustomerRentalRequest updateIndividualCustomerRentalRequest);

	Result updateCorporateCustomerRental(UpdateCorporateCustomerRentalRequest updateCorporateCustomerRentalRequest);

	public Rental geyByRentalId(int id);

}
