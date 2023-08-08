package edu.javacourse.city.domain;
import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "ro_marriage_certificate")
public class MarriageCertificate extends AbstractEntity{
    @Column(name = "number_certificate")
    private String number;
    @Embedded
    @Column(name = "local_date")
    private EmbeddedLocalDateCertificate localDate;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Person person;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private PersonMale husband;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private PersonFemale wife;
    @Column(name = "active")
    private boolean active;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public EmbeddedLocalDateCertificate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(EmbeddedLocalDateCertificate localDate) {
        this.localDate = localDate;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public PersonMale getHusband() {
        return husband;
    }

    public void setHusband(PersonMale husband) {
        this.husband = husband;
    }

    public PersonFemale getWife() {
        return wife;
    }

    public void setWife(PersonFemale wife) {
        this.wife = wife;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
