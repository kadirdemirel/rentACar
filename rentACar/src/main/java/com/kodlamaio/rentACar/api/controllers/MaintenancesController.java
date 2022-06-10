package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.MaintenanceService;
import com.kodlamaio.rentACar.business.request.maintenance.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.request.maintenance.DeleteMaintenanceRequest;
import com.kodlamaio.rentACar.business.request.maintenance.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.business.response.maintenances.ReadMaintenanceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Maintenance;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenancesController {
	@Autowired
	MaintenanceService maintenanceService;

	@PostMapping("/add")
	public Result add(@RequestBody CreateMaintenanceRequest createMaintenanceRequest) {
		return this.maintenanceService.add(createMaintenanceRequest);

	}
	@PostMapping("/update")
	public Result update(@RequestBody UpdateMaintenanceRequest updateMaintenanceRequest) {
		return this.maintenanceService.update(updateMaintenanceRequest);

	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody DeleteMaintenanceRequest deleteMaintenanceRequest) {
		return this.maintenanceService.delete(deleteMaintenanceRequest);
	}

	@GetMapping("/getbyid")
	public DataResult<Maintenance> getById(@RequestBody ReadMaintenanceResponse readMaintenanceResponse) {
		return this.maintenanceService.getById(readMaintenanceResponse);
	}

	@GetMapping("/getall")
	public DataResult<List<Maintenance>> getAll(ReadMaintenanceResponse readMaintenanceResponse) {
		return this.maintenanceService.getAll(readMaintenanceResponse);
	}

}
