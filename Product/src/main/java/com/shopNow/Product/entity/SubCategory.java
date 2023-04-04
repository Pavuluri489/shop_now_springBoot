package com.shopNow.Product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class SubCategory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long subCategoryId;
	private String subCategoryName;
	@OneToOne
	private ProductImage subCategoryImage;
	@ManyToOne
	private Category category;
}
