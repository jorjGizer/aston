package org.example.service;

import org.example.config.DBConfig;
import org.example.config.SpringConfig;
import org.example.dto.AddressDto;
import org.example.entity.Address;
import org.example.entity.Person;
import org.example.repository.AddressRepository;
import org.example.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class AddressServiceTest {
    @Test
    @Transactional
    public void testYourServiceMethod() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class)) {
            AddressRepository repository = context.getBean(AddressRepository.class);
            PersonRepository personRepo = context.getBean(PersonRepository.class);
            Long personIdFromAddress = repository.getPersonIdFromAddress(1L);
            Assertions.assertEquals(personIdFromAddress, 1);
            Long personIdFromAddress2 = repository.getPersonIdFromAddress(2L);
            assertNull(personIdFromAddress2);
            Person person = personRepo.findById(personIdFromAddress).get();
            System.out.println(person.getId());
        }
    }
    @Test
    @Transactional
    public void getAddresses() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class)) {
            AddressRepository addressRepo = context.getBean(AddressRepository.class);
            PersonRepository personRepo = context.getBean(PersonRepository.class);
            List<Address> addresses = addressRepo.findAll();
            List<Person> persons = addresses.stream()
                    .map(a -> addressRepo.getPersonIdFromAddress(a.getId()))
                    .map(id -> personRepo.findById(id).get())
                    .toList();
            for (int i = 0; i < addresses.size(); i++) {
                addresses.get(i).setPerson(persons.get(i));
            }

        }
    }
}