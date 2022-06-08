package com.kodlamaio.rentACar.business.abstracts;

import com.kodlamaio.rentACar.business.request.brands.UpdateBrandRequest;
import com.kodlamaio.rentACar.business.request.colors.CreateColorRequest;
import com.kodlamaio.rentACar.business.request.colors.UpdateColorRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface ColorService {
	Result add(CreateColorRequest createColorRequest);
	Result update(UpdateColorRequest updateColorRequest);
	
}
