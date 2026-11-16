package com.scm.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

// Full application context load using H2 (Testcontainers bypassed due to OS limitations)
@SpringBootTest(properties = "spring.profiles.active=dev") 
@AutoConfigureMockMvc
public class OrderServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", roles = {"USER"}) // Bypasses JWT for test scope
    public void testCreateOrderEndpoint_Integration() throws Exception {
        
        // Validating the HTTP REST integration down to the web layer
        mockMvc.perform(post("/api/orders/ORD-123")
                .param("expedite", "true"))
               .andExpect(status().isCreated())
               .andExpect(content().string("Order ORD-123 created"));
    }
}