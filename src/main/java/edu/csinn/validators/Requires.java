package edu.csinn.validators;

import edu.csinn.fundamentals.FutureBirthdayException;

import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 * Validates whether arguments fit the criteria and if not, throws a kind of IllegalArgumentException.
 */
public class Requires {

    public static class Str{
        /**
         * Checks if the string is not null or empty.
         *
         * @param argumentName - name of an argument that is being validated.
         * @throws IllegalArgumentException if the string is null or empty.
         */
        public static void notNullOrEmpty(final String string, final String argumentName) throws IllegalArgumentException{
            if(string == null || string.trim().isEmpty()){
                throw new IllegalArgumentException(String.format("string %s cannot be null or empty.", argumentName));
            }
        }
    }

    public static class Time{
        public static void notFuture(LocalDate date){
            var compareResult = LocalDate.now().compareTo(date);
            boolean isFuture = compareResult < 0;
            if(isFuture){
                throw new FutureBirthdayException(date);
            }
        }
    }
}
