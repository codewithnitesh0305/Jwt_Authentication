package com.springboot.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("/User")
	public String home() {
		return "Hlo User";
	}
}
