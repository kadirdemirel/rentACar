package com.kodlamaio.rentACar.business.request.brands;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandRequest {

	private int id;

	@NotBlank
	@Size(min = 2)
	private String name;
}
