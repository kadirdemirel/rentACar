package com.kodlamaio.rentACar.business.request.corporateCustomers;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCorporateCustomerRequest {
	@Min(1)
	private int corporateCustomerId;
}
