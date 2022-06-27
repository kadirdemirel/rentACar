package com.kodlamaio.rentACar.business.request.additionalItems;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteAdditionalItemRequest {
	@Min(1)
	private int id;
}
