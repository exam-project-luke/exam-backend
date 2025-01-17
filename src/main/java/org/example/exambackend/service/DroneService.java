package org.example.exambackend.service;

import org.example.exambackend.dto.DroneDTO;
import org.example.exambackend.entity.Drone;
import org.example.exambackend.entity.DroneStatus;
import org.example.exambackend.entity.Station;
import org.example.exambackend.repository.DroneRepository;
import org.example.exambackend.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DroneService {
    private final DroneRepository droneRepository;
    private final StationRepository stationRepository;

    public DroneService(DroneRepository droneRepository, StationRepository stationRepository) {
        this.droneRepository = droneRepository;
        this.stationRepository = stationRepository;
    }

    public DroneDTO createDrone() {
        List<Station> stationsWithFewestDrones = stationRepository.findStationsWithFewestDrones();

        if (stationsWithFewestDrones.isEmpty()) {
            throw new IllegalStateException("No stations available to assign a drone.");
        }

        Station selectedStation = stationsWithFewestDrones.get(0);

        Drone drone = new Drone();
        drone.setSerialNumber(UUID.randomUUID());
        drone.setStatus(DroneStatus.IN_OPERATION);
        drone.setStation(selectedStation);

        Drone savedDrone = droneRepository.save(drone);
        return new DroneDTO(savedDrone);
    }

    public List<DroneDTO> getAllDronesWithStationInfo() {
        List<Drone> drones = droneRepository.findAll();
        return drones.stream().map(DroneDTO::new).collect(Collectors.toList());
    }

    public DroneDTO updateDroneStatus(Long id, DroneStatus newStatus) {
        Drone drone = droneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Drone with ID " + id + " not found"));
        drone.setStatus(newStatus);
        Drone updatedDrone = droneRepository.save(drone);
        return new DroneDTO(updatedDrone);
    }
}
