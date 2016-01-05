package com.nexmo.hm.management;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.nexmo.hm.utils.SessionKeyEnum;

public final class SessionManagement {
	
	@Autowired
	private HttpServletRequest request;

	public void setSession(SessionKeyEnum key,Object value) {
		request.getSession().setAttribute(key.toString(), value);
	}
	
	public Object getSession( SessionKeyEnum key) {
		return request.getSession().getAttribute(key.toString());
	}
	
	public void clearSession(){
		if (request.getSession() != null) {
		request.getSession().invalidate();
		}
		return;
	}
	
	public void clearGameSession(SessionKeyEnum key) {
		if (request.getSession() != null) {
			request.getSession().removeAttribute(key.toString());
		}
	}
}