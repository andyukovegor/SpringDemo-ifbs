package my.self.demo.domain.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CarModel {

	private int id;
	private CarBrand brand;
	private String name;
	private Date productionDate;
	
	private double price;
	
	public CarModel(int id, CarBrand brand, String name, Date productionDate, double price) {
		super();
		this.id = id;
		this.brand = brand;
		this.name = name;
		this.productionDate = productionDate;
		this.price = price;
	}

	public CarModel() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CarBrand getBrand() {
		return brand;
	}

	public void setBrand(CarBrand brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date producctionDate) {
		this.productionDate = producctionDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductionDateFormat() {
		String productionYear = "";
		if (this.productionDate != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy");  
			productionYear = dateFormat.format(this.getProductionDate()); 			
		}
		
		return productionYear;
	}
	
	@Override
	public String toString() {
		return "CarModel [id:" + this.id + ", name:" + this.name + ", production date:" + this.getProductionDateFormat() + ", price:" + this.price + ", brand:" + this.brand.getName() + "]";
	}
	
}
