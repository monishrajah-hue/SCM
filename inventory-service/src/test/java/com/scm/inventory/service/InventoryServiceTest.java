package com.scm.inventory.service;

import com.scm.inventory.model.InventoryItem;
import com.scm.inventory.repository.InventoryRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

    // Mocking vs Stubbing - behavior vs data; @Mock - fake object; @InjectMocks - injects fakes; when() vs given() - stubbing syntax; verify() - check calls; ArgumentCaptor - capture params
    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryService inventoryService;

    private InventoryItem mockItem;

    // @Test - test method; @BeforeEach vs @BeforeAll - setup methods; Assertions - verification
    @BeforeAll
    static void globalSetup() {
        System.out.println("Starting InventoryService Unit Tests...");
    }

    @BeforeEach
    void setUp() {
        mockItem = new InventoryItem();
        mockItem.setId("1");
        mockItem.setProductId("PROD-99");
        mockItem.setStockQuantity(10);
    }

    @Test
    @DisplayName("Should successfully reserve stock and deduct quantity")
    void testReserveStockSuccess() {
        // Stubbing the behavior
        when(inventoryRepository.findByProductId("PROD-99")).thenReturn(Optional.of(mockItem));

        // Execute
        boolean result = inventoryService.reserveStock("PROD-99", 5);

        // Assertions
        assertTrue(result, "Reservation should be successful");
        assertEquals(5, mockItem.getStockQuantity(), "Stock should be deducted by 5");

        // verify() to check calls and ArgumentCaptor to capture params
        ArgumentCaptor<InventoryItem> captor = ArgumentCaptor.forClass(InventoryItem.class);
        verify(inventoryRepository, times(1)).save(captor.capture());
        
        InventoryItem savedItem = captor.getValue();
        assertEquals("PROD-99", savedItem.getProductId());
    }

    @Test
    @DisplayName("Should fail to reserve stock if insufficient quantity")
    void testReserveStockInsufficient() {
        // Stubbing the behavior
        when(inventoryRepository.findByProductId("PROD-99")).thenReturn(Optional.of(mockItem));

        // Execute
        boolean result = inventoryService.reserveStock("PROD-99", 15);

        // Assertions
        assertFalse(result, "Reservation should fail due to insufficient stock");
        
        // Verify that the save method was NEVER called
        verify(inventoryRepository, never()).save(any(InventoryItem.class));
    }
}