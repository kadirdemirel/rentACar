package com.kodlamaio.rentACar.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.BrandService;
import com.kodlamaio.rentACar.business.request.brands.CreateBrandRequest;
import com.kodlamaio.rentACar.business.request.brands.DeleteBrandRequest;
import com.kodlamaio.rentACar.business.request.brands.UpdateBrandRequest;
import com.kodlamaio.rentACar.business.response.brands.ReadBrandResponse;
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
	@Autowired
	private BrandRepository brandRepository;

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		// mapping
		Brand brand = new Brand();
		brand.setName(createBrandRequest.getName());
		this.brandRepository.save(brand);
		return new SuccessResult("BRAND.ADDED");

	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		Brand brand = new Brand();
		brand.setId(updateBrandRequest.getId());
		brand.setName(updateBrandRequest.getName());
		this.brandRepository.save(brand);
		return new SuccessResult("BRAND.DELETED");

	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {

		Brand brand = new Brand();
		brand.setId(deleteBrandRequest.getId());
		this.brandRepository.delete(brand);
		return new SuccessResult("BRAND.UPDATED");

	}

	@Override
	public DataResult<Brand> getById(ReadBrandResponse readBrandResponse) {

		return new SuccessDataResult<Brand>(this.brandRepository.getById(readBrandResponse.getId()));
	}

	@Override
	public DataResult<List<Brand>> getAll() {
		return new SuccessDataResult<List<Brand>>(this.brandRepository.findAll());
	}

}
