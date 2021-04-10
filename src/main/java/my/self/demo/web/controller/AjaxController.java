package my.self.demo.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.self.demo.domain.CarService;
import my.self.demo.domain.model.CarModel;

@RestController
@RequestMapping("/ajax")
public class AjaxController {

	@Autowired
	private CarService carService;
	
	@RequestMapping(path = "/car-filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CarModel> carFilter(@RequestParam(name = "q", required = false, defaultValue = "") String name) {
		return carService.getCarsByNameLike(name);
	}
	
}
