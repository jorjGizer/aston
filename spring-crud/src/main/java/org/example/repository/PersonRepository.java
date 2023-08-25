package org.example.repository;
import org.example.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query(value = "SELECT p from Person p LEFT JOIN FETCH p.addresses")
    List<Person> findAll();
    @Query(value = "SELECT p from Person p LEFT JOIN FETCH p.addresses where p.id=?1")
    Optional<Person> findById(Long id);
}
