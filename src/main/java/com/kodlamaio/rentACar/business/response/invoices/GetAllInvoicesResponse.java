package com.kodlamaio.rentACar.business.response.invoices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllInvoicesResponse {
	private String invoiceNumber;
	private int rentalId;
	private double totalPrice;
	private int state;
}
