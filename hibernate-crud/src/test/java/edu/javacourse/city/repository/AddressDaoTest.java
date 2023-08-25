package edu.javacourse.city.repository;

import edu.javacourse.city.domain.Address;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AddressDaoTest {
    @Test
    public void save() {
        AddressDao aDao = new AddressDao();
        PersonDao pDao = new PersonDao();
        Address address = new Address();
        address.setPerson(pDao.findById(2L));
        address.setExtension("1");
        address.setStreetCode(2);
        address.setBuilding("4");
        address.setApartment("28");
        aDao.save(address);
    }

    @Test
    public void findAll() {
        AddressDao dao = new AddressDao();
        List<Address> all = dao.findAll();
        all.forEach(System.out::println);
    }
    @Test
    public void deleteById(){
        AddressDao dao = new AddressDao();
        dao.deleteById(4L);
        findAll();
    }

}