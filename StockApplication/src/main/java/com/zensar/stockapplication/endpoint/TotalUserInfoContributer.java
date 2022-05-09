package com.zensar.stockapplication.endpoint;

import org.springframework.boot.actuate.info.Info.Builder;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.info.InfoContributor;

public class TotalUserInfoContributer implements InfoContributor{

	@Override
	public void contribute(Builder builder) {
		Map<String,Integer> userDetails=new HashMap<>();
		userDetails.put("active",100);
		userDetails.put("Inactive",10);
		builder.withDetail("users", userDetails);
		
	}
		
	}


