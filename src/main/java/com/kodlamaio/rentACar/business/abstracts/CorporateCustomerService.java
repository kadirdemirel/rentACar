package com.kodlamaio.rentACar.business.abstracts;

import com.kodlamaio.rentACar.business.request.corporateCustomers.CreateCorporateCustomerRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface CorporateCustomerService {
	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
}
