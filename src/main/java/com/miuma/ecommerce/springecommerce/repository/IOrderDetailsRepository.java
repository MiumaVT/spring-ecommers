package com.miuma.ecommerce.springecommerce.repository;

import com.miuma.ecommerce.springecommerce.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

}
