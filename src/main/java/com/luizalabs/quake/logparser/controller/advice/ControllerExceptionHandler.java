package com.luizalabs.quake.logparser.controller.advice;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.luizalabs.quake.logparser.exception.ErrorResponse;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleObjectNotFoundException(ObjectNotFoundException exception) {
		return new ResponseEntity<Object>(
				new ErrorResponse(
						String.format("Game %s not found", Optional.ofNullable(exception.getIdentifier()).orElse("")),
						"Tried to find a game, but did not find any", HttpStatus.NOT_FOUND.value()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException exception) {

		return new ResponseEntity<Object>(
				new ErrorResponse(
						String.format("Invalid value '%s' for parameter %s. It must be a number", exception.getValue(),
								exception.getParameter().getParameterName()),
						String.format("Invalid value '%s' for parameter %s. It must be a number", exception.getValue(),
								exception.getParameter().getParameterName()),
						HttpStatus.BAD_REQUEST.value()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {

		return new ResponseEntity<Object>(
				new ErrorResponse(exception.getMessage(), exception.getMessage(), HttpStatus.BAD_REQUEST.value()),
				HttpStatus.BAD_REQUEST);
	}
}
