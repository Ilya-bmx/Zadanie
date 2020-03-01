package com.project.repos;

import com.project.entities.SalePointSpecific;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalePointSpecificRepository extends JpaRepository<SalePointSpecific,Integer> {
}
