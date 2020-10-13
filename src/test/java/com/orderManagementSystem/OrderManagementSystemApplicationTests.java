package com.orderManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.orderManagementSystem.exception.CustomerNotfoundException;
import com.orderManagementSystem.modal.Customer;
import com.orderManagementSystem.modal.Order;
import com.orderManagementSystem.repository.CustomerReposiory;
import com.orderManagementSystem.repository.OrderRepository;
import com.orderManagementSystem.service.CustomerActionsService;

@SpringBootTest
class OrderManagementSystemApplicationTests {

	@Autowired
	CustomerActionsService service;
	
	@MockBean
	CustomerReposiory customerRepository;
	
	@MockBean
	OrderRepository orderRepository;
	
	
	@Test
	void TestCreateCustomer() {
		Customer inputCustomer = new Customer();
		inputCustomer.setCustomerId("111");
		
		Customer outputCustomer = new Customer();
		outputCustomer.setCustomerId("111");
		outputCustomer.setCustomerType("Regular");
		
		Optional<Customer> customer = Optional.ofNullable(null);
		
		when(customerRepository.findById(inputCustomer.getCustomerId())).thenReturn(customer);
		when(customerRepository.save(outputCustomer)).thenReturn(outputCustomer);
		
		assertEquals(outputCustomer, service.createCustomer(inputCustomer));
	}
	
	@Test
	void TestCreateCustomerForAlreadyPresent() {
		Customer inputCustomer = new Customer();
		inputCustomer.setCustomerId("111");
		
		Optional<Customer> customer = Optional.ofNullable(inputCustomer);
		
		when(customerRepository.findById(inputCustomer.getCustomerId())).thenReturn(customer);
		assertEquals(null, service.createCustomer(inputCustomer));
		
	}
	
	@Test
	void TestCreateOrder() throws CustomerNotfoundException {
		Customer inputCustomer = new Customer();
		inputCustomer.setCustomerId("111");
		
		Customer fromDataBase = new Customer();
		fromDataBase.setCustomerId("111");
		fromDataBase.setCustomerType("Regular");
		fromDataBase.setOrderCount(9);
		
		Order orderCreated = new Order();
		orderCreated.setCustomer(fromDataBase);
		
		Optional<Customer> customer = Optional.of(fromDataBase);
		
		when(customerRepository.findById(inputCustomer.getCustomerId())).thenReturn(customer);
		when(orderRepository.save(orderCreated)).thenReturn(orderCreated);
		
		assertEquals(orderCreated, service.createOrder(inputCustomer));
	}
	
	@Test
	void TestCreateOrderForCustomerNotPresent() {
		Customer inputCustomer = new Customer();
		inputCustomer.setCustomerId("111");
		
		Optional<Customer> customer = Optional.ofNullable(null);
		
		when(customerRepository.findById(inputCustomer.getCustomerId())).thenReturn(customer);
		
		try {
			service.createOrder(inputCustomer);
		} catch (CustomerNotfoundException e) {
			assertEquals("Customer Not Present", e.toString());
		}
	}

}
