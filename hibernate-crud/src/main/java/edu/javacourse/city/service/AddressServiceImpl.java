package edu.javacourse.city.service;

import edu.javacourse.city.domain.Address;
import edu.javacourse.city.domain.Person;
import edu.javacourse.city.exception.PersonTransactionException;
import edu.javacourse.city.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService implements AddressServiceRepo{
    @Autowired
    private AddressRepository repository;

    @Transactional
    public Address save(Address address) {
            repository.save(address);
        return address;
    }
    @Transactional
    public Address update(Long id, Address address) {
        Optional<Address> addressById = repository.findById(id);
        if(addressById.isPresent()){
            addressById.get().setStreetCode(address.getStreetCode());
            addressById.get().setExtension(address.getExtension());
            addressById.get().setBuilding(address.getBuilding());
            addressById.get().setApartment(address.getApartment());
        }
        repository.save(addressById.get());
        return addressById.get();
    }

    @Transactional
    public void deleteAddressById(Long id) {
         repository.deleteById(id);
    }

    public Address findAddressById(Long id) {
        Address addressById = repository.findAddressById(id);
        return addressById;
    }

    public List<Address> findAll() {
        return repository.findAll();
    }
}
