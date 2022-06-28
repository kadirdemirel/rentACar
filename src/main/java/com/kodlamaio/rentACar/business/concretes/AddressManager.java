package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AddressService;
import com.kodlamaio.rentACar.business.request.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.business.request.addresses.DeleteAddressRequest;
import com.kodlamaio.rentACar.business.request.addresses.UpdateAddressRequest;
import com.kodlamaio.rentACar.business.response.addresses.GetAllAddressesResponse;
import com.kodlamaio.rentACar.business.response.addresses.ReadAddressResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AddressRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.UserRepository;
import com.kodlamaio.rentACar.entities.concretes.Address;
import com.kodlamaio.rentACar.entities.concretes.User;

@Service
public class AddressManager implements AddressService {

	private ModelMapperService modelMapperService;
	private AddressRepository addressRepository;
	private UserRepository userRepository;

	@Autowired
	public AddressManager(ModelMapperService modelMapperService, AddressRepository addressRepository,
			UserRepository userRepository) {
		this.modelMapperService = modelMapperService;
		this.addressRepository = addressRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Result addSameAddress(CreateAddressRequest createAddressRequest) {
		checkIfUserExistsById(createAddressRequest.getUserId());
		Address address = this.modelMapperService.forRequest().map(createAddressRequest, Address.class);

		address.setBillAddress(createAddressRequest.getDeliveryAddress());

		this.addressRepository.save(address);

		return new SuccessResult("ADDED.ADDRESS");

	}

	@Override
	public Result addDifferentAddress(CreateAddressRequest createAddressRequest) {
		checkIfUserExistsById(createAddressRequest.getUserId());
		Address address = this.modelMapperService.forRequest().map(createAddressRequest, Address.class);
		this.addressRepository.save(address);
		return new SuccessResult("ADDED.ADDRESS");
	}

	@Override
	public Result updateDifferentAddress(UpdateAddressRequest updateAddressRequest) {
		checkIfAddressExistsById(updateAddressRequest.getId());
		checkIfUserExistsById(updateAddressRequest.getUserId());
		Address address = this.modelMapperService.forRequest().map(updateAddressRequest, Address.class);
		System.out.println(updateAddressRequest.getBillAddress() + updateAddressRequest.getDeliveryAddress());
		this.addressRepository.save(address);
		return new SuccessResult("UPDATED.ADDRESS");
	}

	@Override
	public Result updateSameAddress(UpdateAddressRequest updateAddressRequest) {
		checkIfAddressExistsById(updateAddressRequest.getId());
		checkIfUserExistsById(updateAddressRequest.getUserId());
		Address address = this.modelMapperService.forRequest().map(updateAddressRequest, Address.class);
		address.setBillAddress(updateAddressRequest.getDeliveryAddress());
		this.addressRepository.save(address);
		return new SuccessResult("UPDATED.ADDRESS");
	}

	@Override
	public Result delete(DeleteAddressRequest deleteAddressRequest) {
		checkIfAddressExistsById(deleteAddressRequest.getId());
		Address address = this.modelMapperService.forRequest().map(deleteAddressRequest, Address.class);

		this.addressRepository.delete(address);
		return new SuccessResult("ADDRESS.DELETED");

	}

	@Override
	public DataResult<ReadAddressResponse> getById(int id) {
		Address address = checkIfAddressExistsById(id);
		ReadAddressResponse readAddressResponse = this.modelMapperService.forResponse().map(address,
				ReadAddressResponse.class);
		return new SuccessDataResult<ReadAddressResponse>(readAddressResponse);
	}

	@Override
	public DataResult<List<GetAllAddressesResponse>> getAll() {
		List<Address> addresses = this.addressRepository.findAll();
		List<GetAllAddressesResponse> response = addresses.stream()
				.map(address -> this.modelMapperService.forResponse().map(address, GetAllAddressesResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAddressesResponse>>(response);
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

	private User checkIfUserExistsById(int id) {
		User currentUser;
		try {
			currentUser = this.userRepository.findById(id).get();

		} catch (Exception e) {
			throw new BusinessException("USER.NOT.EXISTS");
		}
		return currentUser;

	}

}