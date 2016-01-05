package com.nexmo.hm.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import com.nexmo.hm.utils.RandomWordsGenerator;

/**
 * @author Sunny Ghosh
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class GameServiceImpTest {

	String userName = "maa";

	@InjectMocks
	private GameService gameService = new GameServiceImp();

	@Mock
	private UserTO userTOFirst;

	@Mock
	private GameTO gameTO;

	@Mock
	private UserDao userDao;

	@Mock
	private RandomWordsGenerator randomWordsGenerator;
	
	@Before
	public void setup() throws IOException {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * GameServiceImp() constructor test.
	 * 
	 */
	@Test
	public void testGameServiceImp() throws Exception {
		GameServiceImp result = new GameServiceImp();
		assertNotNull(result);
	}

	/**
	 * testNewGame() test for checking the whole service logic
	 * 
	 */
	@Test
	public void testNewGame1() throws Exception {
		UserTO userToSecond = new UserTO(userName);
		when(userTOFirst.getName()).thenReturn("maa");
		when(userDao.getUser(userName)).thenReturn(userToSecond);
		when(userTOFirst.getGames()).thenReturn(getGame(new GameTO(),userToSecond));
		when(randomWordsGenerator.getRandonWord()).thenReturn(new String("APPLE"));
		userToSecond = gameService.newGame(userTOFirst, "maa");
		assertEquals(userToSecond.getName(), userName);
	}

	/**
	 * testNewGame() test while passing the userName and UserTO object as null
	 * 
	 */
	@Test(expected = com.nexmo.hm.exception.BadRequestException.class)
	public void testNewGame2() throws Exception {
		UserTO userToSecond = null;
		String userName = "";
		userToSecond = gameService.newGame(userToSecond, userName);
		assertNotNull(userToSecond);
	}

	/**
	 * testNewGame() null check ,test while passing only the userName as null
	 * 
	 */
	@Test(expected = com.nexmo.hm.exception.BadRequestException.class)
	public void testNewGame3() throws Exception {
		UserTO userToSecond = new UserTO(userName);
		String userName = null;
		UserTO result = gameService.newGame(userToSecond, userName);
		assertNotNull(result);
	}

	/**
	 * testGameAlgorithm1()  test for checking the whole Logic for gameAlgorithm
	 * 
	 */
	@Test
	public void testGameAlgorithm1() {
		UserTO userToSecond = new UserTO("maa");
		userToSecond.setUserId(1);
		List<?> gameList = getGameList(userToSecond);
		when(userTOFirst.getGames()).thenReturn(getGame(new GameTO(),userToSecond));
		when(gameTO.getGameStatus()).thenReturn(GameStatusEnum.WON);
		gameTO = gameService.checkGameAlgorithm(userToSecond, (GameTO) gameList.get(0), 'A', 1);
		assertEquals(gameTO.getGameStatus(), GameStatusEnum.WON);
	}

	
	/**
	 * testGameAlgorithm2()  test after updating the userLetters and remainingChars for checking the Logic for scenarios like containsCharacter & Misses
	 * 
	 */
	@Test
	public void testGameAlgorithm2() {
		UserTO userToSecond = new UserTO("maa");
		userToSecond.setUserId(1);
		List<?> gameList = getGameList(userToSecond);
		when(gameTO.getUsedLetters()).thenReturn("BCDE");
		when(gameTO.getRemainingChars()).thenReturn("");
		gameTO = gameService.checkGameAlgorithm(userToSecond, (GameTO) gameList.get(0), 'A', 1);
		assertEquals(gameTO.getRemainingChars(), "");
		assertNotNull(gameTO);
	}

	/**
	 * testGameAlgorithm3()  test for checking the userId equality passed by the user
	 * 
	 */
	@Test(expected = com.nexmo.hm.exception.BadRequestException.class)
	public void testGameAlgorithm3() throws Exception {
		UserTO userToSecond = new UserTO("maa");
		userToSecond.setUserId(1);
		int userId = 2;
		List<?> gameList = getGameList(userToSecond);
		gameTO = gameService.checkGameAlgorithm(userToSecond, (GameTO) gameList.get(0), 'A', userId);
		assertEquals(gameTO.getRemainingChars(), "");
	}

	/**
	 * testGameAlgorithm4()  test for checking an empty letter passed by the user
	 * 
	 */
	@Test(expected = com.nexmo.hm.exception.BadRequestException.class)
	public void testCheckGameAlgorithm4() throws Exception {
		UserTO userToSecond = null;
		GameTO gameTO = new GameTO();
		char letterEach = '';
		int userId = 1;
		gameTO = gameService.checkGameAlgorithm(userToSecond, gameTO, letterEach, userId);
		// add additional test code here
		assertEquals(gameTO.getGameId(), userId);
	}

	// Method to set a new new game and return its set back to the user object
	private Set<GameTO> getGame(GameTO gameTO, UserTO userTo) {
		Set<GameTO> gameSet = new HashSet<>();
		gameTO.setGameId(1);
		gameTO.setGameStatus(GameStatusEnum.WON);
		gameTO.setGuesses(2);
		gameTO.setMisses(4);
		gameTO.setNumberOfTry(4);
		gameTO.setWord("abcde");
		gameTO.setUsedLetters("de");
		gameTO.setRemainingChars("abc");
		gameTO.setWordToShow("___de");
		gameTO.setUser(userTo);
		gameSet.add(gameTO);
		userTo.setGames(gameSet);
		return gameSet;
	}

	// updating the user object with information provided and returning the game set
	private List<?> getGameList(UserTO userToSecond) {
		userToSecond.setGames(getGame(gameTO, userTOFirst));
		when(userDao.getUser(userName)).thenReturn(userToSecond);
		return new ArrayList<>(userToSecond.getGames());
	}
}
