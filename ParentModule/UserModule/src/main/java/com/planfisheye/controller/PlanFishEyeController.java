package com.planfisheye.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.planfisheye.AppConstants;
import com.planfisheye.dao.exception.PlanFishEyeDatabaseException;
import com.planfisheye.model.User;
import com.planfisheye.service.PlanFishEyeService;

@RestController
@RequestMapping("/planfisheyeuser")
public class PlanFishEyeController 
{
	@Autowired
	PlanFishEyeService service;
	
	private static final Logger logger = LoggerFactory.getLogger(PlanFishEyeController.class);
			
	@RequestMapping(method=RequestMethod.GET, value="/user/{username}",
			produces = "application/json")
	public User getUserByUserName(@PathVariable String username ) {
		logger.debug(AppConstants.STARTMETHOD + "login");
		User user=null;
		try {
			user = service.getUserByUserName(username);
			if(user==null)
				user= new User();
		} catch (PlanFishEyeDatabaseException e) {
			logger.error("getUserByUserName Error:"+e.getMessage());
			e.printStackTrace();
		}
		catch (Exception e) {
			logger.error("getUserByUserName Error:"+e.getMessage());
			e.printStackTrace();
		}
		
		return user;
	}
}
