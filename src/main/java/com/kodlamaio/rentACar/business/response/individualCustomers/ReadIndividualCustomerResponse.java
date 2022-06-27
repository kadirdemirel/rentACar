package com.kodlamaio.rentACar.business.response.individualCustomers;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadIndividualCustomerResponse {

	@Min(1)
	private int individualCustomerId;
}
