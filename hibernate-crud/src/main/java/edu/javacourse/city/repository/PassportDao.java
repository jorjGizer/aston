package edu.javacourse.city.repository;

import edu.javacourse.city.domain.Passport;
import edu.javacourse.city.exception.PersonTransactionException;

import javax.persistence.*;
import java.util.List;

public class PassportDao implements PassportRepository{
    private EntityManager entityManager;
    private EntityManagerFactory factory;
    private static final String findAllHql = "SELECT DISTINCT p FROM Passport p";
    private static final String findByIdHql = "" +
            "SELECT DISTINCT p FROM Passport p WHERE p.id=:id";
    private static final String updateHql = "" +
            "UPDATE Passport p " +
            "SET p.apartment=:apartment, p.building=:building, p.extension=:extension, p.streetCode=:streetCode " +
            "WHERE p.id=:id";
    public PassportDao() {
        factory = Persistence.createEntityManagerFactory("persistence");
        entityManager = factory.createEntityManager();
    }

    @Override
    public Passport save(Passport passport) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(passport);
            entityManager.getTransaction().commit();
        } catch (PersonTransactionException ex) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Failed to save passport: " + ex.getMessage(), ex);
        }
        return passport;
    }

    @Override
    public Passport update(Long id, Passport passport) {
        try {
            entityManager.getTransaction().begin();
//            TypedQuery<passport> query = entityManager.createQuery(updateHql, passport.class);
//            query.setParameter("apartment", passport.getApartment());
//            query.setParameter("building", passport.getBuilding());
//            query.setParameter("extension", passport.getExtension());
//            query.setParameter("streetCode", passport.getStreetCode());
//            query.setParameter("id", passport.getId());
            passport = entityManager.merge(passport);
            entityManager.getTransaction().commit();
        } catch (PersonTransactionException ex) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Failed to update passport: " + ex.getMessage(), ex);
        }
        return passport;
    }

    @Override
    public void deleteById(Long id) {
        try {
            entityManager.getTransaction().begin();
            Passport passport = findById(id);
            if (passport!=null) {
                Query query = entityManager.createQuery("DELETE from Passport where id=:id");
                query.setParameter("id", id);
                query.executeUpdate();
                entityManager.getTransaction().commit();
            }
        } catch (PersonTransactionException ex) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Failed to delete passport: " + ex.getMessage(), ex);
        }
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
