package com.miuma.ecommerce.springecommerce.service;

import com.miuma.ecommerce.springecommerce.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IUserService {

    Optional<User> findById(Integer id);

}
