package com.example.ProductionManagementSystem.Repo.Const;

import com.example.ProductionManagementSystem.Model.Const.FactoryProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactoryProcessRepository extends JpaRepository<FactoryProcess, Integer> {
    List<FactoryProcess> findAllByOrderByIdAsc();
    FactoryProcess findByName(String name);
}