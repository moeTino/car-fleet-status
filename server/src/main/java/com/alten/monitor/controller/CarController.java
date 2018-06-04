package com.alten.monitor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alten.monitor.business.model.Car;
import com.alten.monitor.business.service.CarService;

@RestController
public class CarController {
	
	@Autowired
	private CarService carService;
	
	
	@RequestMapping(value="/car/{id}", method=RequestMethod.GET)
	//@CrossOrigin(origins = "http://localhost:4200")
	@CrossOrigin(origins = "*")
    public Car carById(@PathVariable String id) {
		
		Optional<Car> car = carService.findById(id);
		if(car.isPresent()) {
			return car.get();
		}
		return new Car();
		
	}
	
	@RequestMapping(value="/cars/connected/{status}", method=RequestMethod.GET)
	@CrossOrigin(origins = "*")
    public List<Car> carByConnected(@PathVariable String status) {
		
		List<Car> cars = carService.findByConnected(Boolean.valueOf(status));
		return cars;
		
	}
	
	@RequestMapping(value="/cars/owner/{owner}", method=RequestMethod.GET)
	@CrossOrigin(origins = "*")
    public List<Car> searchCarByOwner(@PathVariable String owner) {
		
		Page<Car> carsPage = carService.searchOwner(owner);
		
		if(carsPage != null) {
			List<Car> cars = carsPage.getContent();
			return cars;
		}
		return null;
		
	}
	
	@RequestMapping(value="/cars", method=RequestMethod.GET)
	@CrossOrigin(origins = "*")
    public List<Car> cars() {
		
		Iterable<Car> cars = carService.findAll();
		List<Car> allCars = new ArrayList<Car>();
		cars.forEach(car -> allCars.add(simulateConneced(car)));
		
		return allCars;
		
	}
	
	@Scheduled(fixedDelay = 60000)
	@RequestMapping(value="/cars/connected", method=RequestMethod.POST)
	@CrossOrigin(origins = "*")
	public void updateStatus() {
		
		Iterable<Car> cars = carService.findAll();
		List<Car> simulatedCars = new ArrayList<Car>();
		cars.forEach(car -> simulatedCars.add(simulateConneced(car)));
		
		for(Car simulatedCar: simulatedCars){
			carService.save(simulatedCar);
		}
		
		System.out.println("Fixed delay cars update - " + System.currentTimeMillis() / 1000);
	}
	
	private Car simulateConneced(Car car) {
    	car.setConnected(Math.random() > 0.5);
    	return car;
    }
}
