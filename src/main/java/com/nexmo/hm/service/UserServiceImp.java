package com.nexmo.hm.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexmo.hm.dao.UserDao;
import com.nexmo.hm.to.GameTO;
import com.nexmo.hm.to.UserTO;
import com.nexmo.hm.utils.GameStatusEnum;

/**
 * @author Sunny Ghosh
 *
 */

@Service("userService")
public class UserServiceImp implements UserService {

	@Autowired
	private UserDao userDao;

	public UserServiceImp() {
		super();
	}

	public UserTO getUser(String user) {
		return userDao.getUser(user);
	}

	@Override
	public UserTO createUser(String user) {
		UserTO userTO = null;
		if (user != null) {
			userTO = userDao.getUser(user);
			if (userTO == null) {
				userTO = new UserTO(user);
				userDao.save(userTO);
				userTO = userDao.getUser(user);
			}
		}
		return userTO;
	}

	@Override
	public void updateUser(UserTO user) {
		userDao.update(user);
	}
	
	@Override
	public void updateUserForLogout(UserTO user) {
		for (GameTO game : user.getGames()) {
			game.setGameStatus(game.getGameStatus().equals(GameStatusEnum.NEW) ? GameStatusEnum.LOST : GameStatusEnum.WON);
			game.setUser(user);
			Set<GameTO> gameSet = new HashSet<>();
			gameSet.add(game);
			user.setGames(gameSet);
		}
		userDao.update(user);
	}
}
