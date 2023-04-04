package com.shopNow.Identity.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopNow.Identity.Entity.Address;
import com.shopNow.Identity.Entity.Customer;
import com.shopNow.Identity.Entity.ProfileImage;
import com.shopNow.Identity.Entity.Roles;
import com.shopNow.Identity.dto.ForgotPasswordDto;
import com.shopNow.Identity.dto.ResetPwdDto;
import com.shopNow.Identity.dto.UpdatePasswordDto;
import com.shopNow.Identity.repository.AddressRepository;
import com.shopNow.Identity.repository.CustomerRepository;
import com.shopNow.Identity.repository.ImageRepository;
import com.shopNow.Identity.repository.RoleRepository;
import com.shopNow.Identity.security.JwtRequestFilter;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private ImageRepository imageRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public Customer register(Customer customer) {
		Roles role=roleRepo.findByRoleCode("196527");
		customer.getRoles().add(role);
		System.out.println(role);
	
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		if(customer.getAddress()!=null)
        addressRepo.save(customer.getAddress());
		return customerRepo.save(customer);
	}

	@Override
	public Customer profileImage(ProfileImage profileImage) {
		Customer customer=customerRepo.findByUserName(JwtRequestFilter.CURRENT_USER);
		if(customer.getProfileImage()!=null)
			profileImage.setImageId(customer.getProfileImage().getImageId());
		ProfileImage image=imageRepo.save(profileImage);
		customer.setProfileImage(image);
		
		return customerRepo.save(customer);
	}

	@Override
	public Customer defaultCustomer(Roles adminRole) {
		Customer customer=customerRepo.findByUserName("admin925");
		Customer admin= new Customer();
		
		if(customer==null) {
			Set<Roles> role= new HashSet<>();
			role.add(adminRole);
			admin.setRoles(role);
			admin.setFirstName("Venu");
			admin.setLastname("Pavuluri");
			admin.setPhNo("7794998135");
			admin.setEmail("admin@shopNow.com");
			admin.setUserName("admin925");
			admin.setPassword(passwordEncoder.encode("Admin@92"));			customerRepo.save(admin);
		}
		return admin;
	}

	@Override
	public Customer verifyDetails(ForgotPasswordDto forgotPassword) {
		Customer customer=customerRepo.findByUserName(forgotPassword.getUserName());
		if(customer!=null) {
			String lname=forgotPassword.getLastName();
			String lname1=customer.getLastname();
			String email=forgotPassword.getEmail();
			String email1=customer.getEmail();
			String phno=forgotPassword.getPhNo();
			String phno1=customer.getPhNo();
			String securityQ=forgotPassword.getSecurityQ();
			String securityQ1=customer.getSecurityQ();
			String answer=forgotPassword.getAnswer();
			String answer1=customer.getAnswer();
			if(lname.equalsIgnoreCase(lname1)&& (
			  (email.equalsIgnoreCase(email1)&& phno.equals(phno1))||
			  (securityQ.equals(securityQ1)&& answer.equalsIgnoreCase(answer1))))
				return customer;
		}
		
		return null;
	}

	@Override
	public Customer changePassword(UpdatePasswordDto updatePwd) {
		Customer customer= customerRepo.findByUserName(updatePwd.getUserName());
		if(updatePwd.getPassword().equals(updatePwd.getConfirmPassword())) {
			customer.setPassword(passwordEncoder.encode(updatePwd.getPassword()));
		}
		return customer;
	}

	@Override
	public Customer resetPwd(ResetPwdDto resetPwd) {
		Customer customer=customerRepo.findByUserName(JwtRequestFilter.CURRENT_USER);
		if(this.passwordEncoder.matches(resetPwd.getOldPwd(), customer.getPassword())) {
			customer.setPassword(passwordEncoder.encode(resetPwd.getNewPwd()));
			return customerRepo.save(customer);
		}
		return null;
	}

	@Override
	public Customer updateProfile(Customer customer) {
	
		Address address=addressRepo.save(customer.getAddress());
		customer.setAddress(address);
		return customerRepo.save(customer);
	}

}
