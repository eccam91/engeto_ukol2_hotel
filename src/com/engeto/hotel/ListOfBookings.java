package com.engeto.hotel;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ListOfBookings {
    private static final List<Booking> bookings = new ArrayList<>();

    public static void addReservation(Booking booking) {
        bookings.add(booking);
    }

    public static String getAllReservations() {
        StringBuilder result = new StringBuilder();

        if (!bookings.isEmpty()) {
            for (Booking booking : bookings) {
                String guestNames = getGuestNames(booking.getGuests());
                result.append(String.format("Pokoj číslo: %s, host(é): %s, rezervace od: %s do: %s. typ pobytu: %s.%n",
                        booking.getRoomNumber(), guestNames, booking.getDateStart().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), booking.getDateEnd().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), booking.getVacationType()));
            }
        } else {
            result.append("Žádné rezervace");
        }

        return result.toString();
    }

    private static String getGuestNames(List<Guest> guests) {
        StringBuilder names = new StringBuilder();

        for (int i = 0; i < guests.size(); i++) {
            Guest guest = guests.get(i);
            names.append(guest.getFullName());

            if (i < guests.size() - 1) {
                names.append(", ");
            }
        }

        return names.toString();
    }
}

