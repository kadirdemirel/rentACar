package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.business.request.addresses.DeleteAddressRequest;
import com.kodlamaio.rentACar.business.request.addresses.UpdateAddressRequest;
import com.kodlamaio.rentACar.business.response.addresses.GetAllAddressesResponse;
import com.kodlamaio.rentACar.business.response.addresses.ReadAddressResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface AddressService {
	Result addSameAddress(CreateAddressRequest createAddressRequest);

	Result addDifferentAddress(CreateAddressRequest createAddressRequest);

	Result updateDifferentAddress(UpdateAddressRequest updateAddressRequest);

	Result updateSameAddress(UpdateAddressRequest updateAddressRequest);

	Result delete(DeleteAddressRequest deleteAddressRequest);

	DataResult<ReadAddressResponse> getById(int id);

	DataResult<List<GetAllAddressesResponse>> getAll();

}