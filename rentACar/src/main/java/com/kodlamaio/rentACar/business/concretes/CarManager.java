package com.kodlamaio.rentACar.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.request.cars.CreateCarRequest;
import com.kodlamaio.rentACar.business.request.cars.DeleteCarRequest;
import com.kodlamaio.rentACar.business.request.cars.UpdateCarRequest;
import com.kodlamaio.rentACar.business.response.cars.ReadCarResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.entities.concretes.Brand;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.Color;

import net.bytebuddy.asm.Advice.This;

@Service
public class CarManager implements CarService {

	@Autowired
	private CarRepository carRepository;

	@Override
	public Result add(CreateCarRequest createCarRequest) {

		Color color = new Color();
		Brand brand = new Brand();
		Car car = new Car();

		car.setDescription(createCarRequest.getDescription());
		car.setDailyPrice(createCarRequest.getDailyPrice());
		brand.setId(createCarRequest.getBrandId());
		color.setId(createCarRequest.getBrandId());
		car.setBrand(brand);
		car.setColor(color);
//		if (carRepository.getByBrandId(createCarRequest.getBrandId()).size() < 5) {
//			this.carRepository.save(car);
//			return new SuccessResult("CAR.ADDED");
//		} else {
//			return new ErrorResult("Not");
//		}

		if (maxBrand(createCarRequest.getBrandId())) {
			this.carRepository.save(car);
			return new SuccessResult("CAR.ADDED");
		} else {
			return new ErrorResult("Not");
		}

	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		Color color = new Color();
		Brand brand = new Brand();
		Car car = new Car();

		car.setDescription(updateCarRequest.getDescription());
		car.setDailyPrice(updateCarRequest.getDailyPrice());
		brand.setId(updateCarRequest.getBrandId());
		color.setId(updateCarRequest.getBrandId());
		car.setBrand(brand);
		car.setColor(color);

		this.carRepository.save(car);
		return new SuccessResult("UPDATED.CAR");

	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		Car car = new Car();
		car.setId(deleteCarRequest.getId());
		this.carRepository.delete(car);
		return new SuccessResult("DELETED.CAR");

	}

	private boolean maxBrand(int brandId) {
		boolean exits = false;
		if (carRepository.getByBrandId(brandId).size() < 5) {
			exits = true;
		}
		return exits;
	}

	@Override
	public DataResult<List<Car>> getAll() {
		
		return new SuccessDataResult<List<Car>>(this.carRepository.findAll());
	}

	@Override
	public DataResult<Car> getById(ReadCarResponse readCarResponse) {
		
		return new SuccessDataResult<Car>(this.carRepository.getById(readCarResponse.getId()));
	}

}
//req:
//arabalar bakıma gönderilebilmelidir
//Maintenance : carId,dateSent,datereturned
//Arabalara plaka ve mevcut km bilgisi 
//Arabanın durum bilgisini araba tablosuna giriniz
//1)available
//2)Maintenance
