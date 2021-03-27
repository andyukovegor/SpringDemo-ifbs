package my.self.demo.data.cars;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import my.self.demo.domain.model.CarModelData;

public interface CarModelDataRepository 
	extends CrudRepository<CarModelData, Integer> {

	List<CarModelData> findAllByName(String name);
	List<CarModelData> findAllByNameLike(String name);
	
	@Query("select car_model.* from car_model left join car_brand "
			+ "on car_model.car_brand_id = car_brand.id where car_brand.name LIKE :brand")
	List<CarModelData> findAllByNameBrand(@Param("brand") String brandName);

	
	@Query("update car_model set price = :price where id = :id")
	@Modifying
	int updatePriceByCarId(Integer id, double price);
	
}
