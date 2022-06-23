package com.kodlamaio.rentACar.business.response.additionals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadAdditionalResponse {
	private int id;
	private int additionalItemId;
	private int rentAlId;
}
