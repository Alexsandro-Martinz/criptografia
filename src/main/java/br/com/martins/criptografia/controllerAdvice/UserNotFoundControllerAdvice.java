package br.com.martins.criptografia.controllerAdvice;

import br.com.martins.criptografia.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class UserNotFoundControllerAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandle(UserNotFoundException ex) {
        return ex.getMessage();
    }
}
