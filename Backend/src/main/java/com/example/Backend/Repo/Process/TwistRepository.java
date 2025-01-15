package com.example.Backend.Repo.Process;

import com.example.Backend.Model.Process.ProcessSpin;
import com.example.Backend.Model.Process.ProcessTwist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwistRepository extends JpaRepository<ProcessTwist, Long> {
}
