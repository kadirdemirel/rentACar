package com.kodlamaio.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entities.concretes.Additional;

public interface AdditionalRepository extends JpaRepository<Additional, Integer> {
	List<Additional> getByRentalId(int rentalId);
}
