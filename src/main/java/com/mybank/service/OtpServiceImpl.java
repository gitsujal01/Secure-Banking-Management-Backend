package com.mybank.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mybank.entity.OTP;
import com.mybank.repository.OTPRepository;

@Service
public class OtpServiceImpl implements OtpService{

	private OTPRepository otpRepo;
	private JavaMailSender mailSender;
	
	
	
	public OtpServiceImpl(OTPRepository otpRepo, JavaMailSender mailSender) {
		super();
		this.otpRepo = otpRepo;
		this.mailSender = mailSender;
	}

	@Override
	public void sendOtp(String email) {
		// TODO Auto-generated method stub
	    String otp = String.valueOf(100000+new Random().nextInt(900000));	
	    OTP otpEntity = new OTP();
	    otpEntity.setEmail(email);
	    otpEntity.setOtp(otp);
	    otpEntity.setExpiryTime(
              LocalDateTime.now().plusMinutes(5)
	    		);
	    otpRepo.save(otpEntity);
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setTo(email);
	    message.setSubject("OTP Verification");
	    message.setText(
	    		"Your OTP is : "+otp);
	    mailSender.send(message);
	}
	@Override
	public boolean verifyOtp(String email, String otp) {
		// TODO Auto-generated method stub
        OTP otpEntiy = otpRepo.findByEmail(email).orElseThrow(()->new RuntimeException("OTP not found"));
        if(LocalDateTime.now().isAfter(otpEntiy.getExpiryTime()))
        {
        	return false;
        }
		return otpEntiy.getOtp().equals(otp);
	}

}
