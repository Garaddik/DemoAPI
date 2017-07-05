package com.planfisheye.sync;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.jongo.Jongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.planfisheye.sync.exception.PlanFishEyeSyncDatabaseException;

public class Utility {

	private static final Logger logger = LoggerFactory.getLogger(Utility.class);

	static DB database = null;
	static String IP_ADDRESS = "";
	static MongoClient mongo;
	static SimpleDateFormat formToISO = new SimpleDateFormat("M/d/yyyy hh:mm a");
	static SimpleDateFormat ISOToSTRING = new SimpleDateFormat("dd-MM-yyyy");
	
	static {
		try {
			mongo = new MongoClient("localhost:27017");
			database = mongo.getDB("fisheye");
			logger.info("connecting to database");
			formToISO.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		} catch (UnknownHostException e) {
			logger.info("error occurred while connecting to Database!");
			e.printStackTrace();
		}
	}

	public static Jongo getDBConnection() throws PlanFishEyeSyncDatabaseException {
		System.out.println("utility");
		Jongo jongo = null;
		logger.info(AppConstants.STARTMETHOD + " Jongo framework connections");
		try {
			if (null != database) {
				jongo = new Jongo(database);
			}
		} catch (Exception e) {
			logger.info(AppConstants.ERRORMETHOD + " Jongo framework connections" + e.getMessage());
			throw new PlanFishEyeSyncDatabaseException(503, e.getMessage());
		}
		logger.info(AppConstants.ENDMETHOD + " Jongo framework connections");
		return jongo;
	}
}
