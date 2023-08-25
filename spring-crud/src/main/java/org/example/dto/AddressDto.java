package org.example.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.entity.Person;

public class AddressDto {
    private Long id;
    private String building;
    private String apartment;
    @JsonProperty("person")
    private AddressPersonDto addressPersonDto;

    public AddressPersonDto getAddressPersonDto() {
        return addressPersonDto;
    }

    public void setAddressPersonDto(AddressPersonDto addressPersonDto) {
        this.addressPersonDto = addressPersonDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

}
