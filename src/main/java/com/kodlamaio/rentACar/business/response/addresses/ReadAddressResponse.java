package com.kodlamaio.rentACar.business.response.addresses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadAddressResponse {
	private int id;

	private String deliveryAddress;

	private String billAddress;

	private int userId;
}
