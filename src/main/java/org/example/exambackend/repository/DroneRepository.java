package org.example.exambackend.repository;

import org.example.exambackend.entity.Drone;
import org.example.exambackend.entity.DroneStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DroneRepository extends JpaRepository<Drone, Long> {

    Optional<Drone> findFirstByStatus(DroneStatus status);

}
