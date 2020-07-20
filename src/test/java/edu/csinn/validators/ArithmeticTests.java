package edu.csinn.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class ArithmeticTests {
    @Test
    void notDivisionFromZero_when_0_throws_ArithmeticException(){
        Throwable thrown = catchThrowable(() -> Arithmetic.notDivisionFromZero(0));

        assertThatExceptionOfType(ArithmeticException.class)
                .isThrownBy(() -> {throw thrown;})
                .withMessageContaining("Division from 0");
    }

    @Test
    void notDivisionFromZero_when_1_doesNotThrow(){
        Throwable thrown = catchThrowable(() -> Arithmetic.notDivisionFromZero(1));

        assertThat(thrown).doesNotThrowAnyException();
    }

    @Test
    void notOverflow_when_overflow_throws_ArithmeticException(){
        Throwable thrown = catchThrowable(() -> Arithmetic.notOverflow(Double.MAX_VALUE, Double.MAX_VALUE, (a, b) -> a + b));

        assertThatExceptionOfType(ArithmeticException.class)
                .isThrownBy(() -> {throw thrown;})
                .withMessageContaining("overflow");
    }

    @Test
    void notOverflow_when_notOverflow_doesNotThrow(){
        Throwable thrown = catchThrowable(() -> Arithmetic.notOverflow(1, 1, (a, b) -> a + b));

        assertThat(thrown).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(floats = {0, -1})
    void notLessOrEqualToZero_when_0_orNegative_throws_ArgumentException(float number){
        final String arg = "argument";
        Throwable thrown =  catchThrowable(() -> Arithmetic.notLessOrEqualToZero(number,arg));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {throw thrown;})
                .withMessageContaining(arg);
    }

    @Test
    void notLessOrEqualToZero_when_positive_doesNotThrow(){
        Throwable thrown =  catchThrowable(() -> Arithmetic.notLessOrEqualToZero(1,"irrelevant"));

        assertThat(thrown).doesNotThrowAnyException();
    }
}
