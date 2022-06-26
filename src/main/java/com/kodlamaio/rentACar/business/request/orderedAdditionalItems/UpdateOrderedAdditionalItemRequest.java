package com.kodlamaio.rentACar.business.request.orderedAdditionalItems;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderedAdditionalItemRequest {
	private int id;

	@Min(1)
	private int additionalItemId;

	@Min(1)
	private int rentalId;

	@NotNull
	@Min(1)
	private int totalDays;

	@FutureOrPresent
	private LocalDate pickupDate;
}
