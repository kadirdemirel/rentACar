package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CorporateCustomerService;
import com.kodlamaio.rentACar.business.request.corporateCustomers.CreateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.request.corporateCustomers.DeleteCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.request.corporateCustomers.UpdateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.response.corporateCustomers.GetAllCorporateCustomersResponse;
import com.kodlamaio.rentACar.business.response.corporateCustomers.ReadCorporateCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CorporateCustomerRepository;
import com.kodlamaio.rentACar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

	@Autowired
	private CorporateCustomerRepository corporateCustomerRepository;
	@Autowired
	private ModelMapperService modelMapperService;

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerRepository.save(corporateCustomer);
		return new SuccessResult("CORPORATE.CUSTOMER.ADDED");
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		checkIfCorporateCustomerExistsById(updateCorporateCustomerRequest.getCorporateCustomerId());
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerRepository.save(corporateCustomer);
		return new SuccessResult("CORPORATE.CUSTOMER.UPDATED");
	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		checkIfCorporateCustomerExistsById(deleteCorporateCustomerRequest.getCorporateCustomerId());
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(deleteCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerRepository.delete(corporateCustomer);
		return new SuccessResult("CORPORATE.CUSTOMER.DELETED");
	}

	@Override
	public DataResult<ReadCorporateCustomerResponse> getById(int id) {
		CorporateCustomer corporateCustomer = checkIfCorporateCustomerExistsById(id);
		ReadCorporateCustomerResponse readCorporateCustomerResponse = this.modelMapperService.forResponse()
				.map(corporateCustomer, ReadCorporateCustomerResponse.class);
		return new SuccessDataResult<ReadCorporateCustomerResponse>(readCorporateCustomerResponse);

	}

	@Override
	public DataResult<List<GetAllCorporateCustomersResponse>> getAll() {
		List<CorporateCustomer> corporateCustomers = this.corporateCustomerRepository.findAll();
		List<GetAllCorporateCustomersResponse> response = corporateCustomers.stream()
				.map(corporateCustomer -> this.modelMapperService.forResponse().map(corporateCustomer,
						GetAllCorporateCustomersResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCorporateCustomersResponse>>(response);
	}

	@Override
	public CorporateCustomer getByCorporateCustomerId(int id) {
		return checkIfCorporateCustomerExistsById(id);
	}

	private CorporateCustomer checkIfCorporateCustomerExistsById(int id) {
		CorporateCustomer currentCorporateCustomer;
		try {
			currentCorporateCustomer = this.corporateCustomerRepository.findById(id).get();

		} catch (Exception e) {
			throw new BusinessException("CORPORATE.CUSTOMER.NOT.EXISTS");
		}
		return currentCorporateCustomer;

	}

}
