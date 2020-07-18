package edu.csinn.presentation;

import edu.csinn.calculators.BasicCalculator;

public class Main {

    public static void main(String[] args) {
        var calculator = new BasicCalculator();
        calculator.add(4);
        var result = calculator.getResult();

        System.out.println(result);
    }
}
