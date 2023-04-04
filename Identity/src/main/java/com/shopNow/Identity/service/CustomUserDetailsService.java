package com.shopNow.Identity.service;



import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shopNow.Identity.Entity.Customer;
import com.shopNow.Identity.repository.CustomerRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer=customerRepo.findByUserName(username);
		if(customer==null) {
			throw new UsernameNotFoundException(username+" is not found in the records!");
		}
		
		
            return new org.springframework.security.core.userdetails.User(
                    customer.getUserName(),
                    customer.getPassword(),
                    getAuthority(customer)
            );
        
    }

    private Set<SimpleGrantedAuthority> getAuthority(Customer customer) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        customer.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }
	

}
