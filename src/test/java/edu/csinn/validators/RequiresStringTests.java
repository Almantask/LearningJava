package edu.csinn.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class RequiresStringTests {
    @Test
    void notNullOrEmpty_when_notNullOrEmptyString_doesNotThrow(){
        var input = "a";

        Throwable thrown = catchThrowable(() -> Requires.Str.notNullOrEmpty(input, "irrelevant"));

        assertThat(thrown).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "\"{0}\" - resulted in IllegalArgumentException")
    @NullSource()
    @ValueSource(strings = {"", " "})
    void notNullOrEmpty_when_NullOrEmptyString_Throws_IllegalArgumentException(String input){
        var argName = "input";

        Throwable thrown = catchThrowable(() -> Requires.Str.notNullOrEmpty(input, argName));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {throw thrown;})
                .withMessageContaining(argName);
    }

    private static Stream<Arguments> getNullOrEmptyString(){
        return Stream.of(Arguments.of(""),
                Arguments.of(" "),
                Arguments.of(new Object[]{null}));
    }
}
