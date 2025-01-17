package org.example.exambackend.api;

import org.example.exambackend.dto.DeliveryDTO;
import org.example.exambackend.dto.DeliveryRequest;
import org.example.exambackend.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    public ResponseEntity<List<DeliveryDTO>> getUndeliveredDeliveries() {
        return ResponseEntity.ok(deliveryService.getUndeliveredDeliveries());
    }

    @PostMapping("/add")
    public ResponseEntity<DeliveryDTO> addDelivery(@RequestBody DeliveryRequest deliveryRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(deliveryService.addDelivery(deliveryRequest.getPizzaId(), deliveryRequest.getAddress()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    @GetMapping("/queue")
    public ResponseEntity<List<DeliveryDTO>> getQueuedDeliveries() {
        return ResponseEntity.ok(deliveryService.getQueuedDeliveries());
    }

    @PostMapping("/schedule")
    public ResponseEntity<DeliveryDTO> scheduleDelivery(@RequestParam Long deliveryId, @RequestParam(required = false) Long droneId) {
        try {
            return ResponseEntity.ok(deliveryService.scheduleDelivery(deliveryId, droneId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(null);
        }
    }

    @PostMapping("/finish")
    public ResponseEntity<DeliveryDTO> finishDelivery(@RequestParam Long deliveryId) {
        try {
            return ResponseEntity.ok(deliveryService.finishDelivery(deliveryId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(null);
        }
    }
}