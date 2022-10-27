package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.modals.LocationStats;
import com.example.demo.service.CoronaVirusDataService;

@Controller
public class HomeController {
	@Autowired
	CoronaVirusDataService service;

	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> list = service.getList();
		int totalCases = list.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = list.stream().mapToInt(stat -> stat.getDifference()).sum();
		model.addAttribute("locationStats",list);
		model.addAttribute("totalReportedCases", totalCases);
		model.addAttribute("totalNewCases",totalNewCases);
		return "home";
	}
}
