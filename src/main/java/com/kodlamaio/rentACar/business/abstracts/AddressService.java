package com.kodlamaio.rentACar.business.abstracts;

import com.kodlamaio.rentACar.business.request.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.business.request.addresses.UpdateAddressRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface AddressService {
	Result addSameAddress(CreateAddressRequest createAddressRequest);

	Result addDifferentAddress(CreateAddressRequest createAddressRequest);

	Result updateDifferentAddress(UpdateAddressRequest updateAddressRequest);

	Result updateSameAddress(UpdateAddressRequest updateAddressRequest);

}