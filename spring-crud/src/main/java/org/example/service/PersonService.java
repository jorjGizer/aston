package org.example.service;

import org.example.dto.AddressDto;
import org.example.dto.AddressPersonDto;
import org.example.dto.PersonAddressDto;
import org.example.dto.PersonDto;
import org.example.entity.Address;
import org.example.entity.Person;
import org.example.exception.NotFoundException;
import org.example.repository.AddressRepository;
import org.example.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Component
public class PersonService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PersonRepository personRepo;

    public List<PersonDto> getPersons() {
        List<Person> persons = personRepo.findAll();
        return persons.stream().map(p -> mapper.map(p, PersonDto.class)).collect(Collectors.toList());
    }

    public PersonDto savePerson(PersonDto personDto){
        Person person = personRepo.save(mapper.map(personDto, Person.class));
        return mapper.map(person, PersonDto.class );
    }
    public PersonDto getPersonsById(Long id) {
        return mapper.map(personRepo.findById(id).get(), PersonDto.class);
    }

    public PersonDto updatePerson(PersonDto personDto, Long id) {
        Person person = personRepo.findById(id).orElseThrow(() -> new NotFoundException("Person not found"));
        person.setId(id);
        person.setPatronymic(personDto.getPatronymic());
        person.setDateOfBirth(personDto.getDateOfBirth());
        person.setSurName(personDto.getSurName());

        person = personRepo.save(person);

        return mapper.map(person, PersonDto.class);
    }

    public void deletePerson(Long id) {
        personRepo.deleteById(id);
    }
}
