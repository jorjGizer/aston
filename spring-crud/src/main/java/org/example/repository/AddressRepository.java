package org.example.repository;

import org.example.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(value = "select person_id from address where address.id=?1", nativeQuery = true)
    public Long getPersonIdFromAddress(Long id);
}
