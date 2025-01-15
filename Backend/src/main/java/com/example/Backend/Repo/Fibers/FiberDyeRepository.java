package com.example.Backend.Repo.Fibers;

import com.example.Backend.Model.Fibers.FiberDyed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FiberDyeRepository extends JpaRepository<FiberDyed, Long> {
    List<FiberDyed> findByOrderId(Long orderId);
}
