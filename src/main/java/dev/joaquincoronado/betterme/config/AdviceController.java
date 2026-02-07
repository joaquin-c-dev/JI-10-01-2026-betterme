package dev.joaquincoronado.betterme.config;

import dev.joaquincoronado.betterme.shared.exception.BadRequestException;
import dev.joaquincoronado.betterme.shared.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> generalError(Exception e){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemDetail response = ProblemDetail.forStatusAndDetail(status, e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ProblemDetail> badRequest(Exception e){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemDetail response = ProblemDetail.forStatusAndDetail(status, e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ProblemDetail> notFound(Exception e){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemDetail response = ProblemDetail.forStatusAndDetail(status, e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(response, status);
    }
}
