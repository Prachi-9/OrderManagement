package com.orderManagementSystem.modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="orderDetails")
public class Order {

	@Id
	@GeneratedValue
	private long orderId;
	
	@ManyToOne
	private Customer customer;
	
	private long discount;
	
}
