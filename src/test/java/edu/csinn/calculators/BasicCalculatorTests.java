package edu.csinn.calculators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

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

    @Test
    void add_when_resultIsOverflow_throws_ArithmeticException(){
        calculator.add(Double.MAX_VALUE);

        Throwable thrown = catchThrowable(() -> calculator.add(1));

        assertThat(thrown).hasMessageContaining("Trying to add 1");
    }

    @Test
    void take_when_resultIsOverflow_throws_ArithmeticException(){
        calculator.take(Double.MAX_VALUE);

        Throwable thrown = catchThrowable(() -> calculator.take(1));

        assertThat(thrown).hasMessageContaining("Trying to take 1");
    }

    @Test
    void mul_when_mulResultsInOverflow_throws_ArithmeticException(){
        calculator.add(Double.MAX_VALUE);

        Throwable thrown = catchThrowable(() -> calculator.mul(1.1));

        assertThat(thrown).hasMessageContaining("Trying to multiply 1.1");
    }

    @Test
    void div_given_0_throws_ArithmeticException(){
        Throwable thrown = catchThrowable(() -> calculator.div(0));

        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(() -> {throw thrown;});
    }

    @ParameterizedTest(name = "0 - {0} = {1}")
    @CsvSource({
            "1,    -1",
            "0,    0",
            "-1,  1",
            Double.MAX_VALUE + ", "+ -Double.MAX_VALUE
    })
    void take_when_anyNumber_differenceWithResult(double number, double expected) {
        calculator.take(number);

        assertThat(calculator.getResult()).isEqualTo(expected);
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
