package com.subham.ATM.repository;

import com.subham.ATM.model.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface AccountRepository extends JpaRepository<AccountHolder, Integer> {



    @Query(value = "SELECT * FROM customers a where a.name=?1", nativeQuery = true)
    Optional<AccountHolder> findByName(String name);

}
