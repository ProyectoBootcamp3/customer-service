package com.everis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.everis.model.Customer;
import com.everis.repository.InterfaceCustomerRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CustomerControllerIntegrationTest {
    
    @Autowired
    private InterfaceCustomerRepository repository;
    
//    @Autowired
//    private WebTestClient webClient;
    
    @Test
    public void testCreateCustomer() throws Exception{
        Customer customer= new Customer();
        
        customer.setId("1");
        customer.setName("MIGUEL");
        customer.setIdentityType("DNI");
        customer.setIdentityNumber("741852963");
        customer.setCustomerType("PERSONAL");
        customer.setAddress("PERU");
        customer.setPhoneNumber("963852741");
        
        Mono<Customer> found = repository.findByIdentityNumber(customer.getIdentityNumber());
		
		StepVerifier.create(found)
		.expectNext()
		.verifyComplete();
    }
    
    

}