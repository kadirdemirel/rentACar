package com.kodlamaio.rentACar.business.abstracts;

import com.kodlamaio.rentACar.business.request.additionals.CreateAdditionalRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface AdditionalService {
	
	Result add(CreateAdditionalRequest createAdditionalRequest);

}
