//package com.example.Backend.Repo.Fibers;
//
//import com.example.Backend.Model.Fibers.Fiber;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface FiberReceiveRepository extends JpaRepository<Fiber, Long> {
//    List<Fiber> findByOrderId(Long orderId);
//
//    @Query("SELECT COALESCE(SUM(f.rough_weight), 0) FROM FiberReceive f WHERE f.order.id = :orderId")
//    double calculateTotalReceivedWeightByOrderId(@Param("orderId") Long orderId);
//
//    @Query("SELECT COALESCE(MAX(f.baleNum), 0) FROM FiberReceive f WHERE f.order.id = :orderId")
//    int findLastBaleNumByOrderId(@Param("orderId") Long orderId);
//}