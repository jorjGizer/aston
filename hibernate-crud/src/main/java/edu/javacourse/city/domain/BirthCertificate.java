package edu.javacourse.city.domain;

import org.eclipse.persistence.annotations.CacheIndex;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "ro_birth_certificate")
public class BirthCertificate extends AbstractEntity{
    @Column(name = "number_certificate")
    private String number;
    @Column(name = "issue_date")
    private LocalDate issueDate;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Person person;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private PersonMale father;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private PersonFemale mother;

    public PersonMale getFather() {
        return father;
    }

    public void setFather(PersonMale father) {
        this.father = father;
    }

    public PersonFemale getMother() {
        return mother;
    }

    public void setMother(PersonFemale mother) {
        this.mother = mother;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
