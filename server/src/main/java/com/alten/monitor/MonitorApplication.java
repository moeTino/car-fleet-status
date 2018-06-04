package com.alten.monitor;

import java.util.Map;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.alten.monitor.business.model.Car;
import com.alten.monitor.business.service.CarService;

@SpringBootApplication
@EnableScheduling
public class MonitorApplication implements CommandLineRunner{

	@Autowired
    private ElasticsearchOperations es;

    @Autowired
    private CarService carService;
	
	public static void main(String args[]) {
		SpringApplication.run(MonitorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		printElasticSearchInfo();
		
		carService.save(new Car("ABC123", "YS2R4X20005399401", "Kalles Grustransporter AB", "Cementvägen", 8, "111 11 Södertälje", Boolean.TRUE));
		carService.save(new Car("DEF456", "VLUR4X20009093588", "Kalles Grustransporter AB", "Cementvägen", 8, "111 11 Södertälje", Boolean.TRUE));
		carService.save(new Car("GHI789", "VLUR4X20009048066", "Kalles Grustransporter AB", "Cementvägen", 8, "111 11 Södertälje", Boolean.TRUE));
		
		carService.save(new Car("JKL012", "YS2R4X20005388011", "Johans Bulk AB", "Balkvägen", 12, "222 22 Stockholm", Boolean.TRUE));
		carService.save(new Car("MNO345", "YS2R4X20005387949", "Johans Bulk AB", "Balkvägen", 12, "222 22 Stockholm", Boolean.TRUE));
		
		carService.save(new Car("PQR678", "VLUR4X20009048066", "Haralds Värdetransporter AB", "Budgetvägen", 1, "333 33 Uppsala", Boolean.TRUE));
		carService.save(new Car("STU901", "YS2R4X20005387055", "Haralds Värdetransporter AB", "Budgetvägen", 1, "333 33 Uppsala", Boolean.TRUE));
		
		// Check if cars were inserted correctly
		Page<Car> cars = carService.findByOwner("AB", PageRequest.of(0, 10));		
		cars.forEach(x -> System.out.println(x));
		
	}
	
    private void printElasticSearchInfo() {

        System.out.println("**********ElasticSearch**********");
        Client client = es.getClient();
        Map<String, String> asMap = client.settings().getAsMap();

        asMap.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
        System.out.println("**********ElasticSearch**********");
        
    }
}
