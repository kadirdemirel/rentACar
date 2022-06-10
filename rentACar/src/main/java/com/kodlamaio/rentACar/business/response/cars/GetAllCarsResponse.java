package com.kodlamaio.rentACar.business.response.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCarsResponse {
	private int id;
	private String description;
	private double dailyPrice;
	private int state;
	private String plate;
	private int kilometer;

}
