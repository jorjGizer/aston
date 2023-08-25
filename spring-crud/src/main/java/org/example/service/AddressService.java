package org.example.service;

import org.example.dto.AddressDto;
import org.example.dto.AddressPersonDto;
import org.example.entity.Address;
import org.example.entity.Person;
import org.example.exception.NotFoundException;
import org.example.repository.AddressRepository;
import org.example.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
public class AddressService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AddressRepository addressRepo;
    @Autowired
    private PersonRepository personRepo;

    public List<AddressDto> getAddresses() {
        List<Address> addresses = addressRepo.findAll();
        List<Person> persons = addresses.stream()
                .map(a -> addressRepo.getPersonIdFromAddress(a.getId()))
                .map(id -> personRepo.findById(id).get())
                .toList();
        List<AddressDto> addressDtos = IntStream.range(0, Math.min(addresses.size(), persons.size()))
                .mapToObj(i -> {
                    Address address = addresses.get(i);
                    Person person = persons.get(i);

                    address.setPerson(person);

                    AddressDto addressDto = mapper.map(address, AddressDto.class);
                    AddressPersonDto addressPersonDto = mapper.map(person, AddressPersonDto.class);
                    addressDto.setAddressPersonDto(addressPersonDto);

                    return addressDto;
                })
                .collect(Collectors.toList());

        return addressDtos;

    }

    public AddressDto getAddressesById(Long id) {
        Address address = addressRepo.findById(id).get();
        Long personIdFromAddress = addressRepo.getPersonIdFromAddress(id);
        Person person = personRepo.findById(personIdFromAddress).get();
        AddressDto addressDto = mapper.map(address, AddressDto.class);
        addressDto.setAddressPersonDto(mapper.map(person, AddressPersonDto.class));
        return addressDto;
    }

    public AddressDto saveAddress(AddressDto addressDto, Long id) {
        Person person = personRepo.findById(id).orElseThrow(() -> new NotFoundException("Person not found"));
        Address address = mapper.map(addressDto, Address.class);
        address.setPerson(person);
        address = addressRepo.save(address);

        AddressDto savedAddressDto = mapper.map(address, AddressDto.class);
        AddressPersonDto addressPersonDto = mapper.map(person, AddressPersonDto.class);
        savedAddressDto.setAddressPersonDto(addressPersonDto);

        return savedAddressDto;
    }
    public AddressDto updateAddress(AddressDto addressDto, Long id) {
        Address oldAddress = addressRepo.findById(id).orElseThrow(() -> new NotFoundException("Address not found"));

        Address newAddress = mapper.map(addressDto, Address.class);
        oldAddress.setApartment(newAddress.getApartment());
        oldAddress.setBuilding(newAddress.getBuilding());

        Long personIdFromAddress = addressRepo.getPersonIdFromAddress(id);
        Person person = personRepo.findById(personIdFromAddress)
                .orElseThrow(() -> new NotFoundException("Person not found"));

        oldAddress.setPerson(person);

        Address updatedAddress = addressRepo.save(oldAddress);

        AddressDto updatedAddressDto = mapper.map(updatedAddress, AddressDto.class);
        AddressPersonDto addressPersonDto = mapper.map(person, AddressPersonDto.class);
        updatedAddressDto.setAddressPersonDto(addressPersonDto);

        return updatedAddressDto;
    }

//    public AddressDto updateAddress(AddressDto addressDto, Long id) {
//        Address oldAddress = addressRepo.findById(id).get();
//        Address newAddress = mapper.map(addressDto, Address.class);
//        Long personIdFromAddress = addressRepo.getPersonIdFromAddress(id);
//        Person person = personRepo.findById(personIdFromAddress).get();
//        oldAddress.setApartment(newAddress.getApartment());
//        oldAddress.setBuilding(newAddress.getBuilding());
//        Address saveAddress = addressRepo.save(oldAddress);
//        addressDto = mapper.map(saveAddress, AddressDto.class);
//        addressDto.setAddressPersonDto(mapper.map(person, AddressPersonDto.class));
//        return mapper.map(saveAddress, AddressDto.class);
//    }


    public void deleteAddress(Long id) {
        addressRepo.deleteById(id);
    }
}
