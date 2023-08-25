package edu.javacourse.city.repository;

import edu.javacourse.city.domain.*;
import edu.javacourse.city.repository.PersonDao;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class PersonDaoTest {

    @Test
    public void findPersons() {
        PersonDao dao = new PersonDao();
        List<Person> persons = dao.findAll();
        persons.forEach(System.out::println);
    }
    @Test
    public void findPersonById(){
        PersonDao dao = new PersonDao();
        Person person = dao.findById(1L);
        System.out.println(person);
        Person person2 = dao.findById(5L);
        System.out.println(person2);
        System.out.println(person2.getAddress().size());
    }
    @Test
    public void addNewPerson(){
        PersonDao dao = new PersonDao();
        Person newPerson = createNewPerson();
         dao.save(newPerson);
        List<Person> all = dao.findAll();
        all.forEach(System.out::println);
    }
    public Person createNewPerson(){
        Person person = new PersonMale();
        person.setSurName("Георгий");
        person.setGivenName("Баканчев");
        person.setPatronymic("Вадимович");
        person.setDateOfBirth(LocalDate.of(1995, 6, 27));

        Passport passport = new Passport();
        passport.setPerson(person);
        passport.setIssueDate(LocalDate.of(2018, 6, 27));
        passport.setNumber("132456578900");
        passport.setSeries("12345");
        passport.setIssueDepartment("отдел1");

        BirthCertificate birthCertificate = new BirthCertificate();
        birthCertificate.setPerson(person);
        birthCertificate.setNumber("13435676");
        birthCertificate.setIssueDate(LocalDate.of(2018, 8, 16));

        person.setPassports(Set.of(passport));
        person.setBirthCertificates(Set.of(birthCertificate));
        return person;
    }
   @Test
    public void deletePersonFromId(){
        PersonDao dao = new PersonDao();
        dao.deleteById(1L);
        dao.deleteById(2L);
        dao.deleteById(3L);
        dao.deleteById(4L);
    }
    @Test
    public void updatePerson(){
        PersonDao dao = new PersonDao();
        Person person = new PersonFemale();
        person.setSurName("uiytree");
        person.setGivenName("yujhnbgvfc");
        person.setDateOfBirth(LocalDate.of(1100, 8, 10));
        person.setPatronymic("kuygffd");
        dao.update(5L, person);
        dao.findById(5L);
        System.out.println(dao.findById(5L));
    }

}