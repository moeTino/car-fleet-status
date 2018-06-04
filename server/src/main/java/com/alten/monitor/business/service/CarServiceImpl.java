package com.alten.monitor.business.service;

import java.util.List;
import java.util.Optional;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.alten.monitor.business.model.Car;
import com.alten.monitor.business.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {

	private CarRepository carRepository;

	@Autowired
	public void setCarRepository(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Override
	public Car save(Car car) {

		return carRepository.save(car);

	}

	@Override
	public void delete(Car car) {

		carRepository.delete(car);

	}

	@Override
	public Optional<Car> findById(String id) {

		return carRepository.findById(id);

	}

	@Override
	public Iterable<Car> findAll() {

		return carRepository.findAll();
	}

	@Override
	public Page<Car> findByOwner(String owner, PageRequest pageRequest) {

		return carRepository.findByOwner(owner, pageRequest);

	}
	
	@Override
	public Page<Car> searchOwner(String owner) {
		
		BoolQueryBuilder qb = QueryBuilders.boolQuery();
		QueryBuilder qbm = QueryBuilders.matchQuery("owner", owner)
										.operator(Operator.AND)
										.fuzziness(Fuzziness.ONE)
										.prefixLength(3);
		QueryBuilder qbmp = QueryBuilders.prefixQuery("owner", owner);
		QueryBuilder qbmph = QueryBuilders.matchPhraseQuery("owner", owner)
										  .slop(1);
		QueryBuilder qbmphp = QueryBuilders.matchPhrasePrefixQuery("owner", owner)
										   .slop(1);
		qb.should(qbm);
		qb.should(qbmp);
		qb.should(qbmph);
		qb.should(qbmphp);
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
					.withQuery(qb)
					.build();
		return carRepository.search(searchQuery);
	}

	@Override
	public List<Car> findByConnected(Boolean connected) {

		return carRepository.findByConnected(connected.toString());

	}

}
