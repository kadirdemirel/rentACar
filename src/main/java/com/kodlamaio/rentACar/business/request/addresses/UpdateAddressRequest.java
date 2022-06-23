package com.kodlamaio.rentACar.business.request.addresses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAddressRequest {
	private int id;
	private String deliveryAddress;
	private String billAddress;
	private int userId;

}