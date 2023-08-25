package edu.javacourse.city.web;

import edu.javacourse.city.domain.*;
import edu.javacourse.city.repository.PersonDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
@Path("/person")
public class PersonController {
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
    private PersonDao dao;

    @PostConstruct
    public void init() {
        logger.info("Controller is created");
        dao = new PersonDao();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersons() {
        try {
            List<Person> all = dao.findAll();
            return all.toString();
        } catch (NoResultException ex) {
            return "К сожалению, список пуст =(";
        }
    }

    @GET
    @Path("/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonById(@PathParam("personId") Long id) {
        try {
            Person person = dao.findById(id);
            return person.toString();
        } catch (NoResultException ex) {
            return "К сожалению, такой сущности нет =(";
        }
    }

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> checkPerson() {
        Person pr = new Person();
        pr.setSurName("Васильев");
        pr.setGivenName("Павел");
        pr.setPatronymic("Николаевич");
        pr.setDateOfBirth(LocalDate.of(1995, 3, 18));
        Address address = new Address();
        Set<Address> addresses = new HashSet<>();
        address.setStreetCode(1);
        address.setBuilding("10");
        address.setExtension("2");
        address.setApartment("256");
        addresses.add(address);
        pr.setAddress(addresses);
        return List.of(pr);
    }

    @DELETE
    @Path("/{personId}")
    public String deletePerson(@PathParam(value = "personId") Long id) {
        try {
            Person person = dao.findById(id);
            if (person!=null) {
                dao.deleteById(id);
            }
            return "Person успешно удален";
        } catch (NoResultException ex) {
            return "Не найден Person для удаления =(";
        }
    }

    @PUT
    @Path("{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updatePerson(@PathParam(value = "personId") Long id,
                               @QueryParam(value = "surName") String surName,
                               @QueryParam(value = "givenName") String givenName,
                               @QueryParam(value = "patronymic") String patronymic,
                               @QueryParam(value = "dateOfBirth") String dateOfBirth){
        Person update = null;
        try{
            Person byId = dao.findById(id);
            Person newPerson = initializePerson(surName, givenName, patronymic, dateOfBirth, byId);
            if(byId!=null && newPerson!=null) {
                update = dao.update(id, newPerson);
            }
            return update.toString();
        } catch (NoResultException | NullPointerException ex) {
            return "Не найден Person для обновления =(";
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createPerson(@QueryParam(value = "sex") String sex,
                               @QueryParam(value = "surName") String surName,
                               @QueryParam(value = "givenName") String givenName,
                               @QueryParam(value = "patronymic") String patronymic,
                               @QueryParam(value = "dateOfBirth") String dateOfBirth) {
        Person person = null;
        if (sex!=null) {
            if (sex.equals("1")) {
                person = new PersonMale();
            } else {
                person = new PersonFemale();
            }
        }
       person = initializePerson(surName, givenName, patronymic, dateOfBirth, person);
            dao.save(person);
            return "Добавили нового Person";
        }
        public Person initializePerson(String surName, String givenName, String patronymic, String dateOfBirth, Person person){
            if (surName != null) {
                person.setSurName(surName);
            }
            if (givenName != null) {
                person.setGivenName(givenName);
            }
            if (patronymic != null) {
                person.setPatronymic(patronymic);
            }
            if (dateOfBirth != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
                LocalDate dateOfBirth2 = LocalDate.parse(dateOfBirth, formatter);
                person.setDateOfBirth(dateOfBirth2);
            }
            return person;
        }
}
