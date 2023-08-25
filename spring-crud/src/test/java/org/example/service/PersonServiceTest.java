package org.example.service;

import org.example.config.DBConfig;
import org.example.config.SpringConfig;
import org.example.entity.Person;
import org.example.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PersonServiceTest {
    @Test
    @Transactional
    public void testYourServiceMethod() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class)) {
            PersonRepository repository = context.getBean(PersonRepository.class);
            List<Person> all = repository.findAll();
            all.forEach(System.out::println);
        }
    }
    @Test
    void getPersons() {
    }

    @Test
    void getPersonsById() {
    }
}