package com.example.Backend.Repo.Process;

import com.example.Backend.Model.Process.ProcessShip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<ProcessShip, Long> {
}

