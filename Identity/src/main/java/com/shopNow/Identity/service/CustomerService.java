package com.shopNow.Identity.service;

import com.shopNow.Identity.Entity.Customer;
import com.shopNow.Identity.Entity.ProfileImage;
import com.shopNow.Identity.Entity.Roles;
import com.shopNow.Identity.dto.ForgotPasswordDto;
import com.shopNow.Identity.dto.ResetPwdDto;
import com.shopNow.Identity.dto.UpdatePasswordDto;

public interface CustomerService {

	Customer register(Customer customer);

	Customer profileImage(ProfileImage profileImage);

	Customer defaultCustomer(Roles adminRole);

	Customer verifyDetails(ForgotPasswordDto forgotPassword);

	Customer changePassword(UpdatePasswordDto updatePwd);

	Customer resetPwd(ResetPwdDto resetPwd);

	Customer updateProfile(Customer customer);

}
