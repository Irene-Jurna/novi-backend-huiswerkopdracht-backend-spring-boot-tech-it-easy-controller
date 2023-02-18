package nl.novi.techItEasy.controllers;

import nl.novi.techItEasy.exceptions.IndexOutOfBoundsException;
import nl.novi.techItEasy.exceptions.InvalidNameException;
import nl.novi.techItEasy.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IndexOutOfBoundsException.class)
    public ResponseEntity<Object> exception(IndexOutOfBoundsException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidNameException.class)
    public ResponseEntity<Object> exception(InvalidNameException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE);
    }

}
