package org.example.exambackend.repository;

import org.example.exambackend.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByActualDeliveryTimeIsNull();

    List<Delivery> findByDroneIsNull();

}
