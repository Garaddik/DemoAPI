package com.planfisheye.dao;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mongodb.WriteResult;
import com.planfisheye.AppConstants;
import com.planfisheye.Utility;
import com.planfisheye.dao.exception.PlanFishEyeDatabaseException;
import com.planfisheye.model.User;


@Component
public class PlanFishEyeDaoImpl implements PlanFishEyeDao
{
	private static final Logger logger = LoggerFactory.getLogger(PlanFishEyeDaoImpl.class);
	@Override
	public User login(User user) throws PlanFishEyeDatabaseException {
		logger.info(AppConstants.STARTMETHOD + "login");
		Jongo jongo = Utility.getDBConnection();
		MongoCollection users = jongo.getCollection("Users");
		User userResponse = users.findOne("{email:#,password:#}", user.getEmail(), user.getPassword()).as(User.class);
		logger.info(AppConstants.ENDMETHOD + "login");
		return userResponse;
	}
	
	
}
