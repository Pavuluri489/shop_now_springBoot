package com.shopNow.Identity.Entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="customer_info")
@Data
@RequiredArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long customerId;
	private String firstName;
	private String lastname;
	@Column(unique=true,nullable=false)
	private String phNo;
	@Column(unique=true,nullable=false)
	private String userName;
	private String password;
	@Column(unique=true,nullable=false)
	private String email;
	private String securityQ;
	private String answer;
	@OneToOne
	private Address address;
	@OneToOne
	private ProfileImage profileImage;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( name="customer_role_info",joinColumns= {@JoinColumn(name="customer_id")},
	inverseJoinColumns={@JoinColumn(name="role_id")})
	private Set<Roles> roles=new HashSet<>();
}
