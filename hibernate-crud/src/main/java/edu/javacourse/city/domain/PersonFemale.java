package edu.javacourse.city.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("2")
public class PersonFemale extends Person{
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY,
    mappedBy = "wife")
    private Set<MarriageCertificate> marriageCertificates;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY,
    mappedBy = "mother")
    private Set<BirthCertificate> birthCertificates;

    public Set<MarriageCertificate> getMarriageCertificates() {
        return marriageCertificates;
    }

    public void setMarriageCertificates(Set<MarriageCertificate> marriageCertificates) {
        this.marriageCertificates = marriageCertificates;
    }

    @Override
    public Set<BirthCertificate> getBirthCertificates() {
        return birthCertificates;
    }

    @Override
    public void setBirthCertificates(Set<BirthCertificate> birthCertificates) {
        this.birthCertificates = birthCertificates;
    }
}
