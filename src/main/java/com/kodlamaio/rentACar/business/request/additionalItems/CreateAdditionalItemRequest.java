package com.kodlamaio.rentACar.business.request.additionalItems;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalItemRequest {

	@NotBlank
	@Size(min = 2)
	private String name;

	@NotNull
	@Min(0)
	private double dailyPrice;
}
