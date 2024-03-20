package esportsclash.pratique.player.infrastructure.spring;

import esportsclash.pratique.core.domain.exception.BadRequestException;
import esportsclash.pratique.core.domain.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<?> handleNotFoundException(NotFoundException e){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BadRequestException.class)
     ResponseEntity<?> handleIlleBadRequestException(IllegalArgumentException e){
        return ResponseEntity.badRequest().build();
    }
}
