package com.example.Backend.Repo.Process;

import com.example.Backend.Model.Process.DyeingProcess;
import com.example.Backend.Model.Process.ProcessDye;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DyeingProcessRepository extends JpaRepository<DyeingProcess, Long> {
}