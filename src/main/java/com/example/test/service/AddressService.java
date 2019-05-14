package com.example.test.service;

import com.example.test.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressService extends CrudRepository <Address, Integer>{
}
