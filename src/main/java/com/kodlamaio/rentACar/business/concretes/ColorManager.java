package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.ColorService;
import com.kodlamaio.rentACar.business.request.colors.CreateColorRequest;
import com.kodlamaio.rentACar.business.request.colors.DeleteColorRequest;
import com.kodlamaio.rentACar.business.request.colors.UpdateColorRequest;
import com.kodlamaio.rentACar.business.response.colors.GetAllColorsResponse;
import com.kodlamaio.rentACar.business.response.colors.ReadColorResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.ColorRepository;
import com.kodlamaio.rentACar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {

	private ColorRepository colorRepository;
	private ModelMapperService modelMapperService;

	@Autowired
	public ColorManager(ColorRepository colorRepository, ModelMapperService modelMapperService) {

		this.colorRepository = colorRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) {

		checkIfColorExitsByName(createColorRequest.getName());
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		this.colorRepository.save(color);
		return new SuccessResult("ADDED.COLOR");

	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		checkIfColorExistsById(updateColorRequest.getId());
		checkIfColorExitsByName(updateColorRequest.getName());
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorRepository.save(color);
		return new SuccessResult("UPDATED.COLOR");
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		checkIfColorExistsById(deleteColorRequest.getId());
		Color color = this.modelMapperService.forRequest().map(deleteColorRequest, Color.class);
		this.colorRepository.delete(color);
		return new SuccessResult("DELETED.COLOR");
	}

	@Override
	public DataResult<ReadColorResponse> getById(int id) {
		Color color = checkIfColorExistsById(id);
		ReadColorResponse readColorResponse = this.modelMapperService.forResponse().map(color, ReadColorResponse.class);
		return new SuccessDataResult<ReadColorResponse>(readColorResponse);
	}

	@Override
	public DataResult<List<GetAllColorsResponse>> getAll() {
		List<Color> colors = this.colorRepository.findAll();
		List<GetAllColorsResponse> response = colors.stream()
				.map(color -> this.modelMapperService.forResponse().map(color, GetAllColorsResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllColorsResponse>>(response);
	}

	@Override
	public Color getByColorId(int id) {
		return checkIfColorExistsById(id);
	}

	private void checkIfColorExitsByName(String name) {
		Color currentColor = this.colorRepository.findByName(name);
		if (currentColor != null) {
			throw new BusinessException("COLOR.EXITS");
		}
	}

	private Color checkIfColorExistsById(int id) {
		Color currentColor;
		try {
			currentColor = this.colorRepository.findById(id).get();

		} catch (Exception e) {
			throw new BusinessException("COLOR.NOT.EXISTS");
		}
		return currentColor;
	}

}
