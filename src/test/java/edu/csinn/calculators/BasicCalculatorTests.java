package edu.csinn.calculators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class BasicCalculatorTests {

    private final BasicCalculator calculator;

    public BasicCalculatorTests(){
        calculator = new BasicCalculator();
    }

    @ParameterizedTest(name = "0 + {0} = {1}")
    @MethodSource("getAddCalculatorAddExpectations")
    void add_when_anyNumber_sumsWithResult(double number, double expected) {
        calculator.add(number);

        assertThat(calculator.getResult()).isEqualTo(expected);
    }

    @ParameterizedTest(name = "0 - {0} = {1}")
    @CsvSource({
            "1, -1",
            "0, 0",
            "-1, 1",
            Double.MAX_VALUE + ", "+ -Double.MAX_VALUE
    })
    void take_when_anyNumber_differenceWithResult(double number, double expected) {
        calculator.take(number);

        assertThat(calculator.getResult()).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({
            "0,  1, 0",
            "-1, 1, -1",
            "6,  3, 2",
            Double.MAX_VALUE + ", -1, " + -Double.MAX_VALUE
    })
    void div_when_anyNumber_resultDivided(double initial, double number, double expected) {
        // Arrange
        calculator.add(initial);

        // Act
        calculator.div(number);

        // Assert
        assertThat(calculator.getResult()).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0} * {1} = {2}")
    @CsvSource({
            "0,  1, 0",
            "1,  0, 0",
            "-1, 1, -1",
            "2,  3, 6",
            Double.MAX_VALUE + ", -1, " + -Double.MAX_VALUE
    })
    void mul_when_anyNumber_resultMultiplied(double initial, double number, double expected) {
        // Arrange
        calculator.add(initial);

        // Act
        calculator.mul(number);

        // Assert
        assertThat(calculator.getResult()).isEqualTo(expected);
    }

    @Test
    void add_when_resultIsOverflow_throws_ArithmeticException(){
        calculator.add(Double.MAX_VALUE);

        Throwable thrown = catchThrowable(() -> calculator.add(Double.MAX_VALUE));

        assertThat(thrown).hasMessageContaining("resulted in an overflow");
    }

    @Test
    void take_when_resultIsOverflow_throws_ArithmeticException(){
        calculator.take(Double.MAX_VALUE);

        Throwable thrown = catchThrowable(() -> calculator.take(Double.MAX_VALUE));

        assertThat(thrown).hasMessageContaining("resulted in an overflow");
    }

    @Test
    void mul_when_mulResultsInOverflow_throws_ArithmeticException(){
        calculator.add(Double.MAX_VALUE);

        Throwable thrown = catchThrowable(() -> calculator.mul(1.1));

        assertThat(thrown).hasMessageContaining("resulted in an overflow");
    }

    @Test
    void div_given_0_throws_ArithmeticException(){
        Throwable thrown = catchThrowable(() -> calculator.div(0));

        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(() -> {throw thrown;});
    }

    private static Stream<Arguments> getAddCalculatorAddExpectations() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(1, 1),
                Arguments.of(-1, -1),
                Arguments.of(Double.MAX_VALUE, Double.MAX_VALUE)
        );
    }
}
