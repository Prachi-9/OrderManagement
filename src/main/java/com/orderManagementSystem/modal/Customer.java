package com.orderManagementSystem.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="customerDetails")
public class Customer {
	
	@Id
	@Column(length = 10)
	private String customerId;
	
	private String customerType;
	
	private long orderCount;

}
