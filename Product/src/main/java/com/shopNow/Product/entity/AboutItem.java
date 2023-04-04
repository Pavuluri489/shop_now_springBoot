package com.shopNow.Product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class AboutItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long aboutItemId;
	private String description;

}
