package com.engeto.hotel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Room> rooms = new ArrayList<>();

    public static void main(String[] args) {

        createRooms();
        fillBookings();

        /*
        V hlavní třídě projektu připrav metodu pro výpis seznam všech rezervací ve formátu:
        datumOd až datumDo: jméno hlavního hosta (datum narození)[počet hostů, výhledNaMoře ano/ne] za cena
         */

        getAllBookings();

        /*
        Připrav metodu pro výpis prvních 8 rezervací, které jsou určeny pro rekreaci (typ pobytu je rekreační). Pracovní pobyty při výpisu ignoruj/přeskoč.
        Můžeš ji také zobecnit a počet vypisovaných rezervací zadat jako parametr metody.
         */

        printRecreationalBookings(8);
        System.out.println();

        /*
        Vložení rezervace do seznamu: addBooking(Booking newBooking).
         */
        Room room3 = rooms.get(2);
        BookingManager.addBooking(new Booking(room3,
                List.of((new Guest("Jan", "Suchařípa", LocalDate.of(1944, 5,30))),
                new Guest("Ludmila", "Skočdopolová", LocalDate.of(1950, 3, 17))),
                VacationType.RECREATIONAL));

        /*
        Připrav v hlavní třídě metodu printGuestStatistics, která vypíše:
            celkový počet rezervací s jedním hostem,
            celkový počet rezervací se dvěma hosty,
            a celkový počet rezervací s více než dvěma hosty.
         */

        printGuestStatistics();
        System.out.println();

        /*
        Získání seznamu rezervací: getBookings().
         */
        System.out.println("Seznam rezervací:");
        System.out.println(BookingManager.getBookings());
        System.out.println();

        /*
        Ve třídě BookingManager připrav metodu getNumberOfWorkingBookings,
        která vrátí počet rezervací pro pracovní pobyty.
         */

        System.out.println("Počet pracovních pobytů: " + BookingManager.getNumberOfWorkingBookings());
        System.out.println();

        /*
        Ve třídě BookingManager připrav metodu getAverageGuests.
        Metoda projde všechny rezervace a vrátí jako výsledek průměrný počet hostů na rezervaci.
         */

        System.out.println("Průměrný počet hostů na rezervaci: " + String.format("%.2f", BookingManager.getAverageGuests()));
        System.out.println();

        /*
        Získání rezervace se zadaným indexem ze seznamu: getBooking(index).
         */

        System.out.println("Vybraná rezervace: " + BookingManager.getBooking(1));

        /*
        Do třídy Booking přidej metodu getBookingLength, která bude vracet počet nocí pro danou rezervaci.
         */
        Booking selectedReservation = BookingManager.getBookingObject(1);
        System.out.println("Délka pobytu vybrané rezervace: " + selectedReservation.getBookingLength() + " dní.");

        /*
        Přidej do třídy Booking metodu getPrice, která spočítá celkovou cenu objednávky.
         */
        System.out.println("Celková cena pobytu vybrané rezervace: " + selectedReservation.getPrice() + " Kč.");
        System.out.println();

        /*
        Vymazání seznamu rezervací: clearBookings()
         */
        BookingManager.clearBookings();
        System.out.println(BookingManager.getBookings());

    }

    private static void getAllBookings() {
        StringBuilder result = new StringBuilder();

        if (!BookingManager.bookings.isEmpty()) {
            for (Booking booking : BookingManager.bookings) {
                result.append(formatBookingDetails(booking));
            }
        } else {
            result.append("Žádné rezervace");
        }
        System.out.println(result.toString());
    }
    public static void printRecreationalBookings(int n) {
        int count = 0;
        for (Booking booking : BookingManager.bookings) {
            if (count >= n) {
                break;
            }

            if (booking.getVacationType() == VacationType.RECREATIONAL) {
                System.out.print("Rekreační pobyt: " + formatBookingDetails(booking));
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Žádné rekreační pobyty nenalezeny.");
        }
    }
    public static void printGuestStatistics() {
        int oneGuestCount = 0;
        int twoGuestsCount = 0;
        int moreThanTwoGuestsCount = 0;

        for (Booking booking : BookingManager.bookings) {
            List<Guest> guests = booking.getGuests();
            int guestCount = guests.size();

            if (guestCount == 1) {
                oneGuestCount++;
            } else if (guestCount == 2) {
                twoGuestsCount++;
            } else {
                moreThanTwoGuestsCount++;
            }
        }

        System.out.println("Statistiky hostů:");
        System.out.println("Celkem rezervací s jedním hostem: " + oneGuestCount);
        System.out.println("Celkem rezervací s dvěma hosty: " + twoGuestsCount);
        System.out.println("Celkem rezervací s více jak dvěma hosty: " + moreThanTwoGuestsCount);
    }
    private static String formatBookingDetails(Booking booking) {
        List<Guest> guests = booking.getGuests();
        Guest firstGuest = guests.get(0);
        String guestDescription = firstGuest.getDescription();

        Room room = booking.getRoom();
        String oceanView = room.oceanViewString();
        BigDecimal pricePerNight = room.getPricePerNight();

        return String.format("%s až %s: %s [%s, %s] za %s Kč %n",
                booking.getDateStart().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                booking.getDateEnd().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                guestDescription,
                booking.getGuests().size(),
                oceanView,
                pricePerNight);
    }
    public static void fillBookings() {
        Room room1 = rooms.get(0);
        Room room2 = rooms.get(1);
        Room room3 = rooms.get(2);

        BookingManager.addBooking(new Booking(room3,
                List.of(new Guest("Karel", "Dvořák", LocalDate.of(1990, 5,15))),
                LocalDate.of(2023, 6,1), LocalDate.of(2023, 6, 7),
                VacationType.WORK));

        BookingManager.addBooking(new Booking(room2,
                List.of(new Guest("Karel", "Dvořák", LocalDate.of(1979, 1, 3))),
                LocalDate.of(2023, 7, 18), LocalDate.of(2023, 7, 21),
                VacationType.RECREATIONAL));

        Guest guestKarolinaTmava = new Guest("Karolína", "Tmavá", LocalDate.of(1980, 1, 1));

        LocalDate karolinaStartDate = LocalDate.of(2023, 8, 1);
        LocalDate karolinaEndDate = karolinaStartDate.plusDays(1);

        for(int i = 0; i <10; i++) {
            BookingManager.addBooking(new Booking(room2, List.of(guestKarolinaTmava),
                    karolinaStartDate, karolinaEndDate, VacationType.RECREATIONAL));
            karolinaStartDate = karolinaStartDate.plusDays(2);
            karolinaEndDate = karolinaEndDate.plusDays(2);
        }

        BookingManager.addBooking(new Booking(room3, List.of(guestKarolinaTmava),
                LocalDate.of(2023, 8, 1), LocalDate.of(2023, 8, 31),
                VacationType.WORK));

    }
    public static void createRooms() {
        rooms.add(new Room(1, 1, true, true, BigDecimal.valueOf(1000)));
        rooms.add(new Room(2, 1, true, true, BigDecimal.valueOf(1000)));
        rooms.add(new Room(3, 3, false, true, BigDecimal.valueOf(2400)));
    }
    public static void previousHomework() {

        /*
        Vytvoř hosty Adélu Malíkovou (narozena 13. 3. 1993) a Jana Dvořáčka (narozen 5.5.1995).
        Vypiš na obrazovku jejich souhrnný popis ve formátu: Adéla Malíková (1993-03-13)/v českém formátu.
         */

        Guest guestAlena = new Guest("Adéla", "Malíková", LocalDate.of(1993, 3, 13));
        Guest guestJan = new Guest("Jan", "Dvořáček", LocalDate.of(1995, 5, 5));

        System.out.println(guestAlena.getDescription());
        System.out.println(guestJan.getDescription());
        System.out.println();

        /*
        Vytvoř pokoje a vypiš na obrazovku jejich popis:
        pokoj číslo 1 a pokoj číslo 2 jsou jednolůžkové za cenu 1000 Kč/noc, s balkonem a výhledem na moře.
        pokoj číslo 3 je trojlůžkový za cenu 2400 Kč/noc, bez balkónu, s výhledem na moře.
         */

        Room room1 = new Room(1, 1, true, true, BigDecimal.valueOf(1000));
        Room room2 = new Room(2, 1, true, true, BigDecimal.valueOf(1000));
        Room room3 = new Room(3, 3, false, true, BigDecimal.valueOf(2400));


        System.out.println(room1.getRoomDescription());
        System.out.println(room3.getRoomDescription());
        System.out.println();

        /*
        Připrav rezervace:
        pro Adélu na pokoj č. 1 od 19. do 26. 7. 2021.
        pro oba (společná rezervace) na pokoj č. 3 od 1. do 14. 9. 2021.
         */

        Booking booking1 = new Booking(room1, List.of(guestAlena), LocalDate.of(2021, 7, 19), LocalDate.of(2021, 7, 26), VacationType.WORK);
        Booking booking2 = new Booking(room3, List.of(guestAlena, guestJan), LocalDate.of(2021, 9, 1), LocalDate.of(2021, 9, 14), VacationType.RECREATIONAL);

        /*
        Vypiš seznam všech rezervací
         */

        System.out.println(BookingManager.getBookings());
        System.out.println();

        /*
        Zkus přidat dvě různé rezervace pro jednoho hosta na různé pokoje.
         */

        Booking booking3 = new Booking(room3, List.of(guestJan), LocalDate.of(2020, 9, 1), LocalDate.of(2020, 9, 14));
        Booking booking4 = new Booking(room1, List.of(guestJan), LocalDate.of(2020, 8, 1), LocalDate.of(2020, 8, 14));

        /*
        Zkus přidat dvě různé rezervace na jeden pokoj v různá data.
         */

        Booking booking5 = new Booking(room1, List.of(guestAlena), LocalDate.of(2022, 9, 1), LocalDate.of(2022, 9, 14), VacationType.WORK);
        Booking booking6 = new Booking(room1, List.of(guestAlena), LocalDate.of(2020, 6, 11), LocalDate.of(2020, 7, 1), VacationType.RECREATIONAL);


        /*
        Pokus o "automatický" rekreační pobyt na 6 dnů ode dneška
         */

        Booking booking7 = new Booking(room3, List.of(guestAlena, guestJan), VacationType.RECREATIONAL);

        System.out.println(BookingManager.getBookings());
        System.out.println();


        /*
        Je zajištěno, aby u každé rezervace byl registrovaný minimálně jeden host?
        Neměla by jít vytvořit rezervace bez hosta
         */

        //Booking booking9 = new Booking(room1, List.of(), LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 14));



    }


}