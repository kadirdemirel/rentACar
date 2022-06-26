package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.AdditionalItemService;
import com.kodlamaio.rentACar.business.request.additionalItems.CreateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.request.additionalItems.DeleteAdditionalItemRequest;
import com.kodlamaio.rentACar.business.request.additionalItems.UpdateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.response.additionalItems.GetAllAdditionalItemsResponse;
import com.kodlamaio.rentACar.business.response.additionalItems.ReadAdditionalItemResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionalitems")
public class AdditionalItemsController {

	private AdditionalItemService additionalItemService;

	@Autowired
	public AdditionalItemsController(AdditionalItemService additionalItemService) {
		this.additionalItemService = additionalItemService;
	}

	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateAdditionalItemRequest createAdditionalItemRequest) {
		return this.additionalItemService.add(createAdditionalItemRequest);
	}

	@PostMapping("/update")
	Result update(@RequestBody @Valid UpdateAdditionalItemRequest updateAdditionalItemRequest) {
		return this.additionalItemService.update(updateAdditionalItemRequest);
	}

	@PostMapping("/delete")
	Result delete(@RequestBody DeleteAdditionalItemRequest deleteAdditionalItemRequest) {
		return this.additionalItemService.delete(deleteAdditionalItemRequest);
	}

	@GetMapping("/getbyid")
	DataResult<ReadAdditionalItemResponse> getById(@RequestParam int id) {
		return this.additionalItemService.getById(id);
	}

	@GetMapping("/getall")
	DataResult<List<GetAllAdditionalItemsResponse>> getAll() {
		return this.additionalItemService.getAll();
	}
}
