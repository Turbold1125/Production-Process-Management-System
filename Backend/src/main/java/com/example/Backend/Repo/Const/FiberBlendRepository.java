package com.example.Backend.Repo.Const;

import com.example.Backend.Model.Fibers.FiberBlend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FiberBlendRepository extends JpaRepository<FiberBlend, Long> {
    List<FiberBlend> findByOrderId(Long orderId);
}
