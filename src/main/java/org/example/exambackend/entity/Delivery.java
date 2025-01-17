package org.example.exambackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor

@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private LocalDateTime expectedDeliveryTime; // Altid defineret

    private LocalDateTime actualDeliveryTime; // Kan være udefineret

    @ManyToOne
    @JoinColumn(name = "drone_id", nullable = true) //nullable da delivery can eksistere uden en drone
    private Drone drone;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;
}