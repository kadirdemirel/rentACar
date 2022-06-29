package com.kodlamaio.rentACar.business.response.brands;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadBrandResponse {

	@Min(1)
	private int id;

}
