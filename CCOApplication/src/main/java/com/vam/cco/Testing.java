package com.vam.cco;

import java.util.Calendar;
import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Testing {

	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawPassword = "admin@123";
		System.out.println("Admin Encoded Password: " + passwordEncoder.encode(rawPassword));
		
		rawPassword = "user@123";
		System.out.println("User Encoded Password: " + passwordEncoder.encode(rawPassword));
		
		rawPassword = "vam@123";
		System.out.println("Power User Encoded Password: " + passwordEncoder.encode(rawPassword));
	
		Date startDate = new Date();
		Calendar cal = Calendar.getInstance();
    	cal.setTime(startDate);
    	//cal.set(Calendar.YEAR, 2025);
    	cal.set(Calendar.MONTH, Calendar.DECEMBER);
    	cal.set(Calendar.DAY_OF_MONTH, 31);
    	Date dec31 = cal.getTime();
    	System.out.println("Date set to: " + dec31);
	}

}