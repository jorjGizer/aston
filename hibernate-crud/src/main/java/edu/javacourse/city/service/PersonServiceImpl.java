package edu.javacourse.city.repository;

import edu.javacourse.city.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@s
public class PersonService implements PersonRepository {
    @Autowired
    private PersonRepository repository;
    @Override
    @Transactional
    public Person save(Person person) {
            entityManager.persist(person);
        return person;
    }

    @Override
    @Transactional
    public Person update(Long id, Person person) {
            person = entityManager.merge(person);
        return person;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
            Query query = entityManager.createQuery("DELETE FROM Person p WHERE p.id=:id");
            query.setParameter("id", id);
            query.executeUpdate();
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
