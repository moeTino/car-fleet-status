package com.alten.monitor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;

import org.assertj.core.util.IterableUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.alten.monitor.business.model.Car;
import com.alten.monitor.business.service.CarService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MonitorApplication.class)
public class MonitorApplicationTests {

	@Autowired
    private CarService carService;

    @Autowired
    private ElasticsearchTemplate esTemplate;
    
    @Before
    public void before() {
        esTemplate.deleteIndex(Car.class);
        esTemplate.createIndex(Car.class);
        esTemplate.putMapping(Car.class);
        esTemplate.refresh(Car.class);
    }
    
    @Test
    public void testSave() {

        Car car = new Car("ABC123", "YS2R4X20005399401", "Kalles Grustransporter AB", "Cementvägen", 8, "111 11 Södertälje", Boolean.TRUE);
        Car testCar = carService.save(car);

        assertNotNull(testCar.getRegistrationNumber());
        assertEquals(testCar.getVehicleId(), car.getVehicleId());
        assertEquals(testCar.getOwner(), car.getOwner());
        assertEquals(testCar.getType(), car.getType());
        assertEquals(testCar.getCapacity(), car.getCapacity());
        assertEquals(testCar.getAddress(), car.getAddress());
        assertEquals(testCar.getConnected(), car.getConnected());

    }
    
    @Test
    public void testFindOne() {

    	Car car = new Car("ABC123", "YS2R4X20005399401", "Kalles Grustransporter AB", "Cementvägen", 8, "111 11 Södertälje", Boolean.TRUE);
    	carService.save(car);

        Optional<Car> testCar = carService.findById(car.getRegistrationNumber());
        
        if(testCar.isPresent()) {
        	Car testThisCar = testCar.get();
        	assertNotNull(testThisCar.getRegistrationNumber());
        	assertEquals(testThisCar.getVehicleId(), car.getVehicleId());
            assertEquals(testThisCar.getOwner(), car.getOwner());
            assertEquals(testThisCar.getType(), car.getType());
            assertEquals(testThisCar.getCapacity(), car.getCapacity());
            assertEquals(testThisCar.getAddress(), car.getAddress());
            assertEquals(testThisCar.getConnected(), car.getConnected());
        }
    }
    
    @Test
    public void testFindByOwner() {
    	
    	carService.save(new Car("ABC123", "YS2R4X20005399401", "Kalles Grustransporter AB", "Cementvägen", 8, "111 11 Södertälje", Boolean.TRUE));
		carService.save(new Car("DEF456", "VLUR4X20009093588", "Kalles Grustransporter AB", "Cementvägen", 8, "111 11 Södertälje", Boolean.TRUE));
		carService.save(new Car("GHI789", "VLUR4X20009048066", "Kalles Grustransporter AB", "Cementvägen", 8, "111 11 Södertälje", Boolean.TRUE));
		
		carService.save(new Car("JKL012", "YS2R4X20005388011", "Johans Bulk AB", "Balkvägen", 12, "222 22 Stockholm", Boolean.TRUE));
		carService.save(new Car("MNO345", "YS2R4X20005387949", "Johans Bulk AB", "Balkvägen", 12, "222 22 Stockholm", Boolean.TRUE));
		
		carService.save(new Car("PQR678", "VLUR4X20009048066", "Haralds Värdetransporter AB", "Budgetvägen", 1, "333 33 Uppsala", Boolean.TRUE));
		carService.save(new Car("STU901", "YS2R4X20005387055", "Haralds Värdetransporter AB", "Budgetvägen", 1, "333 33 Uppsala", Boolean.TRUE));

		Page<Car> byOwner = carService.findByOwner("Kalles Grustransporter AB", PageRequest.of(0, 10));
    	assertThat(byOwner.getTotalElements(), is(3L));
    	
    }
    
