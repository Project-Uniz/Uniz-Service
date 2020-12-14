package com.uniz.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.uniz.domain.UnizChartVO;
import com.uniz.service.UnizChartService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j

@AllArgsConstructor

public class UnizChartController {
	
	@RequestMapping(value = "dataChart", method = RequestMethod.GET)

	public String dateIncome(Locale locale, Model model) {

		return "dataChart";

	}
	
	
	private UnizChartService service;
	
	@RequestMapping(value = "chartList", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")

	public @ResponseBody String incomeList(Locale locale, Model model) {

		Gson gson = new Gson();

		List<UnizChartVO> list = service.chartData();

		

		return gson.toJson(list);

	}
	

}
