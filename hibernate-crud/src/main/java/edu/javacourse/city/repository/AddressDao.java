package edu.javacourse.city.repository;

import edu.javacourse.city.domain.Address;
import edu.javacourse.city.domain.Person;
import edu.javacourse.city.exception.PersonTransactionException;

import javax.persistence.*;
import java.util.List;

public class AddressDao implements AddressRepository{
    private EntityManager entityManager;
    private EntityManagerFactory factory;
    private static final String findAllHql = "SELECT DISTINCT a FROM Address a";
    private static final String findByIdHql = "" +
            "SELECT DISTINCT a FROM Address a WHERE a.id=:id";
    private static final String updateHql = "" +
            "UPDATE Address a " +
            "SET a.apartment=:apartment, a.building=:building, a.extension=:extension, a.streetCode=:streetCode " +
            "WHERE a.id=:id";
    public AddressDao() {
        factory = Persistence.createEntityManagerFactory("persistence");
        entityManager = factory.createEntityManager();
    }

    @Override
    public Address save(Address address) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(address);
            entityManager.getTransaction().commit();
        } catch (PersonTransactionException ex) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Failed to save address: " + ex.getMessage(), ex);
        }
        return address;
    }

    @Override
    public Address update(Long id, Address address) {
        try {
            entityManager.getTransaction().begin();
//            TypedQuery<Address> query = entityManager.createQuery(updateHql, Address.class);
//            query.setParameter("apartment", address.getApartment());
//            query.setParameter("building", address.getBuilding());
//            query.setParameter("extension", address.getExtension());
//            query.setParameter("streetCode", address.getStreetCode());
//            query.setParameter("id", address.getId());
            address = entityManager.merge(address);
            entityManager.getTransaction().commit();
        } catch (PersonTransactionException ex) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Failed to update address: " + ex.getMessage(), ex);
        }
        return address;
    }

    @Override
    public void deleteById(Long id) {
        try {
            entityManager.getTransaction().begin();
            Address address = findById(id);
            if (address!=null) {
                Query query = entityManager.createQuery("DELETE from Address where id=:id");
                query.setParameter("id", id);
                query.executeUpdate();
                entityManager.getTransaction().commit();
            }
        } catch (PersonTransactionException ex) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Failed to delete address: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Address findById(Long id) {
        TypedQuery<Address> query = entityManager.createQuery(findByIdHql, Address.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Address> findAll() {
        return entityManager.createQuery(findAllHql, Address.class).getResultList();
    }
}
