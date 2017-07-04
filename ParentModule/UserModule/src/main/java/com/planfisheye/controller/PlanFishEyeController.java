package com.planfisheye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planfisheye.service.PlanFishEyeServiceImpl;

@RestController
public class PlanFishEyeController 
{
	@Autowired
	PlanFishEyeServiceImpl planFishEyeService;
	
	@RequestMapping()
	public String testConnection()
	{
		return planFishEyeService.testConnection();
	}
	
}
