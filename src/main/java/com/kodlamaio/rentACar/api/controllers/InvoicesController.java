package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.request.invoices.CancelInvoiceRequest;
import com.kodlamaio.rentACar.business.request.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.response.invoices.GetAllInvoicesResponse;
import com.kodlamaio.rentACar.business.response.invoices.ReadInvoiceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/invoices")
public class InvoicesController {
	@Autowired
	private InvoiceService invoiceService;

	@PostMapping("/add")
	public Result add(@RequestBody CreateInvoiceRequest createInvoiceRequest) {
		return this.invoiceService.add(createInvoiceRequest);
	}

	@GetMapping("/getall")
	public DataResult<List<GetAllInvoicesResponse>> getAll() {
		return this.invoiceService.getAll();
	}

	@GetMapping("/getbyid")
	public DataResult<ReadInvoiceResponse> getById(@RequestParam int id) {
		return this.invoiceService.getById(id);

	}
	
	@PostMapping("/cancel")
	public Result cancel(@RequestBody CancelInvoiceRequest cancelInvoiceRequest) {
		return this.invoiceService.cancel(cancelInvoiceRequest);
	}
}
