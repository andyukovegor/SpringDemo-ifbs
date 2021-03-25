package my.self.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@GetMapping("/")
	public String indexPage(Model model) {
		model.addAttribute("title", "Spring Демо");
		
		return "index.html";
	}
	
}
