package com.nexmo.hm.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexmo.hm.dao.UserDao;
import com.nexmo.hm.exception.BadRequestException;
import com.nexmo.hm.to.GameTO;
import com.nexmo.hm.to.UserTO;
import com.nexmo.hm.utils.GameStatusEnum;
import com.nexmo.hm.utils.RandomWordsGenerator;
import com.nexmo.hm.utils.WordsEnum;

/**
 * @author Sunny Ghosh
 *
 */

@Service("gameService")
public class GameServiceImp implements GameService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RandomWordsGenerator randomWordsGenerator;

	public GameServiceImp() {
		super();
	}

	@Override
	public UserTO newGame(UserTO userTO, String userName) throws BadRequestException {
		if (userTO != null && userTO.getName() != null && userName != null && userTO.getName().equals(userName)) {
			String randomWord = randomWordsGenerator.getRandonWord();
			GameTO game = new GameTO();
			Set<GameTO> gameSet = null;
			if (randomWord != null && userTO.getGames() == null || userTO.getGames().isEmpty()) {
				gameSet = getDefaultGameSet(game, userTO, randomWord);
			} else {
				gameSet = getDefaultGameSet(game, userTO, getRandomWordForNewUser(userTO, game, randomWord));
			}

			userTO.setGames(gameSet);
			userDao.update(userTO);
			userTO = userDao.getUser(userTO.getName());
			if (userTO != null && userTO.getGames() != null) {
				// remove game object which are already Won and Lost
				Iterator<GameTO> iterator = userTO.getGames().iterator();
				while (iterator.hasNext()) {
					GameTO gameEach = (GameTO) iterator.next();
					if (gameEach.getGameStatus() != null && gameEach.getGameStatus().equals(GameStatusEnum.WON)
							|| gameEach.getGameStatus().equals(GameStatusEnum.LOST)) {
						iterator.remove();
					} else {
						setwordToBeFilled(gameEach);
					}
				}
			}
			return userTO;
		}
		throw new BadRequestException();
	}

	@Override
	public GameTO checkGameAlgorithm(UserTO userTO, GameTO gameTO, char letterEach, int userId)
			throws BadRequestException {
		if (gameTO != null && userTO != null && userTO.getUserId() == userId && letterEach != ' ') {
			String letter = String.valueOf(letterEach).toUpperCase();
			gameTO.setContainsCharacter(false);
			if (gameTO.getUsedLetters() != null && gameTO.getUsedLetters().indexOf(letter) != -1) {
				gameTO.setContainsCharacter(true);
			}
			if (!gameTO.isContainsCharacter()) {
				gameTO.setUsedLetters(
						(gameTO.getUsedLetters() != null ? gameTO.getUsedLetters() + "," + letter : letter));
				gameTO.setNumberOfTry(
						gameTO.getNumberOfTry() > 0 ? gameTO.getNumberOfTry() - 1 : gameTO.getNumberOfTry());
				if (gameTO.getRemainingChars() != null && gameTO.getRemainingChars().toUpperCase().indexOf(letter) >= 0
						&& gameTO.getMisses() < 8 && gameTO.getNumberOfTry() <= 8 && gameTO.getNumberOfTry() >= 0) {
					setIndexToCharFill(gameTO, letterEach, WordsEnum.REMAINING_CHARS);
					gameTO.setGuesses(gameTO.getGuesses() + 1);
					setIndexToCharFill(gameTO, letterEach, WordsEnum.WORDS_TO_SHOW);
				} else {
					gameTO.setMisses(gameTO.getMisses() < 8 ? gameTO.getMisses() + 1 : gameTO.getMisses());
				}
				updateWinOrLose(gameTO, userTO);
			}
			return gameTO;
		}
		throw new BadRequestException();
	}

	
	private String getRandomWordForNewUser(UserTO user, GameTO game, String randomWord) {
		if (!user.getGames().contains(randomWord)) {
			return randomWord;
		} else {
			randomWord = randomWordsGenerator.getRandonWord();
			if (randomWord != null && !user.getGames().contains(randomWord)) {
				return randomWord;
			}
		}
		return null;
	}

	private Set<GameTO> getDefaultGameSet(GameTO game, UserTO user, String randomWord) {
		game.setWord(randomWord);
		game.setGameStatus(GameStatusEnum.NEW);
		game.setNumberOfTry(8);
		game.setRemainingChars(game.getWord().toUpperCase());
		game.setUser(user);
		Set<GameTO> gameSet = new HashSet<>();
		gameSet.add(game);
		return gameSet;
	}

	// set the initial word to _ and is not mapped to Domain object
	private void setwordToBeFilled(GameTO game) {
		if (game.getWord().length() > 0) {
			char[] chars = new char[game.getWord().length()];
			Arrays.fill(chars, '_');
			String WordToShow = new String(chars);
			game.setWordToShow(WordToShow);
		}
	}

	// logic to change the word which matches with the inserted character and
	// show it back to the user
	private void setIndexToCharFill(GameTO game, char letterEach, WordsEnum objectselectionenum) {
		int index = -1;
		StringBuilder word = null;
		if (objectselectionenum.equals(WordsEnum.WORDS_TO_SHOW)) {
			word = new StringBuilder(game.getWord().toUpperCase());
		} else {
			word = new StringBuilder(game.getRemainingChars().toUpperCase());
		}
		while ((index = word.toString().indexOf(letterEach, index + 1)) >= 0) {
			if (objectselectionenum.equals(WordsEnum.WORDS_TO_SHOW)) {
				StringBuilder replace = new StringBuilder(game.getWordToShow());
				replace.setCharAt(index, letterEach);
				game.setWordToShow(replace.toString().toUpperCase());
			} else {
				StringBuilder replace = new StringBuilder(word);
				replace.setCharAt(index, letterEach);
				game.setRemainingChars(replace.toString().replace(letterEach + "", ""));
			}
		}
	}

	private void updateWinOrLose(GameTO gameTO, UserTO userTO) {
		if (gameTO.getRemainingChars() != null && gameTO.getRemainingChars().length() >= 0
				&& gameTO.getNumberOfTry() == 0) {
			gameTO.setGameStatus(GameStatusEnum.LOST);
		} else if (gameTO.getRemainingChars() != null && gameTO.getRemainingChars().length() == 0
				&& gameTO.getNumberOfTry() >= 0) {
			gameTO.setGameStatus(GameStatusEnum.WON);
		}
		if (gameTO.getGameStatus().equals(GameStatusEnum.WON) || gameTO.getGameStatus().equals(GameStatusEnum.LOST)) {
			userDao.update(userTO);
		}
	}

}
