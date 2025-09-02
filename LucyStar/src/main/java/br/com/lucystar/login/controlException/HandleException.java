package br.com.lucystar.login.controlException;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import br.com.lucystar.login.controlException.dto.ResponseException;
import br.com.lucystar.login.exceptions.ResetSystemException;
import br.com.lucystar.login.utils.Util;

@RestController
@ControllerAdvice
public class HandleException {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ResponseException> handleAllException(Exception ex, WebRequest request) {
		ResponseException response = new ResponseException(ex.getMessage(), request.getDescription(false),
				Util.convertDate(new Date()));
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@ExceptionHandler(ResetSystemException.class)
	public final ResponseEntity<ResponseException> handleAllResetSystemException(Exception ex, WebRequest request) {
		ResponseException response = new ResponseException(ex.getMessage(), request.getDescription(false),
				Util.convertDate(new Date()));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
