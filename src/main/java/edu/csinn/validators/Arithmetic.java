package edu.csinn.validators;

import java.text.DecimalFormat;

public class Arithmetic{
    interface OperateOnTwoNumbers<TNumber extends Number & Comparable>{
        TNumber operate(TNumber number1, TNumber number2);
    }

    public static <TNumber extends Number> void notOverflow(TNumber number1, TNumber number2, OperateOnTwoNumbers operation) throws ArithmeticException{
        if(number2 == 0){
            return;
        }

        var tempResult = operation.operate(number1, number2);

        var isOverflow = Double.isInfinite(tempResult) || Double.isNaN(tempResult);

        if(isOverflow) {
            var format = new DecimalFormat("#,##0.##E0");
            throw new ArithmeticException(String.format("Operating on %f and %f resulted in an overflow.", format.format(number1), format.format(number2)));
        }
    }
`
    public static <TNumber extends Number> void notDivisionFromZero(TNumber number){
        if(number == (T){
            throw new ArithmeticException("Division from 0 is not possible.");
        }
    }

    private static <TNumber extends Number & Comparable> Boolean isOverflow(TNumber number){
        if(number instanceof int){
            var tmp = (int)number;
            if (tmp > Integer.MAX_VALUE || tmp < Integer.MIN_VALUE){
                return true;
            }
        }
        else(number instanceof )
    }
}
