package com.planfisheye.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.planfisheye.AppConstants;
import com.planfisheye.dao.PlanFishEyeDao;
import com.planfisheye.dao.exception.PlanFishEyeDatabaseException;
import com.planfisheye.model.User;

@Service
public class PlanFishEyeServiceImpl implements PlanFishEyeService
{
	@Autowired
	PlanFishEyeDao dao;
	
	private static final Logger logger = LoggerFactory.getLogger(PlanFishEyeServiceImpl.class);
	
	@Override
	public User getUserByUserName(	String username) throws PlanFishEyeDatabaseException {
		System.out.println("service");
		logger.info(AppConstants.STARTMETHOD + "login");
		User userResponse = dao.getUserByUserName(username);
		System.out.println("service after dao");

		if (null != userResponse) 
		{
			return userResponse;

		} else {
			return null;
		}
		
	}


}
