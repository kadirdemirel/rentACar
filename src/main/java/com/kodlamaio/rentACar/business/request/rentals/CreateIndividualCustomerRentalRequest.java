package com.kodlamaio.rentACar.business.request.rentals;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateIndividualCustomerRentalRequest {

	@FutureOrPresent
	private LocalDate pickUpDate;

	@Min(1)
	@NotNull
	private int totalDays;

	@Min(1)
	private int carId;

	@NotNull
	@Min(1)
	@Max(81)
	private int pickUpCityId;

	@NotNull
	@Min(1)
	@Max(81)
	private int returnCityId;

	@Min(1)
	private int individualCustomerId;

}
