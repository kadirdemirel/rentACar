package com.kodlamaio.rentACar.business.request.individualCustomers;

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
public class UpdateIndividualCustomerRequest {

	@Min(1)
	private int individualCustomerId;

	@Email
	private String email;

	@NotBlank
	@NotNull
	private String password;
}
