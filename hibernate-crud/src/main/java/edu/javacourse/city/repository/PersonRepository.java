package edu.javacourse.city.repository;

import edu.javacourse.city.domain.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    Person save(Person person);
    Person update(Long id, Person person);
    void deleteById(Long id);
    Person findById(Long id);
    List<Person> findAll();
}