    @Test
    public void testSearchOwner() {
    	
    	carService.save(new Car("ABC123", "YS2R4X20005399401", "Kalles Grustransporter AB", "Cementvägen", 8, "111 11 Södertälje", Boolean.TRUE));
		carService.save(new Car("DEF456", "VLUR4X20009093588", "Kalles Grustransporter AB", "Cementvägen", 8, "111 11 Södertälje", Boolean.TRUE));
		carService.save(new Car("GHI789", "VLUR4X20009048066", "Kalles Grustransporter AB", "Cementvägen", 8, "111 11 Södertälje", Boolean.TRUE));
		
		carService.save(new Car("JKL012", "YS2R4X20005388011", "Johans Bulk AB", "Balkvägen", 12, "222 22 Stockholm", Boolean.TRUE));
		carService.save(new Car("MNO345", "YS2R4X20005387949", "Johans Bulk AB", "Balkvägen", 12, "222 22 Stockholm", Boolean.TRUE));
		
		carService.save(new Car("PQR678", "VLUR4X20009048066", "Haralds Värdetransporter AB", "Budgetvägen", 1, "333 33 Uppsala", Boolean.TRUE));
		carService.save(new Car("STU901", "YS2R4X20005387055", "Haralds Värdetransporter AB", "Budgetvägen", 1, "333 33 Uppsala", Boolean.TRUE));

		Page<Car> byOwner = carService.searchOwner("Kalles ");
    	assertThat(byOwner.getTotalElements(), is(3L));
    	
    }
    
    @Test
    public void testFindByConnected() {
    	carService.save(new Car("ABC123", "YS2R4X20005399401", "Kalles Grustransporter AB", "Cementvägen", 8, "111 11 Södertälje", Boolean.TRUE));
		carService.save(new Car("DEF456", "VLUR4X20009093588", "Kalles Grustransporter AB", "Cementvägen", 8, "111 11 Södertälje", Boolean.TRUE));
		carService.save(new Car("GHI789", "VLUR4X20009048066", "Kalles Grustransporter AB", "Cementvägen", 8, "111 11 Södertälje", Boolean.TRUE));
		
		carService.save(new Car("JKL012", "YS2R4X20005388011", "Johans Bulk AB", "Balkvägen", 12, "222 22 Stockholm", Boolean.FALSE));
		carService.save(new Car("MNO345", "YS2R4X20005387949", "Johans Bulk AB", "Balkvägen", 12, "222 22 Stockholm", Boolean.FALSE));
		
		List<Car> byConnected = carService.findByConnected(Boolean.FALSE);
		assertThat(byConnected.size(), is(2));
				
    }
    
    @Test
    public void testFindAll() {
    	
    	carService.save(new Car("ABC123", "YS2R4X20005399401", "Kalles Grustransporter AB", "Cementvägen", 8, "111 11 Södertälje", Boolean.TRUE));
		carService.save(new Car("DEF456", "VLUR4X20009093588", "Kalles Grustransporter AB", "Cementvägen", 8, "111 11 Södertälje", Boolean.TRUE));
		carService.save(new Car("GHI789", "VLUR4X20009048066", "Kalles Grustransporter AB", "Cementvägen", 8, "111 11 Södertälje", Boolean.TRUE));
		
		carService.save(new Car("JKL012", "YS2R4X20005388011", "Johans Bulk AB", "Balkvägen", 12, "222 22 Stockholm", Boolean.TRUE));
		carService.save(new Car("MNO345", "YS2R4X20005387949", "Johans Bulk AB", "Balkvägen", 12, "222 22 Stockholm", Boolean.TRUE));
		
		carService.save(new Car("PQR678", "VLUR4X20009048066", "Haralds Värdetransporter AB", "Budgetvägen", 1, "333 33 Uppsala", Boolean.TRUE));
		carService.save(new Car("STU901", "YS2R4X20005387055", "Haralds Värdetransporter AB", "Budgetvägen", 1, "333 33 Uppsala", Boolean.TRUE));

		Iterable<Car> cars = carService.findAll();
		assertThat(IterableUtil.sizeOf(cars), is(7));
    	
    }
    
    
}
