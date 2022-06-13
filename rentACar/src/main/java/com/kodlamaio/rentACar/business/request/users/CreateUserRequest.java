package com.kodlamaio.rentACar.business.request.users;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

	@Size(min = 2)
	@NotBlank
	@NotEmpty
	@NotNull
	private String firstName;

	@NotBlank
	@NotEmpty
	@NotNull
	@Size(min = 2)
	private String lastName;
	
	@NotBlank
	@NotEmpty
	@NotNull
	@Size(min = 11,max = 11)
	private String nationality;
	@NotBlank
	@NotEmpty
	@NotNull
	@Email
	private String email;

	@NotBlank
	@NotEmpty
	@NotNull
	@Size(min = 4, max = 16)
	private String password;

}
