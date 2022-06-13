package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.BrandService;
import com.kodlamaio.rentACar.business.request.brands.CreateBrandRequest;
import com.kodlamaio.rentACar.business.request.brands.DeleteBrandRequest;
import com.kodlamaio.rentACar.business.request.brands.UpdateBrandRequest;
import com.kodlamaio.rentACar.business.response.brands.GetAllBrandsResponse;
import com.kodlamaio.rentACar.business.response.brands.ReadBrandResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Brand;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {

	@Autowired
	private BrandService brandService;

	// endpoint
	@GetMapping("/sayhello")
	public String sayHello() {
		return "Hello Spring :)";
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
		return this.brandService.add(createBrandRequest);
	}

	@GetMapping("/getbyid")
	public DataResult<ReadBrandResponse> getById(@RequestParam int id) {
		return this.brandService.getById(id);
	}

	@PostMapping("/update")
	public Result update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) {
		return this.brandService.update(updateBrandRequest);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteBrandRequest deleteBrandRequest) {
		return this.brandService.delete(deleteBrandRequest);
	}

	@GetMapping("/getall")
	public DataResult<List<GetAllBrandsResponse>> getAll() {
		return this.brandService.getAll();
	}

}
