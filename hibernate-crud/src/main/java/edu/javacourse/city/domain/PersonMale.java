package edu.javacourse.city.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("1")
public class PersonMale extends Person{
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,
            mappedBy = "husband")
    private Set<MarriageCertificate> marriageCertificates;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,
            mappedBy = "father")
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
