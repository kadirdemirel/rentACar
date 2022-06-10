package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.MaintenanceService;
import com.kodlamaio.rentACar.business.request.maintenance.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.request.maintenance.DeleteMaintenanceRequest;
import com.kodlamaio.rentACar.business.request.maintenance.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.business.response.maintenances.ReadMaintenanceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.MaintenanceRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.Maintenance;

@Service
public class MaintenanceManager implements MaintenanceService {
	@Autowired
	MaintenanceRepository maintenanceRepository;
	@Autowired
	CarRepository carRepository;

	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest) {
		Maintenance maintenance = new Maintenance();
		Car car = this.carRepository.getById(createMaintenanceRequest.getCarId());
		car.setId(createMaintenanceRequest.getCarId());
		car.setState(2);
		maintenance.setCar(car);
		maintenance.setDateSent(createMaintenanceRequest.getSentDate());
		maintenance.setDateReturned(createMaintenanceRequest.getReturnedDate());
		this.maintenanceRepository.save(maintenance);
		return new SuccessResult("ADDED.MAİNTENANCE");
	}

	@Override
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
		Maintenance maintenance = new Maintenance();
		maintenance.setId(deleteMaintenanceRequest.getId());
		this.maintenanceRepository.delete(maintenance);
		return new SuccessResult("DELETED.MAİNTENANCE");
	}

	@Override
	public Result update(UpdateMaintenanceRequest updateMaintenanceRequest) {
		Maintenance maintenance = new Maintenance();
		Car car = this.carRepository.getById(updateMaintenanceRequest.getCarId());
		car.setId(updateMaintenanceRequest.getCarId());
		//car.setState(2);
		
		LocalDate lt = LocalDate.now();
		
		if(lt.equals(updateMaintenanceRequest.getDateReturned())) {
			car.setState(1);
		}
		maintenance.setId(updateMaintenanceRequest.getId());
		maintenance.setDateSent(updateMaintenanceRequest.getDateSent());
		maintenance.setDateReturned(updateMaintenanceRequest.getDateReturned());
		maintenance.setCar(car);
		this.maintenanceRepository.save(maintenance);

		return new SuccessResult("UPDATED.MAİNTENANCE");
	}

	@Override
	public DataResult<Maintenance> getById(ReadMaintenanceResponse readMaintenanceResponse) {
		return new SuccessDataResult<Maintenance>(this.maintenanceRepository.getById(readMaintenanceResponse.getId()));
	}

	@Override
	public DataResult<List<Maintenance>> getAll(ReadMaintenanceResponse readMaintenanceResponse) {
		return new SuccessDataResult<List<Maintenance>>(this.maintenanceRepository.findAll());
	}

}
