package my.self.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import my.self.demo.domain.CarService;

@Controller
public class CarController {

	@Autowired
	private CarService carService;
	
	@GetMapping("car/list")
	public String list(Model model) {
		model.addAttribute("cars", carService.getCarsList());
		model.addAttribute("title", "Список автомобилей (демонстрация Spring JDBC)");
		
		return "car/list";
	}
	
}
