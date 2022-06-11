package com.kodlamaio.rentACar.business.response.colors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllColorsResponse {
	private int id;
	private String name;
}
