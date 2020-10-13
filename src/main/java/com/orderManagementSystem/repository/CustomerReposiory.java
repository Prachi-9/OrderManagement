package com.orderManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderManagementSystem.modal.Customer;

@Repository
public interface CustomerReposiory extends JpaRepository<Customer, String> {

}
