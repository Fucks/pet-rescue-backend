package org.fucks.petrescue.util;

import org.springframework.data.geo.Metric;

public enum Metrics implements Metric {
    METERS(6378137, "m");

    private final double multiplier;
    private final String abbreviation;

    private Metrics(double multiplier, String abbreviation) {

        this.multiplier = multiplier;
        this.abbreviation = abbreviation;
    }

    @Override
    public double getMultiplier() {
        return multiplier;
    }

    @Override
    public String getAbbreviation() {
        return abbreviation;
    }
}
