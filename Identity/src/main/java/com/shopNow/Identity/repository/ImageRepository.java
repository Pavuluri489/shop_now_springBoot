package com.shopNow.Identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopNow.Identity.Entity.ProfileImage;

public interface ImageRepository extends JpaRepository<ProfileImage, Long> {

}
