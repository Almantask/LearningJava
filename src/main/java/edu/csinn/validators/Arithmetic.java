package edu.csinn.validators;

import edu.csinn.fundamentals.FutureBirthdayException;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class Arithmetic{
    public interface OperateOnTwoNumbers{
        double operate(double number1, double number2);
    }

    public static void notOverflow(double number1, double number2, OperateOnTwoNumbers operation) throws ArithmeticException{
        if(number2 == 0){
            return;
        }

        double tempResult = operation.operate(number1, number2);

        var isOverflow = Double.isInfinite(tempResult) || Double.isNaN(tempResult);

        if(isOverflow) {
            var format = new DecimalFormat("#,##0.##E0");
            throw new ArithmeticException(String.format("Operating on %s and %s resulted in an overflow.", format.format(number1), format.format(number2)));
        }
    }

    public static void notDivisionFromZero(double number){
        if(number == 0){
            throw new ArithmeticException("Division from 0 is not possible.");
        }
    }

    public static void notLessOrEqualToZero(float number, String arg){
        if(number <= 0){
            throw new IllegalArgumentException(arg);
        }
    }
}
