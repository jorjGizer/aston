package edu.javacourse.city.domain;

import javax.persistence.*;

@Entity
@Table(name = "ro_address")
public class Address extends AbstractEntity
{
    @Column(name = "building")
    private String building;
    @Column(name = "extension")
    private String extension;
    @Column(name = "street_code")
    private Integer streetCode;
    @Column(name = "apartment")
    private String apartment;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    public Integer getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(Integer streetCode) {
        this.streetCode = streetCode;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "Address{" +
                "building='" + building + '\'' +
                ", extension='" + extension + '\'' +
                ", streetCode=" + streetCode +
                ", apartment='" + apartment + '\'' +
                '}';
    }
}
