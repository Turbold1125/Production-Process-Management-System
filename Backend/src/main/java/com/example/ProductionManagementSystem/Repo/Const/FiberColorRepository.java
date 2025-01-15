package com.example.ProductionManagementSystem.Repo.Const;

import com.example.ProductionManagementSystem.Model.Const.FiberColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FiberColorRepository extends JpaRepository<FiberColor, Integer> {
//    Optional<FiberColor> findByNames(String name);

    Optional<FiberColor> findByName(String name);
}
