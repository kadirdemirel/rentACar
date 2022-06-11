package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.additionals.CreateAdditionalRequest;
import com.kodlamaio.rentACar.business.request.additionals.DeleteAdditionalRequest;
import com.kodlamaio.rentACar.business.request.additionals.UpdateAdditionalRequest;
import com.kodlamaio.rentACar.business.response.additionals.GetAllAdditionalsResponse;
import com.kodlamaio.rentACar.business.response.additionals.ReadAdditionalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface AdditionalService {

	Result add(CreateAdditionalRequest createAdditionalRequest);

	Result update(UpdateAdditionalRequest updateAdditionalRequest);

	Result delete(DeleteAdditionalRequest deleteAdditionalRequest);

	DataResult<ReadAdditionalResponse> getById(int id);

	DataResult<List<GetAllAdditionalsResponse>> getAll();

}
