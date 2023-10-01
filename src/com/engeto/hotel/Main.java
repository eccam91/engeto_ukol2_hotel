import com.engeto.hotel.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

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
        System.out.println(room2.getRoomDescription());
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

        System.out.println(ListOfBookings.getAllReservations());
        System.out.println();

        /*
        Zkus přidat dvě různé rezervace pro jednoho hosta na různé pokoje.
         */

        Booking booking3 = new Booking(room3, List.of(guestJan), LocalDate.of(2020, 9, 1), LocalDate.of(2020, 9, 14));
        Booking booking4 = new Booking(room1, List.of(guestJan), LocalDate.of(2020, 9, 1), LocalDate.of(2020, 9, 14));

        /*
        Zkus přidat dvě různé rezervace na jeden pokoj v různá data.
         */

        Booking booking5 = new Booking(room1, List.of(guestAlena), LocalDate.of(2022, 9, 1), LocalDate.of(2022, 9, 14));
        Booking booking6 = new Booking(room1, List.of(guestAlena), LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 14));


        /*
        Pokus o "automatický" rekreační pobyt na 6 dnů ode dneška
         */

        Booking booking7 = new Booking(room3, List.of(guestAlena, guestJan), VacationType.RECREATIONAL);

        System.out.println(ListOfBookings.getAllReservations());


        /*
        Je zajištěno, aby u každé rezervace byl registrovaný minimálně jeden host?
        Neměla by jít vytvořit rezervace bez hosta
         */

        //Booking booking9 = new Booking(room1, List.of(), LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 14));


    }
}