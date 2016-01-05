package com.nexmo.hm.service;

import com.nexmo.hm.to.UserTO;

/**
 * @author Sunny Ghosh
 *
 */

public interface UserService {
	public UserTO getUser(String user);
	public UserTO createUser(String user);
	public void updateUser(UserTO user);
	public void updateUserForLogout(UserTO user);
}
