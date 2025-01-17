package org.example.exambackend.api;

import org.example.exambackend.dto.DroneDTO;
import org.example.exambackend.entity.DroneStatus;
import org.example.exambackend.service.DroneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drones")
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    // Create Drone
    @PostMapping("/add")
    public ResponseEntity<DroneDTO> createDrone() {
        DroneDTO createdDrone = droneService.createDrone();
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDrone);
    }

    // Read all Drones
    @GetMapping
    public ResponseEntity<List<DroneDTO>> getAllDrones() {
        List<DroneDTO> drones = droneService.getAllDronesWithStationInfo();
        return ResponseEntity.ok(drones);
    }
    // Enable Drone
    @PostMapping("/enable")
    public ResponseEntity<DroneDTO> enableDrone(@RequestParam Long id) {
        DroneDTO updatedDrone = droneService.updateDroneStatus(id, DroneStatus.IN_OPERATION);
        return ResponseEntity.ok(updatedDrone);
    }
    // Disable Drone
    @PostMapping("/disable")
    public ResponseEntity<DroneDTO> disableDrone(@RequestParam Long id) {
        DroneDTO updatedDrone = droneService.updateDroneStatus(id, DroneStatus.OUT_OF_OPERATION);
        return ResponseEntity.ok(updatedDrone);
    }

    // Retire Drone
    @PostMapping("/retire")
    public ResponseEntity<DroneDTO> retireDrone(@RequestParam Long id) {
        DroneDTO updatedDrone = droneService.updateDroneStatus(id, DroneStatus.DECOMMISSIONED);
        return ResponseEntity.ok(updatedDrone);
    }
}

