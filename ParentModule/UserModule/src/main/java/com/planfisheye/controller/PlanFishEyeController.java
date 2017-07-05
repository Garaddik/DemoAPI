package com.planfisheye.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.planfisheye.AppConstants;
import com.planfisheye.dao.exception.PlanFishEyeDatabaseException;
import com.planfisheye.model.User;
import com.planfisheye.service.PlanFishEyeService;

@Controller
public class PlanFishEyeController 
{
	@Autowired
	PlanFishEyeService service;
	
	private static final Logger logger = LoggerFactory.getLogger(PlanFishEyeController.class);
	
	@RequestMapping(value = "/")
	public ModelAndView index(Model model, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) {
		System.out.println("here");
		ModelAndView view = new ModelAndView("login", "user", new User());
		view.setViewName("login");
		return view;
	}
	
	@RequestMapping("/loginpage")
	public ModelAndView loginPage(Model model, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) {
		ModelAndView view = new ModelAndView("login", "user", new User());
		view.setViewName("login");
		return view;
	}
	
	
	
	@RequestMapping("/logout")
	public ModelAndView logOut(ModelAndView view, @ModelAttribute("user") User user, Model model, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		session.removeAttribute("user");
		view.setViewName("/login");
		return view;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(ModelAndView view, @ModelAttribute("user") User user, Model model, HttpSession session,
			HttpServletResponse response, HttpServletRequest request, BindingResult result) {
		logger.debug(AppConstants.STARTMETHOD + "login");
		try {

			User userResponse = service.login(user);
			if (null != userResponse) {
				session.setAttribute("user", userResponse);
				view = new ModelAndView("dashboard", "user", user);
			} else {
				model.addAttribute("Error", "Invalid Credentials");
				view = new ModelAndView("login", "user", user);
			}
		}catch (PlanFishEyeDatabaseException e2) {
			model.addAttribute("Error", e2.getMessage());
			logger.error(AppConstants.ERRORMETHOD + " login. RetailerID:"+user.getEmail()+". Error:"+e2.getMessage());
			view = new ModelAndView("login", "user", user);
		}catch (Exception e) {
			model.addAttribute("Error",e.getMessage());
			view = new ModelAndView("login", "user", new User());
			logger.error(AppConstants.ERRORMETHOD + " login. Error:"+e.getMessage());
		 }
		logger.debug(AppConstants.ENDMETHOD + "login");
		return view;
	}
}
