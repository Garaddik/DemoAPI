package com.planfisheye.sync.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.planfisheye.common.Exception.PlanFishEyeDatabaseException;
import com.planfisheye.common.utility.AppConstants;
import com.planfisheye.sync.model.Contacts;
import com.planfisheye.sync.service.PlanFishEyeSyncService;


@RestController
@RequestMapping("/planfisheyesync")
public class PlanFishEyeSyncController 
{
	private static final Logger logger = LoggerFactory.getLogger(PlanFishEyeSyncController.class);
	@Autowired
	PlanFishEyeSyncService service;
	
	@RequestMapping(method=RequestMethod.GET, value="/sync/contactsList",
			produces = "application/json")
	public List<Contacts> getContactsList() {
		List<Contacts> contactsList=null;
		try {
			contactsList = service.getContactsList();
			System.out.println("controller after service");
			if (null == contactsList) {	
				contactsList=new ArrayList<Contacts>();
			}
		}catch (PlanFishEyeDatabaseException e2) {
			logger.error("getContactsList Error:"+e2.getMessage());
		}catch (Exception e) {
			logger.error("getContactsList Error:"+e.getMessage());
		 }
		logger.debug(AppConstants.ENDMETHOD + "login");
		return contactsList;
	}
}
