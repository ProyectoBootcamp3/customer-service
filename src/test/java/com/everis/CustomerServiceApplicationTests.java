package com.everis;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.everis.model.Customer;
import com.everis.repository.InterfaceCustomerRepository;
import com.everis.service.impl.CustomerService;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class CustomerServiceApplicationTests {

	public final static Flux<Customer> CLIENTE = Flux.just(
			new Customer("1", "MIGUEL", "DNI", "12345678", "PERSONAL", "PERU", "999999999"),
			new Customer("2", "ALEJANDRO", "DNI", "87654321", "PERSONAL", "PERU", "888888888"));
	@Mock
	InterfaceCustomerRepository repository;
	
	@Mock
	ReactiveMongoTemplate mongoTemplate; 
	
	@InjectMocks
	CustomerService customerService;
	
	@Mock 
	CustomerService service;
	
  @Test
  void test() {
		when(repository.findAll()).thenReturn(CLIENTE);
		Flux<Customer> list = customerService.findAll();
		StepVerifier.create(list)
		.expectSubscription()
		.expectNextCount(2)
		.verifyComplete();
	}

}
