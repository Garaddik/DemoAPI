package com.planfisheye.sync.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planfisheye.common.Exception.PlanFishEyeDatabaseException;
import com.planfisheye.common.utility.AppConstants;
import com.planfisheye.sync.dao.PlanFishEyeSyncDao;
import com.planfisheye.sync.model.Contacts;
@Service
public class PlanFishEyeSyncServiceImpl implements PlanFishEyeSyncService
{
	private static final Logger logger = LoggerFactory.getLogger(PlanFishEyeSyncServiceImpl.class);
	@Autowired
	PlanFishEyeSyncDao dao;
	
	@Override
	public List<Contacts> getContactsList() throws PlanFishEyeDatabaseException {
		logger.info(AppConstants.STARTMETHOD + "login");
		List<Contacts> contactsList = dao.getContactsList();
		System.out.println("service after dao");
		logger.info(AppConstants.ENDMETHOD + "login");
		return contactsList;
	}

}
