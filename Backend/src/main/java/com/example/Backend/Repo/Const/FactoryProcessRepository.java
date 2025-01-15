package com.example.Backend.Repo.Const;

import com.example.Backend.Model.Const.FactoryProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactoryProcessRepository extends JpaRepository<FactoryProcess, Long> {
    List<FactoryProcess> findAllByOrderByIdAsc();
}
