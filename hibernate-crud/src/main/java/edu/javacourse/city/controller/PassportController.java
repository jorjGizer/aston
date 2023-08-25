package edu.javacourse.city.web;

import edu.javacourse.city.domain.Address;
import edu.javacourse.city.domain.Passport;
import edu.javacourse.city.domain.Person;
import edu.javacourse.city.repository.PassportDao;
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
import java.util.List;

@Singleton
@Path("/passport")
public class PassportController {
    private static final Logger logger = LoggerFactory.getLogger(PassportController.class);
    private PassportDao dao;

    @PostConstruct
    public void init() {
        logger.info("Controller is created");
        dao = new PassportDao();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPassport() {
        try {
            List<Passport> all = dao.findAll();
            return all.toString();
        } catch (NoResultException ex) {
            return "К сожалению, список пуст =(";
        }
    }

    @GET
    @Path("/{passportId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPassportById(@PathParam("passportId") Long id) {
        try {
            Passport passport = dao.findById(id);
            return passport.toString();
        } catch (NoResultException ex) {
            return "К сожалению, такого паспорта нет =(";
        }
    }

    @DELETE
    @Path("/{passportId}")
    public String deletePassport(@PathParam(value = "passportId") Long id) {
        try {
            Passport passport = dao.findById(id);
            if (passport != null) {
                dao.deleteById(id);
            }
            return "Паспорт успешно удален";
        } catch (NoResultException ex) {
            return "Не найден паспорт для удаления =(";
        }
    }

    @PUT
    @Path("{passportId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updatePassport(@PathParam(value = "passportId") String passportId,
                                 @QueryParam(value = "series") String series,
                                 @QueryParam(value = "number") String number,
                                 @QueryParam(value = "issueDepartment") String issueDepartment,
                                 @QueryParam(value = "issueDate") String issueDate) {

        Passport update = null;
        try {
            Passport byId = dao.findById(Long.parseLong(passportId));
            if (byId != null) {
                byId = initializePassport(series, number, issueDate, issueDepartment, byId);
                update = dao.update(Long.parseLong(passportId), byId);
            }
            return update.toString();
        } catch (NoResultException | NullPointerException ex) {
            return "Не найден паспорт для обновления =(";
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createPassport(@QueryParam(value = "series") String series,
                                 @QueryParam(value = "number") String number,
                                 @QueryParam(value = "issueDepartment") String issueDepartment,
                                 @QueryParam(value = "issueDate") String issueDate,
                                 @QueryParam(value = "personId") String personId) {
        Passport passport = new Passport();
        passport = initializePassport(series, number, issueDate, issueDepartment, passport);
        PersonDao personDao = new PersonDao();
        Person person = personDao.findById(Long.parseLong(personId));
        passport.setPerson(person);
        dao.save(passport);
        return "Добавили новый паспорт";
    }

    public Passport initializePassport(String series, String number, String issueDate, String issueDepartment, Passport passport) {
        if (series != null) {
            passport.setSeries(series);
        }
        if (number!= null) {
            passport.setNumber(number);
        }
        if (issueDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
            LocalDate issueDate2 = LocalDate.parse(issueDate, formatter);
            passport.setIssueDate(issueDate2);
        }
        if (issueDepartment != null) {
            passport.setIssueDepartment(issueDepartment);
        }
        return passport;
    }
}
