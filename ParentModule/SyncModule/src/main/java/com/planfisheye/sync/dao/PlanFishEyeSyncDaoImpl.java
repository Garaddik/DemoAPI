package com.planfisheye.sync.dao;

import java.util.ArrayList;
import java.util.List;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.planfisheye.sync.AppConstants;
import com.planfisheye.sync.Utility;
import com.planfisheye.sync.exception.PlanFishEyeSyncDatabaseException;
import com.planfisheye.sync.model.Contacts;


@Component
public class PlanFishEyeSyncDaoImpl implements PlanFishEyeSyncDao{
	private static final Logger logger = LoggerFactory.getLogger(PlanFishEyeSyncDaoImpl.class);
	@Override
	public List<Contacts> getContactsList() throws PlanFishEyeSyncDatabaseException {
		logger.info(AppConstants.STARTMETHOD + "getContacts");
		Jongo jongo;
		List<Contacts> ContactsList = null;

		try {
			jongo = Utility.getDBConnection();
			MongoCollection Contactss = jongo.getCollection("contacts");
			MongoCursor<Contacts> masterList = null;
			masterList = Contactss.find().as(Contacts.class);
			ContactsList = new ArrayList<Contacts>();
			while (masterList.hasNext()) {
				ContactsList.add(masterList.next());
			}
		} catch (Exception e) {
			logger.error("getContacts error: " + e.getMessage());
			throw new PlanFishEyeSyncDatabaseException(503, e.getMessage());
		}
		logger.info(AppConstants.ENDMETHOD + "getContacts");
		return ContactsList;
	}
}
