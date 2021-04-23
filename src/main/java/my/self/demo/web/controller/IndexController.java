package my.self.demo.web.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@Autowired
    private MessageSource messageSource;	
	
	@GetMapping("/")
	public String indexPage(Model model, Locale locale) {
		System.out.println("Title: " + 
			messageSource.getMessage("index.title", null, locale));
		
		return "index.html";
	}
	
}
