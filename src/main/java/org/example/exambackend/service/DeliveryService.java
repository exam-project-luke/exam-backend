package org.example.exambackend.service;

import org.example.exambackend.dto.DeliveryDTO;
import org.example.exambackend.entity.Delivery;
import org.example.exambackend.entity.Drone;
import org.example.exambackend.entity.DroneStatus;
import org.example.exambackend.entity.Pizza;
import org.example.exambackend.repository.DeliveryRepository;
import org.example.exambackend.repository.DroneRepository;
import org.example.exambackend.repository.PizzaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DroneRepository droneRepository;
    private final PizzaRepository pizzaRepository;

    public DeliveryService(DeliveryRepository deliveryRepository, DroneRepository droneRepository, PizzaRepository pizzaRepository) {
        this.deliveryRepository = deliveryRepository;
        this.droneRepository = droneRepository;
        this.pizzaRepository = pizzaRepository;
    }

    public List<DeliveryDTO> getUndeliveredDeliveries() {
        return deliveryRepository.findByActualDeliveryTimeIsNull()
                .stream()
                .map(DeliveryDTO::new)
                .collect(Collectors.toList());
    }

    public DeliveryDTO addDelivery(Long pizzaId, String address) {
        if (pizzaId == null || address == null) {
            throw new IllegalArgumentException("Pizza ID and address must not be null");
        }

        Pizza pizza = pizzaRepository.findById(pizzaId)
                .orElseThrow(() -> new IllegalArgumentException("Pizza with ID " + pizzaId + " not found"));

        Delivery delivery = new Delivery();
        delivery.setPizza(pizza);
        delivery.setAddress(address);
        delivery.setExpectedDeliveryTime(LocalDateTime.now().plusMinutes(30));
        Delivery savedDelivery = deliveryRepository.save(delivery);
        return new DeliveryDTO(savedDelivery);
    }

    public List<DeliveryDTO> getQueuedDeliveries() {
        return deliveryRepository.findByDroneIsNull()
                .stream()
                .map(DeliveryDTO::new)
                .collect(Collectors.toList());
    }

    public DeliveryDTO scheduleDelivery(Long deliveryId, Long droneId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("Delivery with ID " + deliveryId + " not found"));

        if (delivery.getDrone() != null) {
            throw new IllegalStateException("Delivery already has a drone assigned");
        }

        Drone drone = droneId != null
                ? droneRepository.findById(droneId).orElseThrow(() -> new IllegalArgumentException("Drone with ID " + droneId + " not found"))
                : droneRepository.findFirstByStatus(DroneStatus.IN_OPERATION).orElseThrow(() -> new IllegalStateException("No available drones in operation"));

        if (drone.getStatus() != DroneStatus.IN_OPERATION) {
            throw new IllegalStateException("Drone is not in operation");
        }

        delivery.setDrone(drone);
        Delivery savedDelivery = deliveryRepository.save(delivery);
        return new DeliveryDTO(savedDelivery);
    }

    public DeliveryDTO finishDelivery(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("Delivery with ID " + deliveryId + " not found"));

        if (delivery.getDrone() == null) {
            throw new IllegalStateException("Delivery does not have an assigned drone");
        }

        delivery.setActualDeliveryTime(LocalDateTime.now());
        Delivery savedDelivery = deliveryRepository.save(delivery);
        return new DeliveryDTO(savedDelivery);
    }
}