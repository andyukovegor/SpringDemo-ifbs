package my.self.demo.data.cars;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import my.self.demo.domain.model.CarBrand;
import my.self.demo.domain.model.CarModel;

public class CarRowMapper implements RowMapper<CarModel> {

	@Override
	public CarModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		CarModel carModel = new CarModel();
		CarBrand carBrand = new CarBrand(rs.getInt("car_brand_id"), rs.getString("brandName"),
				rs.getString("url"));

		carModel.setId(rs.getInt("id"));
		carModel.setBrand(carBrand);
		carModel.setName(rs.getString("name"));
		carModel.setPrice(rs.getDouble("price"));
		carModel.setProductionDate(rs.getDate("productionDate"));

		return carModel;
	}

}
