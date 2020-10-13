package com.orderManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.orderManagementSystem.exception.CustomerNotfoundException;
import com.orderManagementSystem.exception.ExceptionJson;
import com.orderManagementSystem.modal.Customer;
import com.orderManagementSystem.modal.Order;
import com.orderManagementSystem.service.CustomerActionsService;

@RestController
public class CustomerActionsController {

	@Autowired
	CustomerActionsService service;
	
	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST,produces = "application/json")
	public @ResponseBody
	ResponseEntity<?> createCustomer(@RequestBody Customer customer){
		Customer customerCreated = new Customer();
		try {
			customerCreated = service.createCustomer(customer);
		}
		catch (Exception e) {
            return new ResponseEntity<ExceptionJson>(new ExceptionJson("Something went wrong","/createCustomer"),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
		if(customerCreated == null) {
			return new ResponseEntity<String>("Customer Already Present",HttpStatus.OK);
		}else{
			return new ResponseEntity<Customer>(customerCreated,HttpStatus.OK);
		}
        

	}
	
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST,produces = "application/json")
	public @ResponseBody
	ResponseEntity<?> createOrder(@RequestBody Customer customer){
		Order orderCreated = new Order();
		try {
			orderCreated = service.createOrder(customer);
		}catch(CustomerNotfoundException e) {
			return new ResponseEntity<String>(e.toString(),HttpStatus.OK);
		}catch (Exception e) {
            return new ResponseEntity<ExceptionJson>(new ExceptionJson("Something went wrong","/createCustomer"),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<Order>(orderCreated,HttpStatus.OK);

	}
}
