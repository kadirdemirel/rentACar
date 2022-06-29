package com.kodlamaio.rentACar.business.response.rentals;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllRentalsResponse {
	private LocalDate pickupDate;
	private LocalDate returnedDate;
	private int totalDays;
	private double totalPrice;
	private String brandName;
	private String colorName;
	//private  int carId;
	private int pickUpCityId;
	private int returnCityId;

}
