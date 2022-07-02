package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.BrandService;
import com.kodlamaio.rentACar.business.request.brands.CreateBrandRequest;
import com.kodlamaio.rentACar.business.request.brands.DeleteBrandRequest;
import com.kodlamaio.rentACar.business.request.brands.UpdateBrandRequest;
import com.kodlamaio.rentACar.business.response.brands.GetAllBrandsResponse;
import com.kodlamaio.rentACar.business.response.brands.ReadBrandResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.BrandRepository;
import com.kodlamaio.rentACar.entities.concretes.Brand;

//BrandServiceImpl
@Service
public class BrandManager implements BrandService {

	// Git constructor parametresine bak git onu newle bana onu ver.

	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;

	@Autowired
	public BrandManager(BrandRepository brandRepository, ModelMapperService modelMapperService) {
		this.brandRepository = brandRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		// mapping
		checkIfBrandExistsByName(createBrandRequest.getName());
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);

		this.brandRepository.save(brand);
		return new SuccessResult("BRAND.ADDED");

	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		checkIfBrandExistsById(updateBrandRequest.getId());
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandRepository.save(brand);
		return new SuccessResult("BRAND.UPDATED");

	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		checkIfBrandExistsById(deleteBrandRequest.getId());
		Brand brand = this.modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
		this.brandRepository.delete(brand);
		return new SuccessResult("BRAND.DELETED");

	}

	@Override
	public DataResult<ReadBrandResponse> getById(int id) {
		Brand brand = checkIfBrandExistsById(id);
		ReadBrandResponse readBrandResponse = this.modelMapperService.forResponse().map(brand, ReadBrandResponse.class);
		return new SuccessDataResult<ReadBrandResponse>(readBrandResponse);
	}

	@Override
	public DataResult<List<GetAllBrandsResponse>> getAll() {

		List<Brand> brands = this.brandRepository.findAll();
		List<GetAllBrandsResponse> response = brands.stream()
				.map(brand -> this.modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllBrandsResponse>>(response);
	}

	@Override
	public Brand getByBrandId(int id) {

		return checkIfBrandExistsById(id);
	}

	@Override
	public Result addBrand(CreateBrandRequest createBrandRequest) {
		checkIfBrandExistsByName(createBrandRequest.getName());
		Brand brand = convertCreateBrandRequestToEntity(createBrandRequest);
		this.brandRepository.save(brand);
		return new SuccessResult("BRAND.ADDED");
	}

	@Override
	public DataResult<ReadBrandResponse> getBrand(int id) {
		Brand brand = checkIfBrandExistsById(id);
		ReadBrandResponse readBrandResponse = this.modelMapperService.forResponse().map(brand, ReadBrandResponse.class);
		return new SuccessDataResult<ReadBrandResponse>(readBrandResponse);
	}

	private Brand convertCreateBrandRequestToEntity(CreateBrandRequest createBrandRequest) {
		return Brand.builder().name(createBrandRequest.getName()).build();

	}

	private void checkIfBrandExistsByName(String name) {
		Brand currentBrand = this.brandRepository.findByName(name);
		if (currentBrand != null) {
			throw new BusinessException("BRAND.EXISTS");
		}
	}

	private Brand checkIfBrandExistsById(int id) {
		Brand currentBrand;
		try {
			currentBrand = this.brandRepository.findById(id).get();

		} catch (Exception e) {
			throw new BusinessException("BRAND.NOT.EXISTS");
		}
		return currentBrand;

	}

}
