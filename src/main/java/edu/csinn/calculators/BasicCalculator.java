package edu.csinn.calculators;

import lombok.Getter;

public class BasicCalculator {

    @Getter
    private double result;

    public void add(double number){
        result += number;
    }

    public void take(double number){
        result -= number;
    }

    public void div(double number){
        result /= number;
    }

    public void mul(double number){
        result *= number;
    }
}
