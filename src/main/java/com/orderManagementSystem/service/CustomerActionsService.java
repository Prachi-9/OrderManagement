package com.orderManagementSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderManagementSystem.exception.CustomerNotfoundException;
import com.orderManagementSystem.modal.Customer;
import com.orderManagementSystem.modal.Order;
import com.orderManagementSystem.repository.CustomerReposiory;
import com.orderManagementSystem.repository.OrderRepository;

@Service
public class CustomerActionsService {

	@Autowired
	CustomerReposiory customerRepository;
	
	@Autowired
	OrderRepository orderRepository;

	public Customer createCustomer(Customer customer) {
		Customer customerCreated = null;
		if(!customerRepository.findById(customer.getCustomerId()).isPresent()) {
			customer.setCustomerType("Regular");
			customerCreated = customerRepository.save(customer);
		}
		return customerCreated;
	}

	public Order createOrder(Customer customer) throws CustomerNotfoundException {
		Order orderCreated = new Order();
		Optional<Customer> customerDetails = customerRepository.findById(customer.getCustomerId());
		if(customerDetails.isPresent()) {
			customerDetails.get().setOrderCount(customerDetails.get().getOrderCount() + 1);
			switch(customerDetails.get().getCustomerType().toLowerCase()) {
				case "regular":
					if(customerDetails.get().getOrderCount() == 9) {
						sendMail("One more order to uprade to Gold");
					}else if(customerDetails.get().getOrderCount() == 10) {
						customerDetails.get().setCustomerType("Gold");
					}
					break;
				case "gold":
					orderCreated.setDiscount(10);
					if(customerDetails.get().getOrderCount() == 19) {
						sendMail("One more order to uprade to Platinum");
					}else if(customerDetails.get().getOrderCount() == 20) {
						customerDetails.get().setCustomerType("Platinum");
					}
					break;
				case "platinum":
					orderCreated.setDiscount(20);
					break;
				default :
					break;
			}
			orderCreated.setCustomer(customerDetails.get());
			orderCreated = orderRepository.save(orderCreated);
		}else {
			throw new CustomerNotfoundException("Customer Not Present");
		}
		
		return orderCreated;
	}
	
	private void sendMail(String msg) {
		System.out.println(msg);
	}
	
	
}
