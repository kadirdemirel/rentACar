package com.kodlamaio.rentACar.business.concretes;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdditionalService;
import com.kodlamaio.rentACar.business.request.additionals.CreateAdditionalRequest;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalItemRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.Additional;
import com.kodlamaio.rentACar.entities.concretes.AdditionalItem;
import com.kodlamaio.rentACar.entities.concretes.Rental;
@Service
public class AdditionalManager implements AdditionalService {
	@Autowired
	AdditionalRepository additionalRepository;
	@Autowired
	AdditionalItemRepository additionalItemRepository;
	@Autowired
	RentalRepository rentalRepository;
	@Autowired
	ModelMapperService modelMapperService;

	@Override
	public Result add(CreateAdditionalRequest createAdditionalRequest) {
		
		AdditionalItem additionalItem = this.additionalItemRepository.getById(createAdditionalRequest.getAdditionalItemId());
		additionalItem.setId(createAdditionalRequest.getAdditionalItemId());
		
		Rental rental = this.rentalRepository.getById(createAdditionalRequest.getRentalId());
		rental.setId(createAdditionalRequest.getRentalId());
		Additional additional= new Additional();
		additional.setAdditionalItem(additionalItem);
		additional.setRental(rental);
//		Additional additional= this.modelMapperService.forRequest().map(createAdditionalRequest, Additional.class);

		this.additionalRepository.save(additional);
		
		
		return new SuccessResult("Success");
	}

}
