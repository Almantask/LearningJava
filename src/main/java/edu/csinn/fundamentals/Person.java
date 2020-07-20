package edu.csinn.fundamentals;

import edu.csinn.validators.Arithmetic;
import edu.csinn.validators.Requires;
import lombok.Getter;
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
        var periodSinceBorn = Period.between(birthday, currentDate);

        return periodSinceBorn.getYears();
    }

    public Person(String name, float weight, float height) {
        this(name, weight, height, LocalDate.now());
    }

    // Note how I had to swap what args are in each ctor!
    public Person(String name, float weight, float height, LocalDate birthday) {
        Requires.Str.notNullOrEmpty(name, "name");
        Requires.Time.notFuture(birthday);
        Arithmetic.notLessOrEqualToZero(weight, "weight");
        Arithmetic.notLessOrEqualToZero(height, "height");

        setWeight(weight);
        setHeight(height);
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "weight=" + getWeight() +
                ", height=" + getHeight() +
                ", name='" + getName() + '\'' +
                ", birthday=" + getBirthday() +
                '}';
    }
}
