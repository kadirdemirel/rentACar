package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.request.rental.CreateRentalRequest;
import com.kodlamaio.rentACar.business.request.rental.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.request.rental.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.response.rentals.GetAllRentalResponse;
import com.kodlamaio.rentACar.business.response.rentals.ReadRentalResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CityRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.City;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {

	@Autowired
	RentalRepository rentalRepository;
	@Autowired
	CarRepository carRepository;
	@Autowired
	CityRepository cityRepository;
	@Autowired
	ModelMapperService modelMapperService;

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		Car car = this.carRepository.getById(createRentalRequest.getCarId());

		car.setState(3);
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);

		LocalDate date = createRentalRequest.getPickupDate();
		rental.setPickupDate(date);
		LocalDate returnvalue = date.plusDays(createRentalRequest.getTotalDays());
		rental.setReturnedDate(returnvalue);

		rental.setTotalPrice(createRentalRequest.getTotalDays() * car.getDailyPrice());// hesaplattırılacak

		if (!rental.getPickUpCity().equals(rental.getReturnCity())) {

			rental.setTotalPrice(rental.getTotalPrice() + 750);
		}

		this.rentalRepository.save(rental);
		return new SuccessResult("ADDED.RENTAL");
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Car car = this.carRepository.getById(updateRentalRequest.getCarId());

		car.setState(3);
		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);

		LocalDate date = updateRentalRequest.getPickupDate();
		rental.setPickupDate(date);
		LocalDate returnvalue = date.plusDays(updateRentalRequest.getTotalDays());
		rental.setReturnedDate(returnvalue);

		rental.setTotalPrice(updateRentalRequest.getTotalDays() * car.getDailyPrice());// hesaplattırılacak

		if (!rental.getPickUpCity().equals(rental.getReturnCity())) {

			rental.setTotalPrice(rental.getTotalPrice() + 750);
		}

		this.rentalRepository.save(rental);
		return new SuccessResult("UPDATED.RENTAL");
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		Rental rental = this.modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
		this.rentalRepository.delete(rental);
		return new SuccessResult("DELETED.RENTAL");
	}

	@Override
	public DataResult<ReadRentalResponse> getById(int id) {
		Rental rental = this.rentalRepository.getById(id);
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

}
