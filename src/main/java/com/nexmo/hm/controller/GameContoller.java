package com.nexmo.hm.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.nexmo.hm.exception.NotAuthorizedException;
import com.nexmo.hm.management.SessionManagement;
import com.nexmo.hm.service.GameService;
import com.nexmo.hm.to.GameTO;
import com.nexmo.hm.to.UserTO;
import com.nexmo.hm.utils.GameStatusEnum;
import com.nexmo.hm.utils.SessionKeyEnum;

/**
 * @author Sunny Ghosh
 *
 */

@Controller
class GameController {

	@Autowired
	private GameService gameService;

	@Autowired
	private SessionManagement sessionManagement;

	@RequestMapping(value = "/newGame/{userName}", method = RequestMethod.GET)
	public ModelAndView newGame(@PathVariable String userName) {
		UserTO userTO = (UserTO) sessionManagement.getSession(SessionKeyEnum.USER);
		invalidateCurrentGameSession();
		if (userTO == null) {
			throw new NotAuthorizedException();
		}
		userTO = gameService.newGame(userTO, userName);
		sessionManagement.setSession(SessionKeyEnum.USER, userTO);
		sessionManagement.setSession(SessionKeyEnum.GAME, new ArrayList<>(userTO.getGames()).get(0));
		return new ModelAndView("gameOn", "user", userTO);
	}

	@RequestMapping(value = "/gameOn/{userId}", method = RequestMethod.POST)
	public @ResponseBody GameTO checkGameAlgorithm(@PathVariable int userId,
			@RequestParam(value = "letter", required = true) String letter) {
		char charEach = letter.toUpperCase().charAt(0);
		GameTO game = (GameTO) sessionManagement.getSession(SessionKeyEnum.GAME);
		UserTO user = (UserTO) sessionManagement.getSession(SessionKeyEnum.USER);
		if (user == null) {
			throw new NotAuthorizedException();
		}
		game = gameService.checkGameAlgorithm(user, game, charEach, userId);
		if (game != null && game.getGameStatus().equals(GameStatusEnum.NEW)) {
			sessionManagement.setSession(SessionKeyEnum.GAME, game);
		}
		return game;
	}

	@RequestMapping(value = "/invalidateCurrentGame", method = RequestMethod.POST)
	public void invalidateCurrentGameSession() {
		sessionManagement.clearGameSession(SessionKeyEnum.GAME);
	}
}
