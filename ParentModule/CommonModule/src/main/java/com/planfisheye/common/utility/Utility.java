package com.planfisheye.common.utility;

import org.jongo.Jongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.planfisheye.common.Exception.PlanFishEyeDatabaseException;

public class Utility {

	private static final Logger logger = LoggerFactory.getLogger(Utility.class);

	static DB database = null;
	static String IP_ADDRESS = "";
	static MongoClient mongo;	

	public static Jongo getDBConnection(String db) throws PlanFishEyeDatabaseException {
		System.out.println("utility");
		Jongo jongo = null;
		logger.info(AppConstants.STARTMETHOD + " Jongo framework connections");
		try {
			mongo = new MongoClient("localhost:27017");
			database = mongo.getDB(db);
			logger.info("connecting to database");
			if (null != database) {
				jongo = new Jongo(database);
			}
		} catch (Exception e) {
			logger.info(AppConstants.ERRORMETHOD + " Jongo framework connections" + e.getMessage());
			throw new PlanFishEyeDatabaseException(503, e.getMessage());
		}
		logger.info(AppConstants.ENDMETHOD + " Jongo framework connections");
		return jongo;
	}
}
