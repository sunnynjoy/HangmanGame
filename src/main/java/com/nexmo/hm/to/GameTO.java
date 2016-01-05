package com.nexmo.hm.to;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nexmo.hm.utils.GameStatusEnum;

/**
 * @author Sunny Ghosh
 *
 */

public class GameTO implements Serializable {

	private static final long serialVersionUID = -6801965386969996581L;

	private int gameId;
	private GameStatusEnum gameStatus;
	private String word;
	private int numberOfTry;
	private int misses;
	private int guesses;
	private UserTO user;
	private String remainingChars;
	private String usedLetters;
	private String wordToShow;
	private boolean containsCharacter;

	@JsonBackReference
	public UserTO getUser() {
		return user;
	}

	public void setUser(UserTO user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public GameTO() {
		super();
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public GameStatusEnum getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatusEnum gameStatus) {
		this.gameStatus = gameStatus;
	}
	
	public int getNumberOfTry() {
		return numberOfTry;
	}

	public void setNumberOfTry(int numberOfTry) {
		this.numberOfTry = numberOfTry;
	}

	public int getMisses() {
		return misses;
	}

	public void setMisses(int misses) {
		this.misses = misses;
	}

	public int getGuesses() {
		return guesses;
	}

	public void setGuesses(int guesses) {
		this.guesses = guesses;
	}

	public String getRemainingChars() {
		return remainingChars;
	}

	public void setRemainingChars(String remainingChars) {
		this.remainingChars = remainingChars;
	}

	public String getUsedLetters() {
		return usedLetters;
	}

	public void setUsedLetters(String usedLetters) {
		this.usedLetters = usedLetters;
	}

	public String getWordToShow() {
		return wordToShow;
	}

	public void setWordToShow(String wordToShow) {
		this.wordToShow = wordToShow;
	}

	public boolean isContainsCharacter() {
		return containsCharacter;
	}

	public void setContainsCharacter(boolean containsCharacter) {
		this.containsCharacter = containsCharacter;
	}

}