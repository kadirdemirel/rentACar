package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.request.rental.CreateRentalRequest;
import com.kodlamaio.rentACar.business.request.rental.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.request.rental.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.response.rentals.ReadRentalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.Rental;

import net.bytebuddy.asm.Advice.This;

@Service
public class RentalManager implements RentalService {

	@Autowired
	RentalRepository rentalRepository;
	@Autowired
	CarRepository carRepository;

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		Car car = this.carRepository.getById(createRentalRequest.getCarId());
		car.setState(3);
		Rental rental = new Rental();
		
		LocalDate date = createRentalRequest.getPickupDate();
		rental.setPickupDate(date);
		LocalDate returnvalue= date.plusDays(createRentalRequest.getTotalDays());
		rental.setReturnedDate(returnvalue);
		rental.setTotalDays(createRentalRequest.getTotalDays());// hesaplattırılacaklar
		rental.setTotalPrice(createRentalRequest.getTotalDays() * car.getDailyPrice());// hesaplattırılacak
		rental.setCar(car);

		this.rentalRepository.save(rental);
		return new SuccessResult("ADDED.RENTAL");
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Rental rental = new Rental();
		Car car = this.carRepository.getById(updateRentalRequest.getCarId());
		rental.setId(updateRentalRequest.getId());
		rental.setPickupDate(updateRentalRequest.getPickupDate());
		rental.setReturnedDate(updateRentalRequest.getReturnedDate());
		rental.setTotalDays(updateRentalRequest.getTotalDays());
		rental.setTotalPrice(updateRentalRequest.getTotalDays() * car.getDailyPrice());
		rental.setCar(car);

		this.rentalRepository.save(rental);

		return new SuccessResult("UPDATED.RENTAL");
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		Rental rental = new Rental();
		rental.setId(deleteRentalRequest.getId());
		this.rentalRepository.delete(rental);
		return new SuccessResult("DELETED.RENTAL");
	}

	@Override
	public DataResult<Rental> getById(ReadRentalResponse readRentalResponse) {
		return new SuccessDataResult<Rental>(this.rentalRepository.getById(readRentalResponse.getId()));
	}

	@Override
	public DataResult<List<Rental>> getAll() {
		return new SuccessDataResult<List<Rental>>(this.rentalRepository.findAll());
	}

}
