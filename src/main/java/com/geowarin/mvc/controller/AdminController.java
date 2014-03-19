package com.geowarin.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geowarin.mvc.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;

	@RequestMapping("/authenticatedAction")
	public String authenticatedAction() {
		
		adminService.authenticatedAction();
		return "/index";
	}
}
