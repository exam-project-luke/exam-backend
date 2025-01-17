package org.example.exambackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.exambackend.entity.Drone;
import org.example.exambackend.entity.DroneStatus;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class DroneDTO {
    private UUID serialNumber;
    private DroneStatus status;
    private StationDTO station;

    public DroneDTO(Drone drone) {
        this.serialNumber = drone.getSerialNumber();
        this.status = drone.getStatus();
        if (drone.getStation() != null) {
            this.station = new StationDTO(drone.getStation());
        }
    }
}