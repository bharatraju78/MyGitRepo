package com.vam.cco.util;

public enum OrganizationLevel {
    SELECT("Select"),
    ORGANIZATION_LEVEL("Organization Level");

    private final String label;

    OrganizationLevel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}