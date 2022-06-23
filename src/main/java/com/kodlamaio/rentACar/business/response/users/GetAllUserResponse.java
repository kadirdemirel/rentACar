package com.kodlamaio.rentACar.business.response.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GetAllUserResponse {
	private String firstName;
	private String lastName;
	private String nationalityId;
	private String email;
	private String password;

}
