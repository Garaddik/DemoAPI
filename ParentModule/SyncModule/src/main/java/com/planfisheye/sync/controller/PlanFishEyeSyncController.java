package com.planfisheye.sync.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.planfisheye.sync.AppConstants;
import com.planfisheye.sync.exception.PlanFishEyeSyncDatabaseException;
import com.planfisheye.sync.model.Contacts;
import com.planfisheye.sync.service.PlanFishEyeSyncService;


@Controller
public class PlanFishEyeSyncController 
{
	private static final Logger logger = LoggerFactory.getLogger(PlanFishEyeSyncController.class);
	@Autowired
	PlanFishEyeSyncService service;
	
	@RequestMapping("/contacts")
	public ModelAndView loginPage(Model model, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) {
		ModelAndView view = null;
		try {
			List<Contacts> contactsList = service.getContacts();
			System.out.println("controller after service");
			if (null != contactsList) {				
				session.setAttribute("contacts", contactsList);
				view = new ModelAndView("contacts", "contacts", contactsList);
			} else {
				model.addAttribute("Error", "No Contacts");
				view = new ModelAndView("contacts", "contacts", new ArrayList<Contacts>());
			}
		}catch (PlanFishEyeSyncDatabaseException e2) {
			model.addAttribute("Error", e2.getMessage());
			logger.error(AppConstants.ERRORMETHOD + " Contact List Error:"+e2.getMessage());
			view = new ModelAndView("contacts", "contacts", new ArrayList<Contacts>());
		}catch (Exception e) {
			model.addAttribute("Error",e.getMessage());
			logger.error(AppConstants.ERRORMETHOD + " Contact List Error:"+e.getMessage());
			view = new ModelAndView("contacts", "contacts", new ArrayList<Contacts>());
		 }
		logger.debug(AppConstants.ENDMETHOD + "login");
		return view;
	}
}
