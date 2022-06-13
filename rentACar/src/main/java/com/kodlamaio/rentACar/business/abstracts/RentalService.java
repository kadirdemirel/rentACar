package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.rental.CreateRentalRequest;
import com.kodlamaio.rentACar.business.request.rental.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.request.rental.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.response.rentals.GetAllRentalResponse;
import com.kodlamaio.rentACar.business.response.rentals.ReadRentalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Rental;

public interface RentalService {
	Result add(CreateRentalRequest createRentalRequest);

	Result update(UpdateRentalRequest updateRentalRequest);

	Result delete(DeleteRentalRequest deleteRentalRequest);

	DataResult<ReadRentalResponse> getById(int id);

	DataResult<List<GetAllRentalResponse>> getAll();

}
