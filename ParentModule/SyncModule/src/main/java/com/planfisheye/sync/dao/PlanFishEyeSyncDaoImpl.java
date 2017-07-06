package com.planfisheye.sync.dao;

import java.util.ArrayList;
import java.util.List;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.planfisheye.common.Exception.PlanFishEyeDatabaseException;
import com.planfisheye.common.utility.AppConstants;
import com.planfisheye.common.utility.Utility;
import com.planfisheye.sync.model.Contacts;


@Component
public class PlanFishEyeSyncDaoImpl implements PlanFishEyeSyncDao{
	private static final Logger logger = LoggerFactory.getLogger(PlanFishEyeSyncDaoImpl.class);
	@Override
	public List<Contacts> getContactsList() throws PlanFishEyeDatabaseException {
		logger.info(AppConstants.STARTMETHOD + "getContacts");
		Jongo jongo;
		List<Contacts> ContactsList = null;

		try {
			jongo = Utility.getDBConnection("fisheye");
			MongoCollection contacts = jongo.getCollection("contacts");
			MongoCursor<Contacts> masterList = null;
			System.out.println(contacts.find().as(Contacts.class).count());
			masterList = contacts.find().as(Contacts.class);
			ContactsList = new ArrayList<Contacts>();
			
			while (masterList.hasNext()) {
				Contacts c=masterList.next();
				ContactsList.add(c);
				System.out.println("<<<<<<<<<<<"+c.getName());
			}
		} catch (Exception e) {
			logger.error("getContacts error: " + e.getMessage());
			throw new PlanFishEyeDatabaseException(503, e.getMessage());
		}
		logger.info(AppConstants.ENDMETHOD + "getContacts");
		return ContactsList;
	}
}
