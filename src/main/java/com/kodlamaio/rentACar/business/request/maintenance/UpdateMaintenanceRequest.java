package com.kodlamaio.rentACar.business.request.maintenance;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMaintenanceRequest {

	@Min(1)
	private int id;
	
	@FutureOrPresent
	private LocalDate dateSent;
	
	@Future
	private LocalDate dateReturned;

	
	@Min(1)
	private int carId;

}
