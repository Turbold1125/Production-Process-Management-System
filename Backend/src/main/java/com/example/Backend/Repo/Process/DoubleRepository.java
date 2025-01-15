package com.example.Backend.Repo.Process;

import com.example.Backend.Model.Process.ProcessDouble;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoubleRepository extends JpaRepository<ProcessDouble, Long> {
}
