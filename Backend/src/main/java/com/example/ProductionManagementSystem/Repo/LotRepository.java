package com.example.ProductionManagementSystem.Repo;

import com.example.ProductionManagementSystem.Model.Lot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotRepository extends JpaRepository<Lot, Integer> {
}
