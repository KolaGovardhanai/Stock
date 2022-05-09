package com.zensar.stockapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidStockIdException extends Exception{
 private String message;

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}
public InvalidStockIdException(String message) {
	super(message);
}

}
