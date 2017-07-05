package com.planfisheye.sync.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.planfisheye.sync.exception.PlanFishEyeSyncDatabaseException;
import com.planfisheye.sync.model.Contacts;
@Service
public interface PlanFishEyeSyncService 
{
	List<Contacts> getContactsList() throws PlanFishEyeSyncDatabaseException;
}
