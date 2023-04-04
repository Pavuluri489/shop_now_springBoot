package com.shopNow.Product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class TechnicalDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long TechnicalDetailsId;
	@OneToOne
	private BasicSpecifications basicSpecifications;
	@OneToOne
	private MobileSpecifications mobileSpecifications;
	@OneToOne
	private LaptopSpecifications laptopSpecifications;

}
