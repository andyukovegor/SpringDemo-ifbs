package my.self.demo.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/calc")
public class CalculatorController {

	
	@GetMapping("/input")
	public String inputPage(Model model) {
		model.addAttribute("title", "Калькулятор");
		
		Map<String, String> op = new HashMap<>();
		op.put("+", "plus");
		op.put("-", "minus");
		
		model.addAttribute("op", op);
		return "/calc/input.html";
	}
	
	@GetMapping("/result")
	public ModelAndView resultPage(
			@RequestParam(name = "aValue") Integer a,
			@RequestParam(name = "bValue") Integer b,
			@RequestParam(name = "op") String op,
			ModelAndView model) {
		model.setViewName("/calc/result.html");
		
		Integer res = null;
		switch (op) {
		case "plus":
			res = a+b;
			break;

		case "minus":
			res = a-b;
			break;
		default:
			break;
		}
		
		model.addObject("title", "Калькулятор - результат");
		model.addObject("result", res);
		return model;
	}
	
	
	@GetMapping({"/plus/{a}/{b}"})
	public ModelAndView plusPage(
			@PathVariable("a") Integer a,
			@PathVariable("b") Integer b,
			ModelAndView model
			) {
		
		model.addObject("aValue", a);
		model.addObject("bValue", b);
		model.addObject("op", "plus");
		
		model.setViewName("redirect:/calc/result");
		
		return model;
	}
	
}
