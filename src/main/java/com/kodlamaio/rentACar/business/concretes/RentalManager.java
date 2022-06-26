package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.FindexService;
import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.request.rental.CreateRentalRequest;
import com.kodlamaio.rentACar.business.request.rental.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.request.rental.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.response.rentals.GetAllRentalResponse;
import com.kodlamaio.rentACar.business.response.rentals.ReadRentalResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CityRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.UserRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.Rental;
import com.kodlamaio.rentACar.entities.concretes.User;

@Service
public class RentalManager implements RentalService {

	RentalRepository rentalRepository;
	CarRepository carRepository;
	CityRepository cityRepository;
	ModelMapperService modelMapperService;
	FindexService findexService;
	UserRepository userRepository;

	@Autowired
	public RentalManager(RentalRepository rentalRepository, CarRepository carRepository, CityRepository cityRepository,
			ModelMapperService modelMapperService, FindexService findexService, UserRepository userRepository) {
		this.rentalRepository = rentalRepository;
		this.carRepository = carRepository;
		this.cityRepository = cityRepository;
		this.modelMapperService = modelMapperService;
		this.findexService = findexService;
		this.userRepository = userRepository;
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		Car car = checkIfCarExistsById(createRentalRequest.getCarId());
		User user = checkIfUserExistsById(createRentalRequest.getUserId());

		checkCarAvailable(car.getId());

		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		rental.setReturnDate(
				calculateReturnDate(createRentalRequest.getPickUpDate(), createRentalRequest.getTotalDays()));
		rental.setTotalPrice(calculateTotalPrice(createRentalRequest.getTotalDays(), car, rental));

		checkFindexValue(car.getMinFindex(), user.getNationality());

		this.rentalRepository.save(rental);
		return new SuccessResult("ADDED.RENTAL");

	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		checkIfRentalExistsById(updateRentalRequest.getId());
		Car car = checkIfCarExistsById(updateRentalRequest.getCarId());
		User user = checkIfUserExistsById(updateRentalRequest.getUserId());

		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		rental.setReturnDate(
				calculateReturnDate(updateRentalRequest.getPickUpDate(), updateRentalRequest.getTotalDays()));
		rental.setTotalPrice(calculateTotalPrice(updateRentalRequest.getTotalDays(), car, rental));

		checkCarChangeId(updateRentalRequest, car);

		checkFindexValue(car.getMinFindex(), user.getNationality());

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

	private void checkFindexValue(int findexScore, String nationality) {

		if (findexService.findexScore(nationality) < findexScore) {
			throw new BusinessException("NOT.ENOUGH.FİNDEX.SCORE");
		}

	}

	private Car checkIfCarExistsById(int id) {
		Car currentCar = this.carRepository.findById(id);
		if (currentCar == null) {
			throw new BusinessException("CAR.NOT.EXISTS");
		}
		return currentCar;

	}

	private User checkIfUserExistsById(int id) {

		User currentUser;
		try {
			currentUser = this.userRepository.findById(id).get();
		} catch (Exception e) {
			throw new BusinessException("USER.NOT.EXISTS");
		}
		return currentUser;

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

	private void checkCarChangeId(UpdateRentalRequest updateRentalRequest, Car car) {
		Rental rental = this.rentalRepository.findById(updateRentalRequest.getId()).get();
		Car tempCar = rental.getCar();

		if (updateRentalRequest.getCarId() != tempCar.getId()) {
			tempCar.setState(1);
			checkCarAvailable(car.getId());
		}

	}
}
