package com.kodlamaio.rentACar.business.response.invoices;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadInvoiceResponse {
	@Min(1)
	private int id;

	@NotBlank
	@NotNull
	private String invoiceNumber;

	@Min(1)
	private int rentalId;

	@Min(0)
	private double totalPrice;

	private int state;
}
