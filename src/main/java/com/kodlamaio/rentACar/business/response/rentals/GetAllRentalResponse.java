package com.kodlamaio.rentACar.business.response.rentals;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllRentalResponse {
	private LocalDate pickupDate;
	private LocalDate returnedDate;
	private int totalDays;
	private double totalPrice;
	private int carId;
	private int pickUpCityId;
	private int returnCityId;

}
