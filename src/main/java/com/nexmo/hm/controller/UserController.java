package com.nexmo.hm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nexmo.hm.exception.NotAuthorizedException;
import com.nexmo.hm.management.SessionManagement;
import com.nexmo.hm.service.UserService;
import com.nexmo.hm.to.UserTO;
import com.nexmo.hm.utils.SessionKeyEnum;

/**
 * @author Sunny Ghosh
 *
 */

@Controller
class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SessionManagement sessionManagement;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView defaultPage() {
		UserTO user = new UserTO();
		return new ModelAndView("index", "user", user);
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public ModelAndView getUser(@ModelAttribute("userForm") UserTO userTO) {
		userTO = userService.createUser(userTO.getName());
		sessionManagement.setSession(SessionKeyEnum.USER, userTO);
		return new ModelAndView("index", "user", userTO);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout() {
		UserTO userTO = (UserTO) sessionManagement.getSession(SessionKeyEnum.USER);
		if(userTO == null){
			throw new NotAuthorizedException();
		}
		userService.updateUserForLogout(userTO);
		sessionManagement.clearSession();
		return new ModelAndView("index", "user", new UserTO());
	}

}
