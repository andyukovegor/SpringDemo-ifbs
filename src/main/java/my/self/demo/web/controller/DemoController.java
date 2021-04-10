package my.self.demo.web.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.self.demo.domain.model.CarBrand;
import my.self.demo.domain.model.CarModel;

@RestController
@RequestMapping("/demo")
public class DemoController {

	@GetMapping("/set")
	public String set(HttpServletResponse response) {
		Cookie cookie = new Cookie("cookie", "45FGS23");
		cookie.setPath("/");
		cookie.setMaxAge(3600);
		cookie.setHttpOnly(true);
		
		response.addCookie(cookie);
		
		return "Ok";
	}
	
	@GetMapping("/get")
	public String get(@CookieValue(name = "cookie") String value) {
		return "Get cookie value: " + value;
	}
	
	@GetMapping("/write")
	public String write(HttpSession session) {
		CarModel car = new CarModel(1, new CarBrand(1, "Lada", ""), "Vesta", new Date(), 1050.0);
		
		session.setAttribute("car", car);
		
		return "Ok";
	}
	
	@GetMapping("/read")
	public String read(HttpSession session) {
		CarModel car = (CarModel)session.getAttribute("car");
		
		return "Read car " + car;
	}
	
}
