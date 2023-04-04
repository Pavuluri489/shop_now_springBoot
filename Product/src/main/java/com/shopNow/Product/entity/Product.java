package com.shopNow.Product.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name="product-details")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long productId;
	private String productName;
	private String productActualPrice;
	private String productDiscountedPrice;
	private String productOverview;
	@OneToOne
	private ManufaturerInfo manufaturerInfo;
	@OneToOne
	private AboutItem aboutItem;
	@OneToOne
	private TechnicalDetails technicalDetails;
	@OneToOne
	private AdditionalDetails additionalDetails;
	@ManyToOne(fetch=FetchType.EAGER)
	private SubCategory subCategory;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="product_images_info",
	joinColumns= {@JoinColumn(name="product_id")},
	inverseJoinColumns= {@JoinColumn(name="product_image_id")})
	private Set<ProductImage> productImages= new HashSet<>();
	
}
