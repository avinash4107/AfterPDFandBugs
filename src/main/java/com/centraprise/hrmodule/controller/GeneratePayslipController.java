package com.centraprise.hrmodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centraprise.hrmodule.service.SalarySlipGenerationService;

@Controller
public class GeneratePayslipController {
	@Autowired
	private SalarySlipGenerationService salarySlipGenerationService;

	@GetMapping("/generatePDF")
	public @ResponseBody String generatePayslip() {

		try {
			salarySlipGenerationService.generatePDF();
		} catch (Exception e) {
			e.printStackTrace();
			return "payslip has not  generated";

		}

		return "payslip has generated";
	}

}
