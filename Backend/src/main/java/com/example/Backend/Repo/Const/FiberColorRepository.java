package com.example.Backend.Repo.Const;

import com.example.Backend.Model.Const.FiberColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FiberColorRepository extends JpaRepository<FiberColor, Long> {
    Optional<FiberColor> findByName(String name);
}
