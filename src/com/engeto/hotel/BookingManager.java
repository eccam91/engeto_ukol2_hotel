package com.engeto.hotel;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static com.engeto.hotel.VacationType.WORK;

public class BookingManager {
    public static List<Booking> bookings = new ArrayList<>();
    public static void addBooking(Booking booking) {
        bookings.add(booking);
    }
    public static String getBookingAsText(int index) {
        if (index >= 1 && index < bookings.size()+1) {
            Booking booking = bookings.get(index-1);
            String guestNames = getGuestNames(booking.getGuests());

            return String.format("ID rezervace: %s, pokoj číslo: %s, host(é): %s, rezervace od: %s do: %s. typ pobytu: %s.%n",
                    booking.getId(), booking.getRoomNumber(), guestNames, booking.getDateStart().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), booking.getDateEnd().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), booking.getVacationType().toString());
        } else {
            return "Rezervace s tímto číslem neexistuje.";
        }
    }
    public static String getBookingsAsText() {
        StringBuilder result = new StringBuilder();

        if (!bookings.isEmpty()) {
            for (Booking booking : bookings) {
                String guestNames = getGuestNames(booking.getGuests());
                result.append(String.format("ID rezervace: %s, pokoj číslo: %s, host(é): %s, rezervace od: %s do: %s. typ pobytu: %s.%n",
                        booking.getId(), booking.getRoomNumber(), guestNames, booking.getDateStart().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), booking.getDateEnd().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), booking.getVacationType().toString()));
            }
        } else {
            result.append("Žádné rezervace");
        }

        return result.toString();
    }
    public static void clearBookings() {
        bookings.clear();
    }
    public static Booking getBooking(int index) {
        if (index >= 1 && index < bookings.size()+1) {
            Booking booking = bookings.get(index-1);
            return booking;
        } else {
            return null;
        }
    }
    public static int getNumberOfWorkingBookings() {
        int result = 0;
        for (Booking booking : bookings) {
            if (booking.getVacationType() == WORK) {
                result++;
            }
        }
        return result;
    }
    public static double getAverageGuests() {
        if (bookings.isEmpty()) {
            return 0;
        }

        int totalGuests = 0;
        for (Booking booking : bookings) {
            totalGuests += booking.getGuests().size();
        }

        return (double) totalGuests/bookings.size();
    }
    public static String getGuestNames(List<Guest> guests) {
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

