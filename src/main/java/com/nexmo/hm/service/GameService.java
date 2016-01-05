package com.nexmo.hm.service;

import com.nexmo.hm.exception.BadRequestException;
import com.nexmo.hm.to.GameTO;
import com.nexmo.hm.to.UserTO;

/**
 * @author Sunny Ghosh
 *
 */

public interface GameService {
	public UserTO newGame(UserTO user, String userName) throws BadRequestException;
	public GameTO checkGameAlgorithm(UserTO user, GameTO game,char letterEach, int userId ) throws BadRequestException;
	
}
