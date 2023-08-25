package org.example.entity;

import jakarta.persistence.*;

@Entity
public class Address extends AbstractEntity{
    private String building;
    private String apartment;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Person person;

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Address{" +
                "building='" + building + '\'' +
                ", apartment='" + apartment + '\'' +
                '}';
    }
}
