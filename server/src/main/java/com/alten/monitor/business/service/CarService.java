package com.alten.monitor.business.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.alten.monitor.business.model.Car;

public interface CarService {
	
	Car save(Car car);
	
	void delete(Car car);
	
	Optional<Car> findById(String id);
	
	Iterable<Car> findAll();
	
	Page<Car> findByOwner(String owner, PageRequest pageRequest);
	
	List<Car> findByConnected(Boolean connected);
	
	Page<Car> searchOwner(String owner);
	
}
