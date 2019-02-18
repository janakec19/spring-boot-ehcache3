package com.example.demo.ehcache3.entity;

public class Country {

    private final String code;

    public Country(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Country country = (Country) o;

        return this.code.equals(country.code);
    }

    @Override
    public int hashCode() {
        return this.code.hashCode();
    }


}
