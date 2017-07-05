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
	public User getUserByUserName(String username) throws PlanFishEyeDatabaseException {
		logger.info(AppConstants.STARTMETHOD + "login");
		System.out.println("dao");
		Jongo jongo = Utility.getDBConnection();
		System.out.println("dao after utility");
		MongoCollection users = jongo.getCollection("user");
		User userResponse = users.findOne("{username:#}",username).as(User.class);
		System.out.println(userResponse.getUserName());
		logger.info(AppConstants.ENDMETHOD + "login");
		return userResponse;
	}
}
