package com.example.Backend.Repo.Process;

import com.example.Backend.Model.Process.ProcessDye;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DyeRepository extends JpaRepository<ProcessDye, Long> {
}
