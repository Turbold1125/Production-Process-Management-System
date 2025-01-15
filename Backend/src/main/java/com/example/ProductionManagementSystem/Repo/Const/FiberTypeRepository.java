package com.example.ProductionManagementSystem.Repo.Const;

import com.example.ProductionManagementSystem.Model.Const.FiberType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FiberTypeRepository extends JpaRepository<FiberType, Integer> {

    Optional<FiberType> findByName(String name);
}
