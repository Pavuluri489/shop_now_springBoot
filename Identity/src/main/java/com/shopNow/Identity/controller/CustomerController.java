package com.shopNow.Identity.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shopNow.Identity.Entity.Customer;
import com.shopNow.Identity.Entity.ProfileImage;
import com.shopNow.Identity.Entity.Roles;
import com.shopNow.Identity.dto.ForgotPasswordDto;
import com.shopNow.Identity.dto.ResetPwdDto;
import com.shopNow.Identity.dto.UpdatePasswordDto;
import com.shopNow.Identity.repository.RoleRepository;
import com.shopNow.Identity.service.CustomerService;

import jakarta.annotation.PostConstruct;
@RequestMapping("auth")
@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@GetMapping("/home")
	public String home() {
		return "Welcome home";
	}

	@PostMapping("/register")
	public Customer register(@RequestBody Customer customer) {
		return customerService.register(customer);
	}
	
	@PostMapping("/profileImage")
	public Customer profileImage(@RequestPart("image")MultipartFile file) throws IOException, SerialException, SQLException {
	
		ProfileImage profileImage=new ProfileImage();
	    profileImage.setPicBytes(file.getBytes());
		profileImage.setImageName(file.getOriginalFilename());
		profileImage.setContentType(file.getContentType());
		
		
	    return customerService.profileImage(profileImage);
	}
	
	@PostConstruct
	public void addRoles() {
		Roles role=new Roles();
		role.setRoleId(1l);
		role.setRoleCode("196527");
		role.setRoleName("User");
		role.setRoleDescription("User can have only read previleges");		Roles userRole=roleRepo.save(role);
		System.out.println(userRole);
		role.setRoleId(2l);
		role.setRoleCode("834915");
		role.setRoleName("Admin");
		role.setRoleDescription("Admin can have both read and write previleges");
		Roles adminRole=roleRepo.save(role);
		System.out.println(adminRole);
		defaultCustomer(adminRole);
		
	}
	
	public Customer defaultCustomer(Roles adminRole) {
		return customerService.defaultCustomer(adminRole);
	}
	
	@PostMapping("/forgotPassword")
	public Customer verifyDetails(@RequestBody ForgotPasswordDto forgotPassword) {
		return customerService.verifyDetails(forgotPassword);
	}
	@PostMapping("/changePassword")
	public Customer changePassword(@RequestBody UpdatePasswordDto updatePwd) {
		return customerService.changePassword(updatePwd);
	}
	@PostMapping("/resetPassword")
	public Customer resetPwd(@RequestBody ResetPwdDto resetPwd) {
		return customerService.resetPwd(resetPwd);
	}
	
	@PostMapping("/updateProfile")
	public Customer updateProfile(@RequestBody Customer customer) {
		return customerService.updateProfile(customer);
	}
}
