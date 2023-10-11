package com.engeto.hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Guest {
    private String name;
    private String surname;
    private LocalDate birthdate;

    public Guest(String name, String surname, LocalDate birthdate) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }
    public String getFullName() {
        return String.format("%s %s", name, surname);
    }
    public String getDescription() {
        return String.format("%s %s (%s)", name, surname, birthdate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

    }
}