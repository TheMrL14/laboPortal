package be.ehb.finalwork.lennert.lapoportal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InputNotCorrect extends Exception{
    public InputNotCorrect() {
        super("Provided Input Is Not Correct");
    }

    public InputNotCorrect(String message) {
        super(message);
    }

    public InputNotCorrect(String message, Throwable cause) {
        super(message, cause);
    }

    public InputNotCorrect(Throwable cause) {
        super(cause);
    }

    public InputNotCorrect(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
