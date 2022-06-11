package com.kodlamaio.rentACar.business.request.rental;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor


public class CreateRentalRequest {
	private LocalDate pickupDate;
	private int totalDays;
	private int carId;
	private int pickUpCityId;
	private int returnCityId;
	

}
