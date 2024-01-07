package com.example.bds.exception;

import com.example.bds.dto.ErrorResponseDTO;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;


@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerNotFoundException(NotFoundException ex, WebRequest req){
        ErrorResponseDTO result = new ErrorResponseDTO();
        result.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> handlerBindException(BindException ex, WebRequest req){
        String errorMessage =  "Validation failed for object: " + ex.getObjectName();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handlerCustomException(CustomRuntimeException ex, WebRequest req){
        ErrorResponseDTO result = new ErrorResponseDTO();
        result.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentialsException(BadCredentialsException ex) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED,ex.getMessage());
        errorDetail.setProperty("access_denied_reason","Invalid username or password");
        return errorDetail;
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDeniedException(AccessDeniedException ex) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN,ex.getMessage());
        errorDetail.setProperty("access_denied_reason","not_authorized");
        return errorDetail;
    }

    @ExceptionHandler(SignatureException.class)
    public ProblemDetail handleSignatureException(SignatureException ex) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN,ex.getMessage());
        errorDetail.setProperty("access_denied_reason","JWT Signature not valid");
        return errorDetail;
    }
    @ExceptionHandler(MalformedJwtException.class)
    public ProblemDetail handleMalformedJwtException(MalformedJwtException ex) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN,ex.getMessage());
        errorDetail.setProperty("access_denied_reason","JWT not valid");
        return errorDetail;
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
