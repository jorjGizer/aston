package edu.javacourse.city.domain;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "ro_passport")
//@SequenceGenerator(name = "default_gen", sequenceName = "ro_passport_seq", allocationSize = 1)
public class Passport extends AbstractEntity{
    @Column(name = "series")
    private String series;
    @Column(name = "number")
    private String number;
    @Column(name = "issue_date")
    private LocalDate issueDate;
    @Column(name = "issue_department")
    private String issueDepartment;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
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

    public String getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(String issueDepartment) {
        this.issueDepartment = issueDepartment;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "series='" + series + '\'' +
                ", number='" + number + '\'' +
                ", issueDate=" + issueDate +
                ", issueDepartment='" + issueDepartment + '\'' +
                '}';
    }
}
