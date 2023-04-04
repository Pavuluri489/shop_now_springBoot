package com.shopNow.Identity.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table
@Data
@RequiredArgsConstructor

public class ProfileImage {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long imageId;
	private String imageName;
	private String contentType;
	@Lob
	@Column(length=(int)4294967295l)
   	private byte[] picBytes;
	
}
