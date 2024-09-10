package com.springboot.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@PreAuthorize("hasAuthority('MANAGER')")
	@GetMapping("/Manager")
	private String manager() {
		return "Hlo Manager";
	}
}
