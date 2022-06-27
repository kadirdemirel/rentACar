package com.kodlamaio.rentACar.business.abstracts;

import com.kodlamaio.rentACar.entities.concretes.IndividualCustomer;

public interface UserCheckService {
	boolean checkIfRealPerson(IndividualCustomer individualCustomer);
}
