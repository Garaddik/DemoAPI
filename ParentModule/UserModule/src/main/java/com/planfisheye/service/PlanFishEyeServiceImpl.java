package com.planfisheye.service;

import org.springframework.stereotype.Component;

@Component
public class PlanFishEyeServiceImpl implements PlanFishEyeService
{
	@Override
	public String testConnection() {
		return "connection done";
	}

}
