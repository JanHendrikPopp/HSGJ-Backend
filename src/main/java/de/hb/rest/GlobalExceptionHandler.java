package de.hb.rest;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.validation.ConstraintViolationException;

import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import de.hb.exception.Errors;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	private static final Logger LOG = Logger.getAnonymousLogger();

	@ExceptionHandler({ConstraintViolationException.class})
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public Errors handleValidationError(ConstraintViolationException valEx) {
		String id = logError(valEx);
		return new Errors(id, valEx.getConstraintViolations());
	}

	private String logError(Throwable t) {
		String uuid = UUID.randomUUID().toString();
		LOG.log(Level.ALL, uuid + t);
		return uuid;
	}
}
