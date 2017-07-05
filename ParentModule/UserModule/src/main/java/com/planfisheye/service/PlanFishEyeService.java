package com.planfisheye.service;

import org.springframework.stereotype.Service;

import com.planfisheye.dao.exception.PlanFishEyeDatabaseException;
import com.planfisheye.model.User;


@Service
public interface PlanFishEyeService 
{
	User getUserByUserName(	String username) throws PlanFishEyeDatabaseException;
}
