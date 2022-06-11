package com.kodlamaio.rentACar.business.request.colors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateColorRequest {
	@NotEmpty
	@NotBlank
	@NotEmpty
	@Size(min = 2)
	private String name;

}
