package my.self.demo.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import my.self.demo.domain.user.UserService;


@Controller
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/user/list")
	public String index(Model model) {

		model.addAttribute("users", userService.getList());

		return "/user/list";
	}

}
