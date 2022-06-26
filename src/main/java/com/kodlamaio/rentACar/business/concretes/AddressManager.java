package com.kodlamaio.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AddressService;
import com.kodlamaio.rentACar.business.request.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AddressRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.UserRepository;
import com.kodlamaio.rentACar.entities.concretes.Address;

@Service
public class AddressManager implements AddressService {

	UserRepository userRepository;
	ModelMapperService modelMapperService;
	AddressRepository addressRepository;

	@Autowired
	public AddressManager(UserRepository userRepository, ModelMapperService modelMapperService,
			AddressRepository addressRepository) {
		this.userRepository = userRepository;
		this.modelMapperService = modelMapperService;
		this.addressRepository = addressRepository;
	}

	@Override
	public Result addSameAddress(CreateAddressRequest createAddressRequest) {

		Address address = this.modelMapperService.forRequest().map(createAddressRequest, Address.class);

		address.setBillAddress(createAddressRequest.getDeliveryAddress());

		this.addressRepository.save(address);

		return new SuccessResult("ADDED.ADDRESS");

	}

	@Override
	public Result addDifferentAddress(CreateAddressRequest createAddressRequest) {
		Address address = this.modelMapperService.forRequest().map(createAddressRequest, Address.class);
		System.out.println(createAddressRequest.getBillAddress() + createAddressRequest.getDeliveryAddress());
		this.addressRepository.save(address);
		return new SuccessResult("ADDED.ADDRESS");
	}

}