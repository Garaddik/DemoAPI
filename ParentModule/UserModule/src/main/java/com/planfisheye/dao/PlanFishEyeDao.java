package com.planfisheye.dao;

import org.springframework.stereotype.Component;

import com.planfisheye.common.Exception.PlanFishEyeDatabaseException;
import com.planfisheye.model.User;

@Component
public interface PlanFishEyeDao 
{
	User getUserByUserName(String username) throws PlanFishEyeDatabaseException;
}
