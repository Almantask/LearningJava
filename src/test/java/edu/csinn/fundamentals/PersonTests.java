package edu.csinn.fundamentals;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PersonTests {
    @Test
    void new_when_nullName_throws(){
        Throwable thrown = catchThrowable(() -> new Person(null, 1, 2));
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {throw thrown;});
    }
}
