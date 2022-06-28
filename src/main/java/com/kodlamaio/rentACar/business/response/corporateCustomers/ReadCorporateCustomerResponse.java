package com.kodlamaio.rentACar.business.response.corporateCustomers;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadCorporateCustomerResponse {
	@Min(1)
	private int corporateCustomerId;

	@NotBlank
	@NotNull
	private String taxNumber;

	@NotBlank
	@NotNull
	private String corporateNumber;

	@Email
	private String email;

	@NotBlank
	@NotNull
	private String password;
}
