package com.kodlamaio.rentACar.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.ColorService;
import com.kodlamaio.rentACar.business.request.colors.CreateColorRequest;
import com.kodlamaio.rentACar.business.request.colors.DeleteColorRequest;
import com.kodlamaio.rentACar.business.request.colors.UpdateColorRequest;
import com.kodlamaio.rentACar.business.response.colors.ReadColorResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.ColorRepository;
import com.kodlamaio.rentACar.entities.concretes.Color;

import net.bytebuddy.asm.Advice.This;

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
		this.colorRepository.save(color);
		
		return new SuccessResult("UPDATED.COLOR");
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		Color color = new Color();
		color.setId(deleteColorRequest.getId());
		this.colorRepository.delete(color);
		return new SuccessResult("DELETED.COLOR") ;
	}

	@Override
	public DataResult<Color> getById(ReadColorResponse readColorResponse) {
		
		return new SuccessDataResult<Color>(this.colorRepository.getById(readColorResponse.getId()));
	}

	@Override
	public DataResult<List<Color>> getAll() {
		
		return new SuccessDataResult<List<Color>>( this.colorRepository.findAll());
	}

}
