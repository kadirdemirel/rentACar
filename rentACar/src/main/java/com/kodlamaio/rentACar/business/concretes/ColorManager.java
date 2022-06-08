package com.kodlamaio.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.ColorService;
import com.kodlamaio.rentACar.business.request.colors.CreateColorRequest;
import com.kodlamaio.rentACar.business.request.colors.UpdateColorRequest;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.ColorRepository;
import com.kodlamaio.rentACar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {

	@Autowired
	private ColorRepository colorRepository;

	@Override
	public Result add(CreateColorRequest createColorRequest) {
		Color color = new Color();
		color.setName(createColorRequest.getName());
		this.colorRepository.save(color);
		
		return new SuccessResult("ADDED.COLOR");

	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		Color color = new Color();
		color.setId(updateColorRequest.getId());
		color.setName(updateColorRequest.getName());
		
		return new SuccessResult("UPDATED.COLOR");
	}

}
