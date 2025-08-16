package com.sb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	@GetMapping("/login")
	public String hello() {
		return "login.html";
	}

	@GetMapping("/")
	public String welcome() {
		return "welcome.html";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin.html";
	}

	@GetMapping("/user")
	public String user() {
		return "user.html";
	}
}