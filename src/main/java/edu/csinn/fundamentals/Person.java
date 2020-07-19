package edu.csinn.fundamentals;

import edu.csinn.validators.Requires;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

public class Person {
    @Getter
    @Setter
    private float weight;

    @Getter
    @Setter
    private float height;

    @Getter
    private final String name;

    @Getter
    private final LocalDate birthday;

    public int getAge(){
        var currentDate = LocalDate.now();
        var periodSinceBorn = Period.between(currentDate, birthday);

        return periodSinceBorn.getYears();
    }

    public Person(String name, float weight, float height) {
        this(name, weight, height, LocalDate.now());
    }

    // Note how I had to swap what args are in each ctor!
    public Person(String name, float weight, float height, LocalDate birthday) {
        Requires.Str.notNullOrEmpty(name, "name");

        this.weight = weight;
        this.height = height;
        this.name = name;
        this.birthday = birthday;
    }
}
