package org.example.exambackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.exambackend.entity.Station;



@Getter
@Setter
@NoArgsConstructor
public class StationDTO {
        private Long id;
        private double latitude;
        private double longitude;

        public StationDTO(Station station) {
            this.id = station.getId();
            this.latitude = station.getLatitude();
            this.longitude = station.getLongitude();
        }
    }
