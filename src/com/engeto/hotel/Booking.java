package com.engeto.hotel;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.time.LocalDate;

public class Booking {
    public static int nextId = 1;
    int id;
    private final Room room;
    private final List<Guest> guests;
    private final LocalDate dateStart;
    private final LocalDate dateEnd;
    private final VacationType type;


    public Booking(Room room, List<Guest> guests, LocalDate dateStart, LocalDate dateEnd, VacationType type) {
        if (guests.isEmpty()) throw new IllegalArgumentException("Přidejte alespoň jednoho hosta!");

        this.id = nextId++;
        this.room = room;
        this.guests = guests;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.type = type;
    }

    public Booking(Room room, List<Guest> guests, LocalDate dateStart, LocalDate dateEnd) {
        this(room, guests, dateStart, dateEnd, VacationType.UNKNOWN);
    }

    public Booking(Room room, List<Guest> guests, VacationType type) {
        if (type != VacationType.RECREATIONAL) {
            throw new IllegalArgumentException("Pro nerekreační typ pobytu musíte zadat datum rezervace.");
        }
        this.id=nextId++;
        this.room = room;
        this.guests = guests;
        this.type = type;
        this.dateStart = LocalDate.now();
        this.dateEnd = LocalDate.now().plusDays(6);
    }

    /*
    Do třídy Booking přidej metodu getBookingLength, která bude vracet počet nocí pro danou rezervaci.
     */

    public int getBookingLength() {
        return (int) ChronoUnit.DAYS.between(dateStart, dateEnd);
    }

    /*
    Přidej do třídy Booking metodu getPrice, která spočítá celkovou cenu objednávky.
     */

    public BigDecimal getPrice() {
        int numberOfNights = getBookingLength();
        BigDecimal pricePerNight = room.getPricePerNight();

        return pricePerNight.multiply(BigDecimal.valueOf(numberOfNights));
    }

    public Room getRoom() {
        return room;
    }

    public int getId() {
        return id;
    }

    public int getRoomNumber() {
        return room.getNumber();
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public VacationType getVacationType() {
        return type;
    }

    public String vacationTypeToString() {
        StringBuilder vacationType = new StringBuilder();
        switch (this.type) {
            case WORK -> vacationType.append("pracovní");
            case RECREATIONAL -> vacationType.append("rekreační");
            default -> vacationType.append("neuvedeno");
        }
        return vacationType.toString();
    }
}
