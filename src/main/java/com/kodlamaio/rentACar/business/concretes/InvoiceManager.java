package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.abstracts.OrderedAdditionalItemService;
import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.request.invoices.CancelInvoiceRequest;
import com.kodlamaio.rentACar.business.request.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.response.invoices.GetAllInvoicesResponse;
import com.kodlamaio.rentACar.business.response.invoices.ReadInvoiceResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.InvoiceRepository;
import com.kodlamaio.rentACar.entities.concretes.Invoice;
import com.kodlamaio.rentACar.entities.concretes.OrderedAdditionalItem;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@Service
public class InvoiceManager implements InvoiceService {

	private InvoiceRepository invoiceRepository;
	private RentalService rentalService;
	private OrderedAdditionalItemService orderedAdditionalItemService;
	private ModelMapperService modelMapperService;

	@Autowired
	public InvoiceManager(InvoiceRepository invoiceRepository, RentalService rentalService,
			OrderedAdditionalItemService orderedAdditionalItemService, ModelMapperService modelMapperService) {

		this.invoiceRepository = invoiceRepository;
		this.rentalService = rentalService;
		this.orderedAdditionalItemService = orderedAdditionalItemService;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		checkIfRentalExistsById(createInvoiceRequest.getRentalId());
		checkIfInvoiceExists(createInvoiceRequest);
		checkIfInvoiceNumberExists(createInvoiceRequest.getInvoiceNumber());
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		invoice.setTotalPrice(calculateTotalPrice(createInvoiceRequest.getRentalId()));
		invoice.setState(1);

		this.invoiceRepository.save(invoice);
		return new SuccessResult("INVOICE.CREATED");

	}

	@Override
	public DataResult<List<GetAllInvoicesResponse>> getAll() {
		List<Invoice> invoices = this.invoiceRepository.findAll();
		List<GetAllInvoicesResponse> response = invoices.stream()
				.map(invoice -> this.modelMapperService.forResponse().map(invoice, GetAllInvoicesResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllInvoicesResponse>>(response);
	}

	@Override
	public DataResult<ReadInvoiceResponse> getById(int id) {
		Invoice invoice = checkIfInvoiceExistsById(id);
		ReadInvoiceResponse readInvoiceResponse = this.modelMapperService.forResponse().map(invoice,
				ReadInvoiceResponse.class);
		return new SuccessDataResult<ReadInvoiceResponse>(readInvoiceResponse);
	}

	@Override
	public Result cancel(CancelInvoiceRequest cancelInvoiceRequest) {
		checkIfInvoiceExistsById(cancelInvoiceRequest.getId());
		Invoice invoice = this.modelMapperService.forRequest().map(cancelInvoiceRequest, Invoice.class);
		setInvoice(invoice, cancelInvoiceRequest.getId());
		this.invoiceRepository.save(invoice);
		return new SuccessResult("INVOICE.CANCEL");

	}

	private void setInvoice(Invoice invoice, int id) {
		Invoice tempInvoice = this.invoiceRepository.findById(id).get();
		invoice.setInvoiceNumber(tempInvoice.getInvoiceNumber());
		invoice.setRental(tempInvoice.getRental());
		invoice.setTotalPrice(tempInvoice.getTotalPrice());

		if (invoice.getState() == 1) {
			invoice.setState(0);
		} else {
			throw new BusinessException("ALREADY.CANCELED");
		}

	}

	private double calculateRentalTotalPrice(int rentalId) {
		Rental rental = this.rentalService.getByRentalId(rentalId);
		double totalPrice = rental.getTotalPrice();
		return totalPrice;
	}

	private double calculateTotalPrice(int rentalId) {
		double total = calculateRentalTotalPrice(rentalId) + sumAdditionalTotalPrice(rentalId);
		return total;
	}

	private double sumAdditionalTotalPrice(int id) {
		double result = 0;
		for (OrderedAdditionalItem orderedAdditionalItem : this.orderedAdditionalItemService
				.getByOrderedAdditionalItemsId(id)) {

			result += orderedAdditionalItem.getTotalPrice();
		}
		return result;
	}

	private Invoice checkIfInvoiceExistsById(int id) {
		Invoice currentInvoice;
		try {
			currentInvoice = this.invoiceRepository.findById(id).get();

		} catch (Exception e) {
			throw new BusinessException("INVOICE.NOT.EXISTS");
		}
		return currentInvoice;

	}

	private Rental checkIfRentalExistsById(int rentalId) {
		Rental currentRental;
		try {
			currentRental = this.rentalService.getByRentalId(rentalId);
		} catch (Exception e) {
			throw new BusinessException("RENTAL.NOT.EXISTS");
		}
		return currentRental;
	}

	private void checkIfInvoiceExists(CreateInvoiceRequest createInvoiceRequest) {
		Invoice checkIfRentalId = this.invoiceRepository.findByRentalId(createInvoiceRequest.getRentalId());
		if (checkIfRentalId != null) {
			throw new BusinessException("NOT.ADDED.INVOICE");
		}
	}

	private void checkIfInvoiceNumberExists(String invoiceNumber) {
		Invoice invoice = this.invoiceRepository.findByInvoiceNumber(invoiceNumber);
		if (invoice != null) {
			throw new BusinessException("INVOICE.NOT.EXISTS");
		}
	}

}
