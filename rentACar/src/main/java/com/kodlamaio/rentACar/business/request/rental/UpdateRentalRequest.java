package com.kodlamaio.rentACar.business.request.rental;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {

	private int id;
	private LocalDate pickupDate;
	private LocalDate returnedDate;
	private int totalDays;
	private double totalPrice;
	private int carId;
}
