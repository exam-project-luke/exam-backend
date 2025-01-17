package org.example.exambackend;

import org.example.exambackend.api.DeliveryController;
import org.example.exambackend.dto.DeliveryDTO;
import org.example.exambackend.dto.DeliveryRequest;
import org.example.exambackend.service.DeliveryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DeliveryControllerMockitoTest {

    @Mock
    private DeliveryService deliveryService;

    @InjectMocks
    private DeliveryController deliveryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUndeliveredDeliveries() {
        // Arrange
        when(deliveryService.getUndeliveredDeliveries()).thenReturn(Collections.emptyList());

        // Act
        var response = deliveryController.getUndeliveredDeliveries();

        // Assert
        assertEquals(200, response.getStatusCodeValue()); // Check status code
        assertEquals(0, response.getBody().size());       // Check empty list
    }

    @Test
    public void testAddDelivery() {
        // Arrange
        DeliveryDTO mockDelivery = new DeliveryDTO();
        mockDelivery.setId(1L);
        mockDelivery.setAddress("Test Address");
        when(deliveryService.addDelivery(1L, "Test Address")).thenReturn(mockDelivery);

        // Act
        var response = deliveryController.addDelivery(new DeliveryRequest(1L, "Test Address"));

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Test Address", response.getBody().getAddress());
    }
    @Test
    public void testGetQueuedDeliveries() {
        // Arrange
        DeliveryDTO queuedDelivery = new DeliveryDTO();
        queuedDelivery.setId(1L);
        queuedDelivery.setAddress("Queued Address");
        when(deliveryService.getQueuedDeliveries()).thenReturn(List.of(queuedDelivery));

        // Act
        var response = deliveryController.getQueuedDeliveries();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("Queued Address", response.getBody().get(0).getAddress());
    }
    @Test
    public void testScheduleDelivery() {
        // Arrange
        DeliveryDTO scheduledDelivery = new DeliveryDTO();
        scheduledDelivery.setId(1L);
        when(deliveryService.scheduleDelivery(1L, null)).thenReturn(scheduledDelivery);

        // Act
        var response = deliveryController.scheduleDelivery(1L, null);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }
    @Test
    public void testFinishDelivery() {
        // Arrange
        DeliveryDTO finishedDelivery = new DeliveryDTO();
        finishedDelivery.setId(1L);
        when(deliveryService.finishDelivery(1L)).thenReturn(finishedDelivery);

        // Act
        var response = deliveryController.finishDelivery(1L);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }

    //EDGE case tests below

    @Test
    public void testGetQueuedDeliveries_EmptyResult() {
        // Arrange
        when(deliveryService.getQueuedDeliveries()).thenReturn(Collections.emptyList());

        // Act
        var response = deliveryController.getQueuedDeliveries();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isEmpty());
    }

}
