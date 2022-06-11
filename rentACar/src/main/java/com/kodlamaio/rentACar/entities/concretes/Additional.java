package com.kodlamaio.rentACar.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "additionals"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "additional")
public class Additional {
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

}
