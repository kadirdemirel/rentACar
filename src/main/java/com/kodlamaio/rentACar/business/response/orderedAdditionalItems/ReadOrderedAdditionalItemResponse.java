package com.kodlamaio.rentACar.business.response.orderedAdditionalItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadOrderedAdditionalItemResponse {
	private int id;
	private int additionalItemId;
	private int rentAlId;
}
