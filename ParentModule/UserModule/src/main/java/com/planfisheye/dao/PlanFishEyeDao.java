package com.planfisheye.dao;

import org.springframework.stereotype.Component;

import com.planfisheye.dao.exception.PlanFishEyeDatabaseException;
import com.planfisheye.model.User;

@Component
public interface PlanFishEyeDao 
{
	User login(User user) throws PlanFishEyeDatabaseException;
}
