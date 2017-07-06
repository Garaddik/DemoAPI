package com.planfisheye.sync.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.planfisheye.common.Exception.PlanFishEyeDatabaseException;
import com.planfisheye.sync.model.Contacts;

@Component
public interface PlanFishEyeSyncDao 
{
	List<Contacts> getContactsList() throws PlanFishEyeDatabaseException;
}
