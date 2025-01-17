package org.example.exambackend.repository;

import org.example.exambackend.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StationRepository extends JpaRepository<Station, Long> {

    @Query("SELECT s FROM Station s WHERE size(s.drones) = " +
            "(SELECT MIN(size(s2.drones)) FROM Station s2)")
    List<Station> findStationsWithFewestDrones();
}
