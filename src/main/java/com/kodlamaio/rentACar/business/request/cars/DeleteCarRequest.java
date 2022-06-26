package com.kodlamaio.rentACar.business.request.cars;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCarRequest {


	@Min(1)
	private int id;
}
