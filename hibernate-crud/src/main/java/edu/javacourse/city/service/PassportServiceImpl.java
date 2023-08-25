package edu.javacourse.city.repository;

import edu.javacourse.city.domain.Passport;
import edu.javacourse.city.exception.PersonTransactionException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
@Repository
public class PassportDao implements PassportRepository{
    @PersistenceContext(unitName = "persistence")
    private EntityManager entityManager;
    private static final String findAllHql = "SELECT DISTINCT p FROM Passport p";
    private static final String findByIdHql = "" +
            "SELECT DISTINCT p FROM Passport p WHERE p.id=:id";
    private static final String updateHql = "" +
            "UPDATE Passport p " +
            "SET p.apartment=:apartment, p.building=:building, p.extension=:extension, p.streetCode=:streetCode " +
            "WHERE p.id=:id";

    @Override
    @Transactional
    public Passport save(Passport passport) {
            entityManager.persist(passport);
        return passport;
    }

    @Override
    @Transactional
    public Passport update(Long id, Passport passport) {
            passport = entityManager.merge(passport);
        return passport;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
                Query query = entityManager.createQuery("DELETE from Passport where id=:id");
                query.setParameter("id", id);
                query.executeUpdate();
    }

    @Override
    public Passport findById(Long id) {
        TypedQuery<Passport> query = entityManager.createQuery(findByIdHql, Passport.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Passport> findAll() {
        return entityManager.createQuery(findAllHql, Passport.class).getResultList();
    }
}
