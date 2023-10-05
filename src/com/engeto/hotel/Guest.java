package com.engeto.hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Guest {
    private final String name, surname;
    private final LocalDate birthdate;

    public Guest(String name, String surname, LocalDate birthdate) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public String getFullName() {
        return String.format("%s %s", name, surname);
    }

    /*
    Ve třídě Guest můžeš zkusit vytvořit metodu getDescription, která vrátí čitelný textový popis,
    zkombinovaný ze jména a data narození.
     */

    public String getDescription() {
        return String.format("%s %s (%s)", name, surname, birthdate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

    }
}