package edu.javacourse.city.repository;

import edu.javacourse.city.domain.Address;
import edu.javacourse.city.domain.Passport;

import java.util.List;

public interface PassportRepository {
    Passport save(Passport passport);
    Passport update(Long id, Passport address);
    void deleteById(Long id);
    Passport findById(Long id);
    List<Passport> findAll();
}
