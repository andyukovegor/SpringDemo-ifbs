package my.self.demo.data.cars;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import my.self.demo.domain.model.CarModel;

public interface CarStorage {
	List<CarModel> getAllCarModels(String pattern);
	CarModel loadById(Long carId);
	Page<CarModel> findAll(Pageable pageable);
}
