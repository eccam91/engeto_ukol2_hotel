package com.engeto.hotel;

import java.math.BigDecimal;

public class Room {
    private final int number, beds;
    private final boolean balcony, oceanView;
    private final BigDecimal pricePerNight;

    public Room(int number, int beds, boolean balcony, boolean oceanView, BigDecimal pricePerNight) {
        this.number = number;
        this.beds = beds;
        this.balcony = balcony;
        this.oceanView = oceanView;
        this.pricePerNight = pricePerNight;
    }

    public int getNumber() {
        return number;
    }

    public String isBalcony() {
        return balcony ? "s balkónem" : "bez balkónu";
    }

    public String isOceanView() {
        return oceanView ? "s výhledem na moře" : "bez výhledu na moře";
    }

    public String getBedCount() {
        StringBuilder bedCount = new StringBuilder();

        switch (this.beds) {
            case 1 -> bedCount.append("jednolůžkový");
            case 2 -> bedCount.append("dvoulůžkový");
            case 3 -> bedCount.append("třílůžkový");
            case 4 -> bedCount.append("čtyřlůžkový");
            //... až kolik lůžek je třeba :)
            default -> bedCount.append("plný postelí");
        }
        return bedCount.toString();
    }

    public String getRoomDescription() {
        String bedCount = getBedCount();
        return String.format("Pokoj čislo %s je %s za cenu %s Kč/noc, %s a %s.", number, bedCount, pricePerNight, isBalcony(), isOceanView());
    }
}
