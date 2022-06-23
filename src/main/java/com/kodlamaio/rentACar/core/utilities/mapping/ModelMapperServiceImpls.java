package com.kodlamaio.rentACar.core.utilities.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Service;
@Service
public class ModelMapperServiceImpls implements ModelMapperService {
	private ModelMapper modelMapper;

	public ModelMapperServiceImpls(ModelMapper modelMapper) {
		super();
		this.modelMapper = modelMapper;
	}

	@Override
	public ModelMapper forResponse() {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true)
		.setMatchingStrategy(MatchingStrategies.LOOSE);
		return this.modelMapper;
	}

	@Override
	public ModelMapper forRequest() {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true)
		.setMatchingStrategy(MatchingStrategies.STANDARD);
		return this.modelMapper;
	}
	
	
//Bean : bellekte oluşturulmuş instance
}
