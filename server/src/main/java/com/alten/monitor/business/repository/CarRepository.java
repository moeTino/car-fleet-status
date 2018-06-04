package com.alten.monitor.business.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.alten.monitor.business.model.Car;

public interface CarRepository extends ElasticsearchRepository<Car, String> {
	
	Page<Car> findByOwner(String owner, Pageable pageable);

	List<Car> findByConnected(String connected);
	
	Optional<Car> findById(String id);
	
}
