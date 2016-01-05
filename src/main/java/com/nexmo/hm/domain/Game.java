package com.nexmo.hm.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nexmo.hm.utils.GameStatusEnum;

/**
 * @author Sunny Ghosh
 *
 */

@Entity
@Table(name = "game")
public class Game implements Serializable {

	private static final long serialVersionUID = -6801965386969996581L;
	private int gameId;
	private GameStatusEnum gameStatus;
	private String word;
	private int numberOfTry;
	private int misses;
	private int guesses;
	private User user;
	private String remainingChars;

	public Game() {
		super();
	}

	@Id
	@Column(name = "gameId", unique = true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name = "userId", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name="gameStatus")
	@Convert(converter = GameStatusEnumConvertor.class)
	public GameStatusEnum getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatusEnum gameStatus) {
		this.gameStatus = gameStatus;
	}
	
	@Column(name="word")
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Column(name="numberOfTry")
	public int getNumberOfTry() {
		return numberOfTry;
	}

	public void setNumberOfTry(int numberOfTry) {
		this.numberOfTry = numberOfTry;
	}

	@Column(name="misses")
	public int getMisses() {
		return misses;
	}

	public void setMisses(int misses) {
		this.misses = misses;
	}

	@Column(name="guesses")
	public int getGuesses() {
		return guesses;
	}

	public void setGuesses(int guesses) {
		this.guesses = guesses;
	}

	@Column(name="remainingChars")
	public String getRemainingChars() {
		return remainingChars;
	}

	public void setRemainingChars(String remainingChars) {
		this.remainingChars = remainingChars;
	}

}