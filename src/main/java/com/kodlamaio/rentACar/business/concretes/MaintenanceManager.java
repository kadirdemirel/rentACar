package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.MaintenanceService;
import com.kodlamaio.rentACar.business.request.maintenance.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.request.maintenance.DeleteMaintenanceRequest;
import com.kodlamaio.rentACar.business.request.maintenance.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.business.response.maintenances.GetAllMaintenancesResponse;
import com.kodlamaio.rentACar.business.response.maintenances.ReadMaintenanceResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
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

	MaintenanceRepository maintenanceRepository;
	CarRepository carRepository;
	ModelMapperService modelMapperService;

	@Autowired
	public MaintenanceManager(MaintenanceRepository maintenanceRepository, CarRepository carRepository,
			ModelMapperService modelMapperService) {

		this.maintenanceRepository = maintenanceRepository;
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest) {

		Car car = checkIfCarExistsById(createMaintenanceRequest.getCarId());
		checkCarAvailable(car.getId());
		Maintenance maintenance = this.modelMapperService.forRequest().map(createMaintenanceRequest, Maintenance.class);

		this.maintenanceRepository.save(maintenance);
		return new SuccessResult("ADDED.MAİNTENANCE");

	}

	@Override
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
		checkIfMaintenanceExistsById(deleteMaintenanceRequest.getId());
		Maintenance maintenance = this.modelMapperService.forRequest().map(deleteMaintenanceRequest, Maintenance.class);
		this.maintenanceRepository.delete(maintenance);
		return new SuccessResult("DELETED.MAINTENANCE");
	}

	@Override
	public Result update(UpdateMaintenanceRequest updateMaintenanceRequest) {

		checkIfMaintenanceExistsById(updateMaintenanceRequest.getId());
		Car car = checkIfCarExistsById(updateMaintenanceRequest.getCarId());

		Maintenance maintenance = this.modelMapperService.forRequest().map(updateMaintenanceRequest, Maintenance.class);
		checkCarChangeId(updateMaintenanceRequest, car);
		this.maintenanceRepository.save(maintenance);
		return new SuccessResult("UPDATED.MAINTENANCE");
	}

	@Override
	public DataResult<ReadMaintenanceResponse> getById(int id) {
		Maintenance maintenance = checkIfMaintenanceExistsById(id);
		ReadMaintenanceResponse response = this.modelMapperService.forResponse().map(maintenance,
				ReadMaintenanceResponse.class);
		return new SuccessDataResult<ReadMaintenanceResponse>(response);
	}

	@Override
	public DataResult<List<GetAllMaintenancesResponse>> getAll() {
		List<Maintenance> maintenances = this.maintenanceRepository.findAll();
		List<GetAllMaintenancesResponse> response = maintenances.stream().map(maintenance -> this.modelMapperService
				.forResponse().map(maintenances, GetAllMaintenancesResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllMaintenancesResponse>>(response);
	}

	private Car checkIfCarExistsById(int carId) {

		Car currentCar = this.carRepository.findById(carId);

		if (currentCar == null) {
			throw new BusinessException("CAR.NOT.EXISTS");
		}

		return currentCar;

	}

	private void checkCarAvailable(int carId) {

		Car car = checkIfCarExistsById(carId);
		if (car.getState() == 1) {
			car.setState(2);
		} else {
			throw new BusinessException("NOT.CAR.AVAILABLE");
		}

	}

	private Maintenance checkIfMaintenanceExistsById(int maintenanceId) {
		Maintenance currentMaintenance;
		try {
			currentMaintenance = this.maintenanceRepository.findById(maintenanceId).get();

		} catch (Exception e) {
			throw new BusinessException("MAINTENANCE.NOT.EXISTS");
		}
		return currentMaintenance;

	}

	private void checkCarChangeId(UpdateMaintenanceRequest updateMaintenanceRequest, Car car) {
		Maintenance maintenance = this.maintenanceRepository.findById(updateMaintenanceRequest.getId()).get();
		Car tempCar = maintenance.getCar();

		if (updateMaintenanceRequest.getCarId() != tempCar.getId()) {
			tempCar.setState(1);
			checkCarAvailable(car.getId());

		}
	}

}
