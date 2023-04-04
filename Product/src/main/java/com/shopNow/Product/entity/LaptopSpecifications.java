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
public class LaptopSpecifications {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long LaptopId;
	private String brand;
	private String manufacturer;	
	private String model;
	private String modelName;
	private String modelYear;
	private String productDimensions;
	private String batteries;
	private String itemModelNumber;
	private String memorySlotsAvailable;
	private String flashMemoryInstalledSize;
	private String ramMemoryInstalledSize;
	private String ramMemoryMaximumSize;
	private String ramMemoryTechnology;
	private String computerMemoryType;	
	private String hardDriveInterface;	
	private String hardDiskDescription;
	private String opticalDriveType;
	private String operatingSystem;
	private String processorBrand;	
	private String processorSpeed;
	private String ProcessorType;	
	private String processorCount;
	private String hardwarePlatform;
	private String hardwareInterface;
	private String graphicsCardDescription;
	private String graphicsCardRam;
	private String graphicsRAMType;
	private String graphicsCardInterface;	
	private String graphicsCoprocessor;	
	private String resolution;
	private String specialFeatures;	
	private String mountingHardware;	
	private String numberOfItems;
	private String softwareIncluded;	
	private String displayTechnology;	
	private String standingScreenDisplaySize;
	private String displayType;	
	private String ImageContrastRatio;	
	private String aspectRatio;
	private String screenResolution;
	private String audioOutputType;
	private String microphoneFormat;
	private String microphoneTechnology;	
    private String voltage;
	private String wattage;
	private String batteryAverageLife;
	private String batteriesIncluded;
	private String batteriesRequired;
	private String batteryCellComposition;	
	private String wirelessType;	
	private String refreshRate;	
	private String totalUsbPorts;
	private String keyboardDescription;
	private String connectorType;
	private String deviceInterfacePrimary;	
	private String formFactor;
	private String deviceType;	
	private String countryOfOrigin;	
	private String itemWeight;	

}
