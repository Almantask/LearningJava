package edu.csinn.fundamentals;

import java.time.LocalDate;

public class FutureBirthdayException extends RuntimeException {
    public FutureBirthdayException(LocalDate date){
        super("Date cannot be ahead of current time. Was: " + date);
    }
}
