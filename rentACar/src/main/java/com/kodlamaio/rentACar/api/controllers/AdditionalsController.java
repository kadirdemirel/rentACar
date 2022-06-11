package com.kodlamaio.rentACar.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.AdditionalService;
import com.kodlamaio.rentACar.business.request.additionals.CreateAdditionalRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionals")
public class AdditionalsController {
	@Autowired
	private AdditionalService additionalService;

	@PostMapping("/add")
	public Result add(@RequestBody CreateAdditionalRequest createAdditionalRequest) {
		return this.additionalService.add(createAdditionalRequest);
	}

}
