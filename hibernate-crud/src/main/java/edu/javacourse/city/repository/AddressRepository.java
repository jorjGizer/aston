package edu.javacourse.city.repository;

import edu.javacourse.city.domain.Address;
import edu.javacourse.city.domain.Person;

import java.util.List;

public interface AddressRepository {
    Address save(Address address);
    Address update(Long id, Address address);
    void deleteById(Long id);
    Address findById(Long id);
    List<Address> findAll();
}
