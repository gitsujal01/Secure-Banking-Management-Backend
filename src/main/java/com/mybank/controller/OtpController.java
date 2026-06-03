package com.mybank.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.dto.OTPRequest;
import com.mybank.dto.VerifyOtpDto;
import com.mybank.service.OtpService;

@RestController
public class OtpController {
	
	private final OtpService otpservice;

	public OtpController(OtpService otpservice) {
		super();
		this.otpservice = otpservice;
	}
	
	@PostMapping("/send")
	public String sendOtp(@RequestBody OTPRequest req)
    {
	        otpservice.sendOtp(req.getEmail());

	        return "OTP Sent";
	}
	@PostMapping("/verify")
	public String sendOtp(@RequestBody VerifyOtpDto req)
	{
		boolean isValid = otpservice.verifyOtp(req.getEmail(), req.getOtp());
		if(isValid)
		{
			return "OTP verified";
		}
		return "Invalid OTP";
	}

}
