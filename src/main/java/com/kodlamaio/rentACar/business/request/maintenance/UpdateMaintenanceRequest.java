package com.kodlamaio.rentACar.business.request.maintenance;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMaintenanceRequest {

	private int id;
	private LocalDate dateSent;
	private LocalDate dateReturned;
	private int carId;

}
