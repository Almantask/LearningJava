package edu.csinn.calculators;

import edu.csinn.validators.Arithmetic;
import lombok.Getter;

public class BasicCalculator {
    @Getter
    private double result = 0;

    public void add(double number){
       Arithmetic.notOverflow(number, (number1, number2) -> number1 + number2);
       result += number;
    }

    public void take(double number){
        Arithmetic.notOverflow(number, (number1, number2) -> number1 - number2);
        result -= number;
    }

    public void div(double number){
        Arithmetic.notDivisionFromZero(number);
        result /= number;
    }

    public void mul(double number){
        Arithmetic.notOverflow(number, (number1, number2) -> number1 * number2);
        result *= number;
    }
}
