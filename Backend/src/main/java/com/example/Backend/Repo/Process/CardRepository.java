package com.example.Backend.Repo.Process;

import com.example.Backend.Model.Process.ProcessCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<ProcessCard, Long> {
}
