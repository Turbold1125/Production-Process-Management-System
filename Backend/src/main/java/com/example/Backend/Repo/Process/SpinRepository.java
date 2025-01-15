package com.example.Backend.Repo.Process;

import com.example.Backend.Model.Process.ProcessSpin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpinRepository extends JpaRepository<ProcessSpin, Long> {
}