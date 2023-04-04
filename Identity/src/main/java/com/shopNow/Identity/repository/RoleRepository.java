package com.shopNow.Identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopNow.Identity.Entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long> {

	Roles findByRoleCode(String string);

}
