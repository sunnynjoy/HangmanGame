package com.nexmo.hm.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * @author Sunny Ghosh
 *
 */

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NotAuthorizedException extends RuntimeException {

	private static final long serialVersionUID = -4515225961281898950L;
	
}
