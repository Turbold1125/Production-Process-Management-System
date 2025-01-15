package com.example.Backend.Repo.Process;

import com.example.Backend.Model.Process.ProcessWind;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WindRepository extends JpaRepository<ProcessWind, Long> {
}