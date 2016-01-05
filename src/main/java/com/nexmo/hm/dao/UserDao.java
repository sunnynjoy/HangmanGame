package com.nexmo.hm.dao;

import com.nexmo.hm.to.UserTO;

/**
 * @author Sunny Ghosh
 *
 */

public interface UserDao {
	
	public UserTO getUser(String user);
	public void save(UserTO user);
	public void update(UserTO user);
}
