package com.nexmo.hm.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Sunny Ghosh
 *
 */

@Entity
@Table(name = "user")
public class User implements Serializable{

	/**
	 * added a default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private int userId;
	private String 	name;
	private Set<Game> games;

	public User(){
		super();
	};
	
	@Id
	@Column(name = "userId", unique = true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getUserId() {
	    return this.userId;
	}
	
	public void setUserId(int userId) {
	    this.userId = userId;
	}
	
	
	@Column(name = "name", unique = true, nullable = false)
	public String getName() {
	    return this.name;
	}
	
	public void setName(String name) {
	    this.name = name;
	}

	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Game> getGames() {
	    return games;
	}

	public void setGames(Set<Game> games) {
		this.games = games;
	}	
}
