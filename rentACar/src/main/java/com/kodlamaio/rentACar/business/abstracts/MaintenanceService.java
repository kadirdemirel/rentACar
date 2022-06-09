package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.maintenance.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.request.maintenance.DeleteMaintenanceRequest;
import com.kodlamaio.rentACar.business.request.maintenance.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.business.response.maintenances.ReadMaintenanceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Maintenance;

public interface MaintenanceService {
	Result add(CreateMaintenanceRequest createMaintenanceRequest);

	Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest);

	Result update(UpdateMaintenanceRequest updateMaintenanceRequest);

	DataResult<Maintenance> getById(ReadMaintenanceResponse readMaintenanceResponse);

	DataResult<List<Maintenance>> getAll(ReadMaintenanceResponse readMaintenanceResponse);

}
