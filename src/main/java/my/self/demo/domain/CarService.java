package my.self.demo.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import my.self.demo.domain.model.CarModel;

public interface CarService {

	public List<CarModel> getCarsList();
	public CarModel findById(Long carId);
	public List<CarModel> getCarsByNameLike(String name);
	public Page<CarModel> findAll(PageRequest pageRequest);
}
