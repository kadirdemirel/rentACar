package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.abstracts.CorporateCustomerService;
import com.kodlamaio.rentACar.business.abstracts.FindexService;
import com.kodlamaio.rentACar.business.abstracts.IndividualCustomerService;
import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.request.rentals.CreateCorporateCustomerRentalRequest;
import com.kodlamaio.rentACar.business.request.rentals.CreateIndividualCustomerRentalRequest;
import com.kodlamaio.rentACar.business.request.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.request.rentals.UpdateCorporateCustomerRentalRequest;
import com.kodlamaio.rentACar.business.request.rentals.UpdateIndividualCustomerRentalRequest;
import com.kodlamaio.rentACar.business.response.rentals.GetAllRentalResponse;
import com.kodlamaio.rentACar.business.response.rentals.ReadRentalResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CityRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.CorporateCustomer;
import com.kodlamaio.rentACar.entities.concretes.IndividualCustomer;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {

	private RentalRepository rentalRepository;
	private CarService carService;
	private ModelMapperService modelMapperService;
	private FindexService findexService;
	private IndividualCustomerService individualCustomerService;
	private CorporateCustomerService corporateCustomerService;

//bir servis sadece kendi repository'sini enjekte edebilir.
	@Autowired
	public RentalManager(RentalRepository rentalRepository, CarService carService, CityRepository cityRepository,
			ModelMapperService modelMapperService, FindexService findexService,
			IndividualCustomerService individualCustomerService, CorporateCustomerService corporateCustomerService) {
		this.rentalRepository = rentalRepository;
		this.carService = carService;
		this.modelMapperService = modelMapperService;
		this.findexService = findexService;
		this.individualCustomerService = individualCustomerService;
		this.corporateCustomerService = corporateCustomerService;
	}

	@Override
	public Result addIndividualCustomerRental(
			CreateIndividualCustomerRentalRequest createIndividualCustomerRentalRequest) {
		Car car = checkIfCarExistsById(createIndividualCustomerRentalRequest.getCarId());
		IndividualCustomer individualCustomer = checkIfIndividualCustomerExistsById(
				createIndividualCustomerRentalRequest.getIndividualCustomerId());

		checkCarAvailable(car.getId());

		Rental rental = this.modelMapperService.forRequest().map(createIndividualCustomerRentalRequest, Rental.class);
		rental.setReturnDate(calculateReturnDate(createIndividualCustomerRentalRequest.getPickUpDate(),
				createIndividualCustomerRentalRequest.getTotalDays()));
		rental.setTotalPrice(calculateTotalPrice(createIndividualCustomerRentalRequest.getTotalDays(), car, rental));

		checkFindexValue(car.getMinFindex(), individualCustomer.getNationality());

		this.rentalRepository.save(rental);
		return new SuccessResult("ADDED.RENTAL");

	}

	@Override
	public Result addCorporateCustomerRental(
			CreateCorporateCustomerRentalRequest createCorporateCustomerRentalRequest) {
		Car car = checkIfCarExistsById(createCorporateCustomerRentalRequest.getCarId());
		checkIfCorporateCustomerExistsById(createCorporateCustomerRentalRequest.getCorporateCustomerId());
		checkCarAvailable(car.getId());
		Rental rental = this.modelMapperService.forRequest().map(createCorporateCustomerRentalRequest, Rental.class);
		rental.setReturnDate(calculateReturnDate(createCorporateCustomerRentalRequest.getPickUpDate(),
				createCorporateCustomerRentalRequest.getTotalDays()));
		rental.setTotalPrice(calculateTotalPrice(createCorporateCustomerRentalRequest.getTotalDays(), car, rental));
		this.rentalRepository.save(rental);
		return new SuccessResult("ADDED.RENTAL");
	}

	@Override
	public Result updateIndividualCustomerRental(
			UpdateIndividualCustomerRentalRequest updateIndividualCustomerRentalRequest) {
		checkIfRentalExistsById(updateIndividualCustomerRentalRequest.getId());
		Car car = checkIfCarExistsById(updateIndividualCustomerRentalRequest.getCarId());
		IndividualCustomer individualCustomer = checkIfIndividualCustomerExistsById(
				updateIndividualCustomerRentalRequest.getIndividualCustomerId());
		Rental rental = this.modelMapperService.forRequest().map(updateIndividualCustomerRentalRequest, Rental.class);
		rental.setReturnDate(calculateReturnDate(updateIndividualCustomerRentalRequest.getPickUpDate(),
				updateIndividualCustomerRentalRequest.getTotalDays()));
		rental.setTotalPrice(calculateTotalPrice(updateIndividualCustomerRentalRequest.getTotalDays(), car, rental));

		checkCarChangeId(updateIndividualCustomerRentalRequest.getId(), car);

		checkFindexValue(car.getMinFindex(), individualCustomer.getNationality());

		this.rentalRepository.save(rental);
		return new SuccessResult("UPDATED.RENTAL");
	}

	@Override
	public Result updateCorporateCustomerRental(
			UpdateCorporateCustomerRentalRequest updateCorporateCustomerRentalRequest) {
		checkIfRentalExistsById(updateCorporateCustomerRentalRequest.getId());
		Car car = checkIfCarExistsById(updateCorporateCustomerRentalRequest.getCarId());
		checkIfCorporateCustomerExistsById(updateCorporateCustomerRentalRequest.getCorporateCustomerId());
		Rental rental = this.modelMapperService.forRequest().map(updateCorporateCustomerRentalRequest, Rental.class);
		rental.setReturnDate(calculateReturnDate(updateCorporateCustomerRentalRequest.getPickUpDate(),
				updateCorporateCustomerRentalRequest.getTotalDays()));
		rental.setTotalPrice(calculateTotalPrice(updateCorporateCustomerRentalRequest.getTotalDays(), car, rental));

		checkCarChangeId(updateCorporateCustomerRentalRequest.getId(), car);

		this.rentalRepository.save(rental);
		return new SuccessResult("UPDATED.RENTAL");
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		checkIfRentalExistsById(deleteRentalRequest.getId());
		Rental rental = this.modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
		this.rentalRepository.delete(rental);
		return new SuccessResult("DELETED.RENTAL");
	}

	@Override
	public DataResult<ReadRentalResponse> getById(int id) {
		Rental rental = checkIfRentalExistsById(id);
		ReadRentalResponse response = this.modelMapperService.forResponse().map(rental, ReadRentalResponse.class);
		return new SuccessDataResult<ReadRentalResponse>(response);
	}

	@Override
	public DataResult<List<GetAllRentalResponse>> getAll() {
		List<Rental> rentals = this.rentalRepository.findAll();
		List<GetAllRentalResponse> responses = rentals.stream()
				.map(rental -> this.modelMapperService.forResponse().map(rental, GetAllRentalResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllRentalResponse>>(responses);
	}

	@Override
	public Rental geyByRentalId(int id) {
		return checkIfRentalExistsById(id);
	}

	private void checkFindexValue(int findexScore, String nationality) {

		if (findexService.findexScore(nationality) < findexScore) {
			throw new BusinessException("NOT.ENOUGH.FİNDEX.SCORE");
		}

	}

//düzelt
	private Car checkIfCarExistsById(int id) {
		Car currentCar = this.carService.getByCarId(id);
		if (currentCar == null) {
			throw new BusinessException("CAR.NOT.EXISTS");
		}
		return currentCar;

	}

	private void checkCarAvailable(int carId) {

		Car car = checkIfCarExistsById(carId);
		if (car.getState() == 1) {
			car.setState(3);
		} else {
			throw new BusinessException("NOT.CAR.AVAILABLE");
		}

	}

	private LocalDate calculateReturnDate(LocalDate pickUpDate, int totalDays) {
		LocalDate returnvalue = pickUpDate.plusDays(totalDays);
		return returnvalue;

	}

	private double calculateTotalPrice(int totalDays, Car car, Rental rental) {
		double totalPrice = totalDays * car.getDailyPrice();

		if (!rental.getPickUpCity().equals(rental.getReturnCity())) {

			totalPrice += 750;
		}

		return totalPrice;
	}

	private Rental checkIfRentalExistsById(int id) {
		Rental currentRental;
		try {
			currentRental = this.rentalRepository.findById(id).get();
		} catch (Exception e) {
			throw new BusinessException("RENTAL.NOT.EXISTS");
		}
		return currentRental;
	}

	private void checkCarChangeId(int id, Car car) {
		Rental rental = this.rentalRepository.findById(id).get();
		Car tempCar = rental.getCar();

		if (car.getId() != tempCar.getId()) {
			tempCar.setState(1);
			checkCarAvailable(car.getId());
		}

	}

	private CorporateCustomer checkIfCorporateCustomerExistsById(int id) {

		CorporateCustomer currentCorporateCustomer;
		try {
			currentCorporateCustomer = this.corporateCustomerService.getByCorporateCustomerId(id);
		} catch (Exception e) {
			throw new BusinessException("CORPORATE.CUSTOMER.NOT.EXISTS");
		}
		return currentCorporateCustomer;

	}

	private IndividualCustomer checkIfIndividualCustomerExistsById(int id) {

		IndividualCustomer currentIndividualCustomer;
		try {
			currentIndividualCustomer = this.individualCustomerService.getByIndividualCustomerId(id);
		} catch (Exception e) {
			throw new BusinessException("INDIVIDUAL.CUSTOMER.NOT.EXISTS");
		}
		return currentIndividualCustomer;

	}

}
