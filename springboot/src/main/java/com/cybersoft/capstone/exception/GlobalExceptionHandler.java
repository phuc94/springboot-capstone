package com.cybersoft.capstone.exception;

import com.cybersoft.capstone.payload.response.BadRequestResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.ErrMessage;
import com.cybersoft.capstone.payload.response.NotFoundResponse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected BaseResponse<ErrMessage> handleNotFoundException(NotFoundException ex) {
        return new NotFoundResponse<>(new ErrMessage(ex.getLocalizedMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    protected BaseResponse<ErrMessage> handleBadRequestException(BadRequestException ex) {
        return new BadRequestResponse<>(new ErrMessage(ex.getLocalizedMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BadRequestResponse<ErrMessage>> handleInvalidRequest(MethodArgumentNotValidException ex, WebRequest request) {
        return new ResponseEntity<>(new BadRequestResponse<>(new ErrMessage(ex.getLocalizedMessage())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<BadRequestResponse<ErrMessage>> handleInvalidFormat(HttpMessageNotReadableException ex, WebRequest request) {
        return new ResponseEntity<>(new BadRequestResponse<>(new ErrMessage(ex.getLocalizedMessage())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    protected ResponseEntity<NotFoundResponse<ErrMessage>> handleNoResourceFoundException(NoResourceFoundException ex, WebRequest request) {
        return new ResponseEntity<>(new NotFoundResponse<>(new ErrMessage(ex.getLocalizedMessage())), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<BadRequestResponse<ErrMessage>> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        return new ResponseEntity<>(new BadRequestResponse<>(new ErrMessage(ex.getLocalizedMessage())), HttpStatus.BAD_REQUEST);
    }

}
