package com.kodlamaio.rentACar.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordered_additional_items")
public class OrderedAdditionalItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "additional_item_id")
	private AdditionalItem additionalItem;

	@ManyToOne
	@JoinColumn(name = "rental_id")
	private Rental rental;

	@Column(name = "total_price")
	private double totalPrice;

	@Column(name = "total_days")
	private int totalDays;

	@Column(name = "pick_up_date")
	private LocalDate pickUpDate;

	@Column(name = "return_date")
	private LocalDate returnDate;

}
