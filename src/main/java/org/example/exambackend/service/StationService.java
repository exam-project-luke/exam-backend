package org.example.exambackend.service;

import org.example.exambackend.entity.Station;
import org.example.exambackend.repository.StationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StationService {

    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public void initializeStations() {
        if (stationRepository.count() < 3) {
            System.out.println("Initializing default stations...");

            Station station1 = new Station(); // Centrum
            station1.setLatitude(55.41);
            station1.setLongitude(12.34);

            Station station2 = new Station();
            station2.setLatitude(55.6726); // Vesterbro
            station2.setLongitude(12.5589);

            Station station3 = new Station();
            station3.setLatitude(55.6713); // Christianshavn
            station3.setLongitude(12.5916);

            stationRepository.saveAll(List.of(station1, station2, station3));
        } else {
            System.out.println("Stations already initialized.");
        }
    }
}
