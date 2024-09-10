package com.springboot.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/Admin")
	public String admin() {
		return "Hlo Adimin";
	}
}
