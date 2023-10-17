package com.engeto.hotel;

public enum VacationType {
    RECREATIONAL,
    WORK,
    UNKNOWN;

    @Override
    public String toString() {
        switch (this) {
            case RECREATIONAL:
                return "rekreační";
            case WORK:
                return "pracovní";
            case UNKNOWN:
                return "neuvedeno";
            default:
                return super.toString();
        }
    }
}