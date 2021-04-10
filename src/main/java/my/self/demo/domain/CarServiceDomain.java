package my.self.demo.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.self.demo.data.cars.CarStorage;
import my.self.demo.domain.model.CarModel;


@Service
public class CarServiceDomain implements CarService {

	@Autowired
	private CarStorage storage;

	@Override
	public List<CarModel> getCarsList() {
		return storage.getAllCarModels(null);
	}

	@Override
	public CarModel findById(Long carId) {
		return (carId != null) ? storage.loadById(carId) : null;
	}

	@Override
	public List<CarModel> getCarsByNameLike(String name) {
		return storage.getAllCarModels(name);
	}
	
}
