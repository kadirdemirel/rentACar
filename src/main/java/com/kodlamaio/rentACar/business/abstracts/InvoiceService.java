package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.invoices.CancelInvoiceRequest;
import com.kodlamaio.rentACar.business.request.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.response.invoices.GetAllInvoicesResponse;
import com.kodlamaio.rentACar.business.response.invoices.ReadInvoiceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface InvoiceService {
	Result add(CreateInvoiceRequest createInvoicesRequest);

	DataResult<List<GetAllInvoicesResponse>> getAll();

	DataResult<ReadInvoiceResponse> getById(int id);

	Result cancel(CancelInvoiceRequest cancelInvoiceRequest);
}
