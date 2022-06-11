package com.kodlamaio.rentACar.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "cars","cities","additionals" })
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rentals")
public class Rental {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "pickup_date")
	private LocalDate pickupDate;

	@Column(name = "returned_date")
	private LocalDate returnedDate;

	@Column(name = "total_days")
	private int totalDays;

	@Column(name = "total_price")
	private double totalPrice;

	@ManyToOne()	
	@JoinColumn(name = "car_id")
	private Car car;
	
	@ManyToOne
    @JoinColumn(name = "pick_up_city_id", referencedColumnName = "id")
    private City pickUpCity;

    @ManyToOne
    @JoinColumn(name = "return_city_id", referencedColumnName = "id")
    private City returnCity;
    
    @OneToMany(mappedBy = "rental")
	List<Additional> additionals;

}
