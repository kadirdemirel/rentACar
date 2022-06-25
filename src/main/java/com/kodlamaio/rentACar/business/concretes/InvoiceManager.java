package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.request.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.response.invoices.GetAllInvoicesResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.OrderedAdditionalItemRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.InvoiceRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.OrderedAdditionalItem;
import com.kodlamaio.rentACar.entities.concretes.Invoice;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@Service
public class InvoiceManager implements InvoiceService {
	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private RentalRepository rentalRepository;

	@Autowired
	private OrderedAdditionalItemRepository additionalRepository;

	@Autowired
	private ModelMapperService modelMapperService;

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {

		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		double totalPrice = this.rentalRepository.getById(createInvoiceRequest.getRentalId()).getTotalPrice();
		Invoice checkIfRentAlId = this.invoiceRepository.findByRentalId(createInvoiceRequest.getRentalId());
		invoice.setState(1);
		double additionalTotalPrice = sumAdditionalTotalPrice(createInvoiceRequest.getRentalId());
		invoice.setTotalPrice(totalPrice + additionalTotalPrice);
		if (checkIfRentAlId == null) {
			this.invoiceRepository.save(invoice);
			return new SuccessResult("INVOICE.CREATED");
		} else {
			throw new BusinessException("NOT.ADDED.INVOICE");
		}

	}

	@Override
	public DataResult<List<GetAllInvoicesResponse>> getAll() {
		List<Invoice> invoices = this.invoiceRepository.findAll();
		List<GetAllInvoicesResponse> response = invoices.stream()
				.map(invoice -> this.modelMapperService.forResponse().map(invoice, GetAllInvoicesResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllInvoicesResponse>>(response);
	}

	private double calculateTotalPrice(int rentalId) {
		Rental rental = this.rentalRepository.getById(rentalId);
		double totalPrice = rental.getTotalPrice();
		return totalPrice;
	}

	private double sumAdditionalTotalPrice(int id) {
		double result = 0;
		for (OrderedAdditionalItem additional : additionalRepository.getByRentalId(id)) {

			result += additional.getTotalPrice();
		}
		return result;
	}
}
