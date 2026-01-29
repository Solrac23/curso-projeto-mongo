package com.example.workshopmongo.resources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.workshopmongo.services.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<StandardError> objectNotFound(
    ObjectNotFoundException e, 
    HttpServletRequest request
  ) {
    StandardError err = new StandardError();
    HttpStatus status = HttpStatus.NOT_FOUND;

    err.setTimestamp(System.currentTimeMillis());
    err.setStatus(status.value());
    err.setError("Não encontrado");
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }

   
}
