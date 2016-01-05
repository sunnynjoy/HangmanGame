package com.nexmo.hm.utils;

public enum SessionKeyEnum {
	GAME("game"), USER("user");
	
	String attributeName;
	
	SessionKeyEnum(String attributeName){
		this.attributeName = attributeName;
	}
}
