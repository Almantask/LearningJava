package edu.csinn.validators;

import edu.csinn.fundamentals.FutureBirthdayException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class RequiresTimeTests {
    @Test
    void notFuture_when_future_throws_FutureBirthdayException(){
        var date = LocalDate.now().plusDays(1);

        Throwable thrown = catchThrowable(()-> Requires.Time.notFuture(date));

        assertThatExceptionOfType(FutureBirthdayException.class)
                .isThrownBy(() -> {throw thrown;})
                .withMessageContaining(date.toString());
    }

    @Test
    void notFuture_when_past_doesNotThrow() {
        var date = LocalDate.now();

        Throwable thrown = catchThrowable(()-> Requires.Time.notFuture(date));

        assertThat(thrown).doesNotThrowAnyException();
    }
}
