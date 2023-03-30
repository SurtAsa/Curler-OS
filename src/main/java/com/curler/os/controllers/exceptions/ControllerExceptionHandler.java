package com.curler.os.controllers.exceptions;

import com.curler.os.services.exceptions.DataIntegrityViolationException;
import com.curler.os.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e){
    
        StandardError error = new StandardError(System.currentTimeMillis(), 
                HttpStatus.NOT_FOUND.value(), e.getMessage());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException error){

        StandardError standardError = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), error.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException error){

        ValidationError validationError = new ValidationError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), " Erro na validação dos campos!");

        for (FieldError fieldError: error.getBindingResult().getFieldErrors()){
            validationError.addErrors(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }
}
