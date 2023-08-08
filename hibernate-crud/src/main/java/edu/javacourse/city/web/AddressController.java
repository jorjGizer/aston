package edu.javacourse.city.web;

import edu.javacourse.city.domain.Address;
import edu.javacourse.city.domain.Person;
import edu.javacourse.city.repository.AddressDao;
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
@Path("/address")
public class AddressController {
    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
    private AddressDao dao;

    @PostConstruct
    public void init() {
        logger.info("Controller is created");
        dao = new AddressDao();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllAddress() {
        try {
            List<Address> all = dao.findAll();
            return all.toString();
        } catch (NoResultException ex) {
            return "К сожалению, список пуст =(";
        }
    }

    @GET
    @Path("/{addressId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAddressById(@PathParam("addressId") Long id) {
        try {
            Address address = dao.findById(id);
            return address.toString();
        } catch (NoResultException ex) {
            return "К сожалению, такого адреса нет =(";
        }
    }
    @DELETE
    @Path("/{addressId}")
    public String deleteAddress(@PathParam(value = "addressId") Long id) {
        try {
            Address address = dao.findById(id);
            if (address!=null) {
                dao.deleteById(id);
            }
            return "Адрес успешно удален";
        } catch (NoResultException ex) {
            return "Не найден адрес для удаления =(";
        }
    }

    @PUT
    @Path("{addressId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateAddress(@PathParam(value = "addressId") String addressId,
                               @QueryParam(value = "apartment") String apartment,
                               @QueryParam(value = "building") String building,
                               @QueryParam(value = "extension") String extension,
                               @QueryParam(value = "streetCode") String streetCode){

        Address update = null;
        try{
            Address byId = dao.findById(Long.parseLong(addressId));
            if(byId!=null) {
                byId = initializeAddress(apartment, building, extension, streetCode, byId);
                update = dao.update(Long.parseLong(addressId), byId);
            }
            return update.toString();
        } catch (NoResultException | NullPointerException ex) {
            return "Не найден адрес для обновления =(";
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createAddress(@QueryParam(value = "apartment") String apartment,
                                @QueryParam(value = "building") String building,
                                @QueryParam(value = "extension") String extension,
                                @QueryParam(value = "streetCode") String streetCode,
                                @QueryParam(value = "personId") String personId) {
        Address address =  new Address();
        address = initializeAddress(apartment, building, extension, streetCode, address);
        PersonDao personDao = new PersonDao();
        Person person = personDao.findById(Long.parseLong(personId));
        address.setPerson(person);
        dao.save(address);
        return "Добавили новый address";
    }
    public Address initializeAddress(String apartment, String building, String extension, String streetCode, Address address){
        if (apartment!= null) {
            address.setApartment(apartment);
        }
        if (building != null) {
            address.setBuilding(building);
        }
        if (extension != null) {
            address.setExtension(extension);
        }
        if(streetCode != null){
            address.setStreetCode(Integer.parseInt(streetCode));
        }
        return address;
    }
}
