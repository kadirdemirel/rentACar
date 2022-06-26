package com.kodlamaio.rentACar.business.response.maintenances;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadMaintenanceResponse {
	
	@NotNull
	@Min(1)
	private int id;
	private LocalDate dateSentDate;
	private LocalDate dateReturned;
	private int carId;
}
