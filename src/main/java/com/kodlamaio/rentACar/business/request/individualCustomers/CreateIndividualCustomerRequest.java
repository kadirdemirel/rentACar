package com.kodlamaio.rentACar.business.request.individualCustomers;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {

	@NotBlank
	@NotNull
	private String firstName;

	@NotBlank
	@NotNull
	private String lastName;

	@NotBlank
	@NotNull
	private String nationality;


	@NotNull
	private int birthDate;

	@Email
	private String email;

	@NotBlank
	@NotNull
	private String password;

}
