package edu.javacourse.city.domain;


import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ro_person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "sex", discriminatorType = DiscriminatorType.INTEGER)
public class Person extends AbstractEntity{
    @Column(name = "sur_name")
    private String surName;
    @Column(name = "given_name")
    private String givenName;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "date_of_birth")
    //@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate dateOfBirth;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER,
            mappedBy = "person", orphanRemoval = true)
    private Set<Address> address;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER,
            mappedBy = "person", orphanRemoval = true)
    private Set<Passport> passports;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER,
            mappedBy = "person",orphanRemoval = true)
    private Set<BirthCertificate> birthCertificates;

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }

    public Set<Passport> getPassports() {
        return passports;
    }

    public void setPassports(Set<Passport> passports) {
        this.passports = passports;
    }

    public Set<BirthCertificate> getBirthCertificates() {
        return birthCertificates;
    }

    public void setBirthCertificates(Set<BirthCertificate> birthCertificates) {
        this.birthCertificates = birthCertificates;
    }

    @Override
    public String toString() {
        return "Person{" +
                "surName='" + surName + '\'' +
                ", givenName='" + givenName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address=" + address +
                ", passports=" + passports +
                ", birthCertificates=" + birthCertificates +
                '}';
    }

}
