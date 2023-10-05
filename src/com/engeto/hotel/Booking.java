package com.engeto.hotel;

import java.util.List;
import java.time.LocalDate;

public class Booking {
    private final Room room;
    private final List<Guest> guests;
    private final LocalDate dateStart;
    private final LocalDate dateEnd;
    private final VacationType type;


    public Booking(Room room, List<Guest> guests, LocalDate dateStart, LocalDate dateEnd, VacationType type) {
        if (guests.isEmpty()) throw new IllegalArgumentException("Přidejte alespoň jednoho hosta!");

        this.room = room;
        this.guests = guests;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.type = type;

        ListOfBookings.addReservation(this);
    }

    public Booking(Room room, List<Guest> guests, LocalDate dateStart, LocalDate dateEnd) {
        this(room, guests, dateStart, dateEnd, VacationType.UNKNOWN);
    }

    /*
    Volitelně můžeš zkusit zařídit, aby se při vytváření rezervace rezervovalo automaticky na rekreační pobyt
    od dneška na dalších 6 nocí, pokud nezadáš konkrétní data začátku a konce.
    (Pokud uvedeš všechny parametry rezervace, použijí se tak, jak jsou zadané.)
     */

    public Booking(Room room, List<Guest> guests, VacationType type) {
        if (type != VacationType.RECREATIONAL) {
            throw new IllegalArgumentException("Pro nerekreační typ pobytu musíte zadat datum rezervace.");
        }

        this.room = room;
        this.guests = guests;
        this.type = type;
        this.dateStart = LocalDate.now();
        this.dateEnd = LocalDate.now().plusDays(6);

        ListOfBookings.addReservation(this);
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

    public String getVacationType() {
        StringBuilder vacationType = new StringBuilder();
        switch (this.type) {
            case WORK -> vacationType.append("pracovní");
            case RECREATIONAL -> vacationType.append("rekreační");
            default -> vacationType.append("neuvedeno");
        }
        return vacationType.toString();
    }
}
