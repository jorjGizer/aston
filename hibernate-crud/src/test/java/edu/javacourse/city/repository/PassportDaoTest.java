package edu.javacourse.city.repository;

import edu.javacourse.city.domain.Address;
import edu.javacourse.city.domain.Passport;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class PassportDaoTest {

    @Test
    public void save() {
        PassportDao passDao = new PassportDao();
        PersonDao persDao = new PersonDao();
        Passport passport = new Passport();
        passport.setPerson(persDao.findById(2L));
        passport.setIssueDepartment("wedrfgtyh");
        passport.setNumber("123");
        passport.setSeries("2134");
        passport.setIssueDate(LocalDate.of(2018, 7, 30));
        passDao.save(passport);
    }
    @Test
    public void delete(){
        PassportDao dao = new PassportDao();
        dao.deleteById(4L);

    }
}