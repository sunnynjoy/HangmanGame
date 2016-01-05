package com.nexmo.hm.to;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * @author Sunny Ghosh
 *
 */

public class UserTO implements Serializable{

	/**
	 * added a default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private int userId;
	private String 	name;
	private Set<GameTO> games;

	public UserTO(){
		super();
	};
	
	public UserTO(String name) {
		super();
	    this.name 	= name;
	}
	
	public String getName() {
	    return this.name;
	}
	
	public void setName(String name) {
	    this.name = name;
	}	
	
	@JsonBackReference
	public Set<GameTO> getGames() {
	    return games;
	}

	public void setGames(Set<GameTO> games) {
		this.games = games;
	}	
	
	public int getUserId() {
	    return this.userId;
	}
	
	public void setUserId(int userId) {
	    this.userId = userId;
	}
}
