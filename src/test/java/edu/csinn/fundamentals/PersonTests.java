package edu.csinn.fundamentals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class PersonTests {
    @Test
    void new_when_nullName_throws(){
        Throwable thrown = catchThrowable(() -> new Person(null, 1, 2));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {throw thrown;})
                .withMessage("string name cannot be null or empty.");
    }

    @Test
    void new_when_validArgs_doesNotThrow(){
        Throwable thrown = catchThrowable(() -> new Person("a", 1, 2));

        assertThat(thrown).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "Born at {0} is {1} years old.")
    @MethodSource("getAgeExpectations")
    void getAge_returns_yearsFromBirthdayToNow(LocalDate birthday, int expectedYears){
        var person = buildPersonWithBirthday(birthday);

        var age = person.getAge();

        assertThat(age).isEqualTo(expectedYears);
    }

    @Test
    void toString_returns_defaultIDEFormattedString() throws JsonProcessingException {
        // Arrange
        var person = buildPersonWithBirthday(LocalDate.of(2020, 05, 25));

        // Act
        var formattedPerson = person.toString();

        // Assert
        var expected = "Person{weight=1.0, height=5.0, name='irrelevant', birthday=2020-05-25}";
        assertThat(formattedPerson).isEqualTo(expected);
    }

    static Stream<Arguments> getAgeExpectations(){
        return Stream.of(
                Arguments.of(LocalDate.now().minusYears(1).minusDays(1), 1),
                Arguments.of(LocalDate.now().minusYears(1).plusDays(1), 0)
        );
    }

    private Person buildPersonWithBirthday(LocalDate birthday) {
        return new Person("irrelevant", 1, 5, birthday);
    }
}
