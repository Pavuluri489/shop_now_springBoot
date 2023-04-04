package com.shopNow.Product.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopNow.Product.entity.AboutItem;
import com.shopNow.Product.entity.AdditionalDetails;
import com.shopNow.Product.entity.BasicSpecifications;
import com.shopNow.Product.entity.Category;
import com.shopNow.Product.entity.LaptopSpecifications;
import com.shopNow.Product.entity.ManufaturerInfo;
import com.shopNow.Product.entity.MobileSpecifications;
import com.shopNow.Product.entity.Product;
import com.shopNow.Product.entity.SubCategory;
import com.shopNow.Product.entity.TechnicalDetails;
import com.shopNow.Product.repository.AboutRepo;
import com.shopNow.Product.repository.AdditionalRepo;
import com.shopNow.Product.repository.BasicTechRepo;
import com.shopNow.Product.repository.CategoryRepo;
import com.shopNow.Product.repository.LaptopTechRepo;
import com.shopNow.Product.repository.ManfacturerInfoRepo;
import com.shopNow.Product.repository.MobileTechRepo;
import com.shopNow.Product.repository.ProductRepo;
import com.shopNow.Product.repository.SubCategoryRepo;
import com.shopNow.Product.repository.TechnicalRepo;
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private SubCategoryRepo subCategoryRepo;
	@Autowired
	private AboutRepo aboutRepo;
	@Autowired
	private TechnicalRepo technicalRepo;
	@Autowired
	private BasicTechRepo basicTechRepo;
	@Autowired
	private MobileTechRepo mobileTechRepo;
	@Autowired
	private LaptopTechRepo laptopTechRepo;
	@Autowired
	private ManfacturerInfoRepo manufacturerInfoRepo;
	@Autowired
	private AdditionalRepo additionalRepo;
	
	@Override
	public Category addCategory(Category category) {
		System.out.println(category.getCategoryImage().getProductImageId());
		
		return categoryRepo.save(category);
	}
	
	@Override
	public SubCategory addSubCategory(SubCategory subCategory, String categoryName) {
		Category category=categoryRepo.findByCategoryName(categoryName);
		subCategory.setCategory(category);
		return subCategoryRepo.save(subCategory);
	}
	
	@Override
	public Product addProduct(Product product,String subCategoryName) {
		SubCategory subCategory=subCategoryRepo.findBySubCategoryName(subCategoryName);
		product.setSubCategory(subCategory); 
		AboutItem aboutItem=aboutRepo.save(product.getAboutItem());
		product.setAboutItem(aboutItem);
		return addProductDetails(product);
	}
	
	@Override
	public Product addProductDetails(Product product) {
		if(product.getManufaturerInfo()!=null) {
			ManufaturerInfo manufacturerInfo= manufacturerInfoRepo.save(product.getManufaturerInfo());
			product.setManufaturerInfo(manufacturerInfo);
		}
		if(product.getAdditionalDetails()!=null) {
			AdditionalDetails additionalDetails= additionalRepo.save(product.getAdditionalDetails());
			product.setAdditionalDetails(additionalDetails);
		}
		if(product.getTechnicalDetails()!=null) {
		TechnicalDetails technicalDetails=new TechnicalDetails();
		if(product.getTechnicalDetails().getBasicSpecifications()!=null) {
			BasicSpecifications basicSpecs=basicTechRepo.save(product.getTechnicalDetails().getBasicSpecifications());
			technicalDetails.setBasicSpecifications(basicSpecs);
		}
		if(product.getTechnicalDetails().getMobileSpecifications()!=null) {
			MobileSpecifications mobileSpecs=mobileTechRepo.save(product.getTechnicalDetails().getMobileSpecifications());
			technicalDetails.setMobileSpecifications(mobileSpecs);
		}
		if(product.getTechnicalDetails().getLaptopSpecifications()!=null) {
			LaptopSpecifications laptopSpecs=laptopTechRepo.save(product.getTechnicalDetails().getLaptopSpecifications());
			technicalDetails.setLaptopSpecifications(laptopSpecs);
		}
		technicalDetails=technicalRepo.save(technicalDetails);
		product.setTechnicalDetails(technicalDetails);
		}
		return productRepo.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public List<Product> getProductsByCategory(String categoryName) {
		return productRepo.findBySubCategory_category_categoryName(categoryName);
	}

	@Override
	public List<Product> getProductsBySubCategory(String subCategoryName) {
		return productRepo.findBySubCategory_subCategoryName(subCategoryName);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepo.findAll();
	}

	@Override
	public List<SubCategory> getAllSubCategoriesByCategories(String category) {
		return subCategoryRepo.findByCategory_categoryName(category);
	}

	@Override
	public Product getProductById(Long id) {
		return productRepo.findById(id).get();
	}

	@Override
	public void deleteProductById(Long id) {
		productRepo.deleteById(id);
	}

}