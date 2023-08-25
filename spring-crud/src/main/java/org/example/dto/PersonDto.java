package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import org.example.entity.Address;
import org.example.entity.LocalDateDeserializer;
import org.example.entity.LocalDateSerializer;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class PersonDto {
    private Long id;
    private String surName;
    private String patronymic;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfBirth;
    @JsonProperty("address")
    private Set<PersonAddressDto> addresses;

    public Set<PersonAddressDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<PersonAddressDto> addresses) {
        this.addresses = addresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
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

}
