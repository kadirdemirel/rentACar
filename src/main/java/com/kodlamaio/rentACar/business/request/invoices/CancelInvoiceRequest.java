package com.kodlamaio.rentACar.business.request.invoices;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelInvoiceRequest {
	@Min(1)
	private int id;
}
