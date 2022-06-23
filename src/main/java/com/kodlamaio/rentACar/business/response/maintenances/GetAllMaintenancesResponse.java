package com.kodlamaio.rentACar.business.response.maintenances;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllMaintenancesResponse {
	private int id;
	private LocalDate dateSentDate;
	private LocalDate dateReturned;
	private int carId;

}
