package my.self.demo.data.cars;

import java.util.List;

import my.self.demo.domain.model.CarModel;

public interface CarStorage {

	List<CarModel> getAllCarModels(String pattern);

	CarModel loadById(Long carId);
}
