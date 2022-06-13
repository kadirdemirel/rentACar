package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdditionalService;
import com.kodlamaio.rentACar.business.request.additionals.CreateAdditionalRequest;
import com.kodlamaio.rentACar.business.request.additionals.DeleteAdditionalRequest;
import com.kodlamaio.rentACar.business.request.additionals.UpdateAdditionalRequest;
import com.kodlamaio.rentACar.business.response.additionals.GetAllAdditionalsResponse;
import com.kodlamaio.rentACar.business.response.additionals.ReadAdditionalResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
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
	private AdditionalRepository additionalRepository;
	@Autowired
	private AdditionalItemRepository additionalItemRepository;
	@Autowired
	private RentalRepository rentalRepository;
	@Autowired
	private ModelMapperService modelMapperService;

	@Override
	public Result add(CreateAdditionalRequest createAdditionalRequest) {

		AdditionalItem additionalItem = this.additionalItemRepository
				.getById(createAdditionalRequest.getAdditionalItemId());
		Rental rental = this.rentalRepository.getById(createAdditionalRequest.getRentalId());
		rental.setTotalPrice(rental.getTotalPrice() + (rental.getTotalDays() * additionalItem.getDailyPrice()));
		rental.setId(createAdditionalRequest.getRentalId());
		Additional additional = this.modelMapperService.forRequest().map(createAdditionalRequest, Additional.class);

		this.additionalRepository.save(additional);
		return new SuccessResult("ADDED.ADDITIONAL");
	}

	@Override
	public Result update(UpdateAdditionalRequest updateAdditionalRequest) {
		AdditionalItem additionalItem = this.additionalItemRepository
				.getById(updateAdditionalRequest.getAdditionalItemId());
		
		
		Additional additional = this.modelMapperService.forRequest().map(updateAdditionalRequest, Additional.class);
		Rental rental = this.rentalRepository.getById(updateAdditionalRequest.getRentalId());
		rental.setTotalPrice(rental.getTotalPrice() + (rental.getTotalDays() * additionalItem.getDailyPrice()));
		rental.setId(rental.getId());
		
		this.additionalRepository.save(additional);
		return new SuccessResult("UPDATED.ADDITIONAL");
	}

	@Override
	public Result delete(DeleteAdditionalRequest deleteAdditionalRequest) {
		Additional additional = this.modelMapperService.forRequest().map(deleteAdditionalRequest, Additional.class);
		this.additionalRepository.delete(additional);
		return new SuccessResult("DELETED.ADDITIONAL");
	}

	@Override
	public DataResult<ReadAdditionalResponse> getById(int id) {
		Additional additional = this.additionalRepository.getById(id);
		ReadAdditionalResponse response = this.modelMapperService.forResponse().map(additional,
				ReadAdditionalResponse.class);
		return new SuccessDataResult<ReadAdditionalResponse>(response);
	}

	@Override
	public DataResult<List<GetAllAdditionalsResponse>> getAll() {
		List<Additional> additionals = this.additionalRepository.findAll();
		List<GetAllAdditionalsResponse> response = additionals.stream().map(
				additional -> this.modelMapperService.forResponse().map(additional, GetAllAdditionalsResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllAdditionalsResponse>>(response);
	}

}
