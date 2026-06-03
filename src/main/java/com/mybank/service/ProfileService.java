package com.mybank.service;

import com.mybank.dto.ProfileResponse;

public interface ProfileService {
	
	ProfileResponse getProfile(String email);

}
