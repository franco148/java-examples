package com.eextreme.tdd.legacycode;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanApplication, Integer> {

}
