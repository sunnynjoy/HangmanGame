package com.nexmo.hm.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.nexmo.hm.dao.UserDao;
import com.nexmo.hm.to.GameTO;
import com.nexmo.hm.to.UserTO;
import com.nexmo.hm.utils.GameStatusEnum;

/**
 * @author Sunny Ghosh
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTestImp {

	@InjectMocks
	private UserService userService=new UserServiceImp();

	@Mock
	private UserDao userDao;
	
	@Mock
	private UserTO userToFirst;
	
	@InjectMocks
	private GameTO gameTO = new GameTO();

	String userName = "maa";

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
	}
	
	/**
	 * UserServiceImpl() constructor test.
	 * 
	 */
	@Test
	public void testUserServiceImpl() throws Exception {
		UserServiceImp result = new UserServiceImp();
		assertNotNull(result);
	}

	/**
	 * testGetUser() //returning the mock object while calling getUser and then checking its value with the userName using assertEquals
	 * 
	 */
	@Test
	public void testGetUser1() {
		when(userToFirst.getName()).thenReturn("maa");
		when(userDao.getUser(userName)).thenReturn(userToFirst);
		assertEquals(userService.getUser(userName).getName(),"maa");
	}
	
	/**
	 * testGetUser1()  checking if it returns null
	 */
	@Test
	public void testGetUser2() throws Exception{
		UserTO userTO = userService.getUser(userName);
		assertEquals(userTO,null);
	}

	/**
	 * testCreateUser1() , it returns the mock object by calling getUser and saving its value with the userName and then verifying it
	 */
	@Test
	public void testCreateUser1() {
		when(userDao.getUser(userName)).thenReturn(userToFirst);
		userService.createUser(userName);
		when(userToFirst.getName()).thenReturn("maa");
		when(userDao.getUser(userName)).thenReturn(userToFirst);
		assertEquals(userService.getUser(userName).getName(),"maa");
	}
	
	/**
	 * testCreateUser2() , null check
	 */
	@Test
	public void testCreateUser2() {
		when(userDao.getUser(userName)).thenReturn(null);
		userService.createUser(userName);
		when(userDao.getUser(userName)).thenReturn(userToFirst);
		assertEquals(userService.getUser(userName).getName(),null);
	}
	
	/**
	 * testUpdateUser1() , updating the user object with information provided and returning the game set
	 */
	@Test
	public void testUpdateUser1() {
		UserTO userToSecond=new UserTO("maa");
		when(userDao.getUser(userName)).thenReturn(userToFirst);
		userToSecond.setGames(getGame(gameTO, userToFirst));
		when(userToFirst.getName()).thenReturn("maa");
		assertEquals(userToFirst.getName(),userName);
		userService.updateUser(userToSecond);
		verify(userDao).update(userToSecond);
	}
	
	/**
	 * testUpdateUserForLogout() , updating the game status to finished and returning the user mapped to the user and then performing logout
	 */
	@Test
	public void testUpdateUserForLogout() {
		UserTO userToSecond=new UserTO("maa");
		when(userDao.getUser(userName)).thenReturn(userToFirst);
		userToSecond.setGames(getGame(gameTO, userToFirst));
		userToSecond = updateGameForUser(userToSecond);
		when(userToFirst.getName()).thenReturn("maa");
		assertEquals(userToFirst.getName(),userName);
		userService.updateUserForLogout(userToSecond);
		verify(userDao).update(userToSecond);
	}
	
	
	// Method to set a new new game and return its set back to the user object
	private Set<GameTO> getGame(GameTO gameTO,UserTO userTo){
		Set<GameTO> gameSet = new HashSet<>();
		gameTO.setGameId(1);
		gameTO.setGameStatus(GameStatusEnum.NEW);
		gameTO.setGuesses(2);
		gameTO.setMisses(4);
		gameTO.setNumberOfTry(4);
		gameTO.setWord("abcde");
		gameTO.setRemainingChars("");
		gameTO.setUser(userTo);
		gameSet.add(gameTO);
		userTo.setGames(gameSet);
		return gameSet;
	}
	
	
	// Method to update the current game object and return its set back to the user object
	private UserTO updateGameForUser(UserTO user) {
		for (GameTO game : user.getGames()) {
			game.setGameStatus(GameStatusEnum.WON);
			game.setUser(user);
			Set<GameTO> gameSet = new HashSet<>();
			gameSet.add(game);
			user.setGames(gameSet);
		}
		return user;
	}

}
