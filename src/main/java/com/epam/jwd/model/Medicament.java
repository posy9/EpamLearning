package com.epam.jwd.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Medicament {

    private final Integer id;
    private final String name;
    private final BigDecimal price;
    private final String category;
    private final String country;
    private final String information;
    private final int amount;

    public Medicament(String name, BigDecimal price, String category, String country, String information, int amount) {
        this.id = null;
        this.name = name;
        this.price = price;
        this.category = category;
        this.country = country;
        this.information = information;
        this.amount = amount;
    }

    private Medicament(int id, String name, BigDecimal price, String category, String country, String information, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.country = country;
        this.information = information;
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getInformation() {
        return information;
    }

    public Integer getId() {
        return id;
    }

    public Medicament withId(int id) {
        return new Medicament(id, name, price, category, country, information, amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicament that = (Medicament) o;
        return Objects.equals(id, that.id) && amount == that.amount && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(category, that.category) && Objects.equals(country, that.country) && Objects.equals(information, that.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category, country, information, amount);
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", country='" + country + '\'' +
                ", information='" + information + '\'' +
                ", amount=" + amount +
                '}';
    }
}
