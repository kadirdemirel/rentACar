package com.kodlamaio.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AddressService;
import com.kodlamaio.rentACar.business.request.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.business.request.addresses.UpdateAddressRequest;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AddressRepository;
import com.kodlamaio.rentACar.entities.concretes.Address;

@Service
public class AddressManager implements AddressService {

	private ModelMapperService modelMapperService;
	private AddressRepository addressRepository;

	@Autowired
	public AddressManager(ModelMapperService modelMapperService, AddressRepository addressRepository) {
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
		this.addressRepository.save(address);
		return new SuccessResult("ADDED.ADDRESS");
	}

	@Override
	public Result updateDifferentAddress(UpdateAddressRequest updateAddressRequest) {
		checkIfAddressExistsById(updateAddressRequest.getId());
		Address address = this.modelMapperService.forRequest().map(updateAddressRequest, Address.class);
		System.out.println(updateAddressRequest.getBillAddress() + updateAddressRequest.getDeliveryAddress());
		this.addressRepository.save(address);
		return new SuccessResult("UPDATED.ADDRESS");
	}

	@Override
	public Result updateSameAddress(UpdateAddressRequest updateAddressRequest) {
		checkIfAddressExistsById(updateAddressRequest.getId());
		Address address = this.modelMapperService.forRequest().map(updateAddressRequest, Address.class);
		address.setBillAddress(updateAddressRequest.getDeliveryAddress());
		this.addressRepository.save(address);
		return new SuccessResult("UPDATED.ADDRESS");
	}

	private Address checkIfAddressExistsById(int id) {
		Address currentAddress;
		try {
			currentAddress = this.addressRepository.findById(id).get();

		} catch (Exception e) {
			throw new BusinessException("ADDRESS.NOT.EXISTS");
		}
		return currentAddress;

	}
}