package com.subham.ATM.repository;

import com.subham.ATM.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository  extends JpaRepository<Balance,Integer> {
}
