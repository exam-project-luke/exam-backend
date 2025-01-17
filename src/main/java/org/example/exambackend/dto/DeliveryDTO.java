package org.example.exambackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.exambackend.entity.Delivery;
import org.example.exambackend.entity.Drone;
import org.example.exambackend.entity.Pizza;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryDTO {
    private Long id;
    private String address;
    private LocalDateTime expectedDeliveryTime;
    private LocalDateTime actualDeliveryTime;
    private Pizza pizza;
    private DroneDTO drone;

    public DeliveryDTO(Delivery delivery) {
        this.id = delivery.getId();
        this.address = delivery.getAddress();
        this.expectedDeliveryTime = delivery.getExpectedDeliveryTime();
        this.actualDeliveryTime = delivery.getActualDeliveryTime();
        this.pizza = delivery.getPizza();
        if (delivery.getDrone() != null) {
            this.drone = new DroneDTO(delivery.getDrone());
        }
    }
}