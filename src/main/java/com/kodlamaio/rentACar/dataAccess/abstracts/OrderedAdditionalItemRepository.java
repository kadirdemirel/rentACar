package com.kodlamaio.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entities.concretes.OrderedAdditionalItem;

public interface OrderedAdditionalItemRepository extends JpaRepository<OrderedAdditionalItem, Integer> {
	List<OrderedAdditionalItem> getByRentalId(int rentalId);
}
