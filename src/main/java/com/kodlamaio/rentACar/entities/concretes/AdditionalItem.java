package com.kodlamaio.rentACar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "additionals"})
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "additional_items")
public class AdditionalItem {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "daily_price")
	private double dailyPrice;
	
	@OneToMany(mappedBy = "additionalItem")
	List<Additional> additionals;

}
