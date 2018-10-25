package com.raymund.bom.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.raymund.bom.exception.ComponentNotFoundException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Custom error handler to display a 404 error and error-message if {@link Component} does not exist
 *
 * @see  {@link ComponentNotFoundException}
 * @author Raymund
 *
 */
@ControllerAdvice
public class CustomControllerErrorAdvice extends ResponseEntityExceptionHandler {


	@ExceptionHandler({ ComponentNotFoundException.class })
	public ResponseEntity<Object> handleBadRequest(final ComponentNotFoundException ex, final WebRequest request) {
		final CustomErrorFormat responseBody = new CustomErrorFormat(ex.getMessage());
		final HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		return handleExceptionInternal(ex, responseBody, header, HttpStatus.NOT_FOUND, request);
	}


	@Getter
	@Setter
	@AllArgsConstructor
	class CustomErrorFormat{
		String error;
	}
}
