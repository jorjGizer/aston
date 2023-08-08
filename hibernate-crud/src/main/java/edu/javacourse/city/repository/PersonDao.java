package edu.javacourse.city.repository;

import edu.javacourse.city.domain.Person;
import edu.javacourse.city.domain.PersonFemale;
import edu.javacourse.city.exception.PersonCheckException;
import edu.javacourse.city.exception.PersonTransactionException;
import edu.javacourse.city.repository.PersonRepository;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PersonDao implements PersonRepository {
    private EntityManager entityManager;
    private static final String findAllHql = "" +
            "SELECT DISTINCT p FROM Person p " +
            "LEFT JOIN FETCH p.address a " +
            "LEFT JOIN FETCH p.passports ps " +
            "LEFT JOIN FETCH p.birthCertificates bc";
    private static final String findByIdHql = "" +
            "SELECT DISTINCT p FROM Person p " +
            "LEFT JOIN FETCH p.address a " +
            "LEFT JOIN FETCH p.passports ps " +
            "LEFT JOIN FETCH p.birthCertificates bc WHERE p.id=:id";
    private static final String updateHql = "" +
            "UPDATE Person p " +
            "SET p.dateOfBirth=:dateOfBirth, p.givenName=:givenName, p.patronymic=:patronymic, p.surName=:surName " +
            "WHERE p.id=:id";
    private static final String saveHql = "" +
            "INSERT INTO Person (dateOfBirth, givenName, surName, patronymic) " +
            "SELECT (:dateOfBirth, :givenName, :surName, :patronymic)";
    public PersonDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence");
        entityManager = factory.createEntityManager();
    }

    @Override
    public Person save(Person person) {
        TypedQuery<Person> query;
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(person);
//             query = entityManager.createQuery(saveHql, Person.class);
//             query.setParameter("dateOfBirth", person.getDateOfBirth());
//             query.setParameter("givenName", person.getGivenName());
//             query.setParameter("surName", person.getSurName());
//             query.setParameter("patronymic", person.getPatronymic());
            entityManager.getTransaction().commit();
        } catch (PersonTransactionException ex) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Failed to save person: " + ex.getMessage(), ex);
        }
        return person;
    }

    @Override
    public Person update(Long id, Person person) {
        try {
            entityManager.getTransaction().begin();
//            query = entityManager.createQuery(updateHql)
//                    .setParameter("dateOfBirth", person.getDateOfBirth())
//                    .setParameter("givenName", person.getGivenName())
//                    .setParameter("surName", person.getSurName())
//                    .setParameter("patronymic", person.getPatronymic())
//                    .setParameter("id", person.getId());
//             query.executeUpdate();
            person = entityManager.merge(person);
            entityManager.getTransaction().commit();
        } catch (PersonTransactionException ex) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Failed to update person: " + ex.getMessage(), ex);
        }
        return person;
    }

    @Override
    public void deleteById(Long id) {
        Person person = findById(id);
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(person);
            entityManager.getTransaction().commit();
        } catch (PersonTransactionException ex) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Failed to delete person: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Person findById(Long id){
        TypedQuery<Person> query;
            query = entityManager.createQuery(findByIdHql, Person.class);
            query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Person> findAll() {
        List<Person> resultList = entityManager.createQuery(findAllHql, Person.class).getResultList();
        return resultList;
    }
}
