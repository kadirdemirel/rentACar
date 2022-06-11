package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
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
	@Autowired
	MaintenanceRepository maintenanceRepository;
	@Autowired
	CarRepository carRepository;
	@Autowired
	ModelMapperService modelMapperService;

	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest) {

		Car car = this.carRepository.getById(createMaintenanceRequest.getCarId());
		car.setId(createMaintenanceRequest.getCarId());
		car.setState(2);
		Maintenance maintenance = this.modelMapperService.forRequest().map(createMaintenanceRequest, Maintenance.class);

		this.maintenanceRepository.save(maintenance);
		return new SuccessResult("ADDED.MAİNTENANCE");
	}

	@Override
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
		Maintenance maintenance = this.modelMapperService.forRequest().map(deleteMaintenanceRequest, Maintenance.class);
		this.maintenanceRepository.delete(maintenance);
		return new SuccessResult("DELETED.MAİNTENANCE");
	}

	@Override
	public Result update(UpdateMaintenanceRequest updateMaintenanceRequest) {
		// Maintenance maintenance = new Maintenance();
		Car car = this.carRepository.getById(updateMaintenanceRequest.getCarId());
		car.setId(updateMaintenanceRequest.getCarId());
		// car.setState(2);

		LocalDate lt = LocalDate.now();

		if (lt.equals(updateMaintenanceRequest.getDateReturned())) {
			car.setState(1);
		}
//		maintenance.setId(updateMaintenanceRequest.getId());
//		maintenance.setDateSent(updateMaintenanceRequest.getDateSent());
//		maintenance.setDateReturned(updateMaintenanceRequest.getDateReturned());
//		maintenance.setCar(car);
		Maintenance maintenance = this.modelMapperService.forRequest().map(updateMaintenanceRequest, Maintenance.class);
		this.maintenanceRepository.save(maintenance);

		return new SuccessResult("UPDATED.MAİNTENANCE");
	}

	@Override
	public DataResult<ReadMaintenanceResponse> getById(int id) {
		Maintenance maintenance = this.maintenanceRepository.getById(id);
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

}
