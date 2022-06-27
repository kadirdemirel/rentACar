package com.kodlamaio.rentACar.business.request.individualCustomers;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteIndividualCustomerRequest {
	@Min(1)
	private int individualCustomerId;
}
