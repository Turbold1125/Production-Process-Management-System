package com.example.ProductionManagementSystem.Repo;

import com.example.ProductionManagementSystem.Model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository  extends JpaRepository<Batch, Integer> {
}