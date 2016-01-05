package com.nexmo.hm.utils;

import java.util.HashSet;
import java.util.Set;

import com.nexmo.hm.domain.Game;
import com.nexmo.hm.domain.User;
import com.nexmo.hm.to.GameTO;
import com.nexmo.hm.to.UserTO;

/**
 * @author Sunny Ghosh
 *
 */

public class DomainTransferObjectUtil {

	public static User transferObjectToDomainConverter(UserTO userTO) {
		User user = new User();
		Set<Game> gameSet = new HashSet<>();
		if (userTO != null) {
			user.setUserId(userTO.getUserId());
			user.setName(userTO.getName());
			if (userTO != null && userTO.getGames() != null && !userTO.getGames().isEmpty()) {
				for (GameTO gameTO : userTO.getGames()) {
					Game game = new Game();
					game.setGameId(gameTO.getGameId());
					game.setGameStatus(gameTO.getGameStatus());
					game.setGuesses(gameTO.getGuesses());
					game.setMisses(gameTO.getMisses());
					game.setNumberOfTry(gameTO.getNumberOfTry());
					game.setWord(gameTO.getWord());
					game.setRemainingChars(gameTO.getRemainingChars());
					game.setUser(user);
					gameSet.add(game);
					user.setGames(gameSet);
				}
			}
		}
		return user;
	}

	public static UserTO domainToTransferObjectConverter(User user) {
		UserTO userTO = new UserTO();
		Set<GameTO> gameSetModel = new HashSet<>();
		if (user != null) {
			userTO.setUserId(user.getUserId());
			userTO.setName(user.getName());
			if (user != null && user.getGames() != null && !user.getGames().isEmpty()) {
				for (Game game : user.getGames()) {
					GameTO gameTO = new GameTO();
					gameTO.setGameId(game.getGameId());
					gameTO.setGameStatus(game.getGameStatus());
					gameTO.setGuesses(game.getGuesses());
					gameTO.setMisses(game.getMisses());
					gameTO.setNumberOfTry(game.getNumberOfTry());
					gameTO.setWord(game.getWord());
					gameTO.setRemainingChars(game.getRemainingChars().toUpperCase());
					gameTO.setUser(userTO);
					game.setUser(user);
					gameSetModel.add(gameTO);
					userTO.setGames(gameSetModel);
				}

			}
		}
		return userTO;
	}
}
