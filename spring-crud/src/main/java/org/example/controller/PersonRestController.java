package org.example.controller;

import org.example.dto.PersonDto;
import org.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonRestController {

    @Autowired
    private PersonService service;
    @GetMapping
    public ResponseEntity<List<PersonDto>> findPersons() {
            List<PersonDto> persons = service.getPersons();
            return ResponseEntity.ok(persons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPersonById(@PathVariable Long id) {
            PersonDto person = service.getPersonsById(id);
            return ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<?> savePerson(@RequestBody PersonDto personDto) {
            PersonDto savedPerson = service.savePerson(personDto);
            return ResponseEntity.ok(savedPerson);
    }

    @PatchMapping
    public ResponseEntity<?> updateAddress(@RequestBody PersonDto personDto) {
        long id = personDto.getId();
        PersonDto updatedPerson = service.updatePerson(personDto, id);
            return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
            service.deletePerson(id);
            return ResponseEntity.notFound().build();
    }
}
