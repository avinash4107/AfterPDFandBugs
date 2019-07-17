package com.centraprise.hrmodule.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centraprise.hrmodule.entity.SaloryInfo;
import com.centraprise.hrmodule.exception.DatabaseException;
import com.centraprise.hrmodule.model.SaloryCommand;
import com.centraprise.hrmodule.model.SaloryInfoListDTO;
import com.centraprise.hrmodule.repository.SalaryRepository;

@Service
public class SalaryServiceImpl implements SalaryService {

	@Autowired
	private SalaryRepository saloryRepository;

	public void saveSaloryInfo(SaloryCommand saloryCommand) {

		try {
			Date today = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);

			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);

			SaloryInfo info = new SaloryInfo();
			float grossEarings = saloryCommand.getBasic() + saloryCommand.getAllowance()
					+ saloryCommand.getSpecialpayment() + saloryCommand.getIncentive();
			float providentFund = saloryCommand.getBasic() / 100;
			providentFund = providentFund * 12;
			float incomeTaxamount;
			float salory = saloryCommand.getBasic() + saloryCommand.getAllowance() + saloryCommand.getSpecialpayment();
			if (salory <= 250000) {
				incomeTaxamount = 0;
			} else if (salory <= 500000) {
				float sal = salory - 250000;
				incomeTaxamount = (sal / 100) * 5;
				System.out.println(incomeTaxamount);
			} else if (salory <= 1000000) {
				float sal = salory - 500000;
				incomeTaxamount = (sal / 100) * 20;
				incomeTaxamount = incomeTaxamount + (250000 / 100) * 5;
				System.out.println(incomeTaxamount);
			} else {
				float sal = salory - 1000000;
				incomeTaxamount = (sal / 100) * 30;
				incomeTaxamount = incomeTaxamount + (500000 / 100) * 20;
				incomeTaxamount = incomeTaxamount + (250000 / 100) * 5;
				System.out.println(incomeTaxamount);
			}
			float totalDeductions = incomeTaxamount + 100f + providentFund;
			float netSalary = grossEarings - totalDeductions;
			info.setBasicSalory(saloryCommand.getBasic());
			info.setMonth(month);
			info.setYear(year);
			info.setIncentive(saloryCommand.getIncentive());
			info.setSpecialAllowance(saloryCommand.getAllowance());
			info.setSpecialPayment(saloryCommand.getSpecialpayment());
			info.setGrossEarings(grossEarings);
			info.setTotalDeduction(totalDeductions);
			info.setNetSalory(netSalary);
			info.setIncomeTax(incomeTaxamount);
			info.setProvidentFund(providentFund);
			if (saloryCommand.getBasic() <= 15000) {
				info.setProfessionalTax(0);
			} else if (saloryCommand.getBasic() > 15000 && saloryCommand.getBasic() <= 20000) {
				info.setProfessionalTax(150f);
			} else {
				info.setProfessionalTax(200f);
			}
			info.setEmployeeNumber(Integer.parseInt(saloryCommand.getEmployeenumber()));
			saloryRepository.save(info);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DatabaseException("Database is Down");
		}
	}

	@Override
	public List<SaloryInfoListDTO> getSaloryInfoList() {
		List<SaloryInfo> saloryInfos = null;
		List<SaloryInfoListDTO> saloryInfoListDTOs = new ArrayList<SaloryInfoListDTO>();
		try {
			saloryInfos = saloryRepository.findAll();
			if (saloryInfos != null) {
				int id = 1;
				for (SaloryInfo infos : saloryInfos) {
					SaloryInfoListDTO infoDTOS = new SaloryInfoListDTO();
					infoDTOS.setBasicSalaory(infos.getBasicSalory());
					infoDTOS.setEmployeeNumber(infos.getEmployeeNumber());
					infoDTOS.setId(id++);
					int month = infos.getMonth();
					switch (month) {
					case 0:
						infoDTOS.setMonth("January");
						break;
					case 1:
						infoDTOS.setMonth("February");
						break;

					case 2:
						infoDTOS.setMonth("March");
						break;
					case 3:
						infoDTOS.setMonth("April");
						break;

					case 4:
						infoDTOS.setMonth("May");
						break;
					case 5:
						infoDTOS.setMonth("June");
						break;

					case 6:
						infoDTOS.setMonth("July");
						break;
					case 7:
						infoDTOS.setMonth("August");
						break;

					case 8:
						infoDTOS.setMonth("September");
						break;
					case 9:
						infoDTOS.setMonth("October");
						break;

					case 10:
						infoDTOS.setMonth("November");
						break;

					default:
						infoDTOS.setMonth("December");
						break;
					}
					infoDTOS.setIncentive(infos.getIncentive());
					infoDTOS.setSpecialAllowance(infos.getSpecialAllowance());
					infoDTOS.setSpecialPayment(infos.getSpecialPayment());
					infoDTOS.setNetSalory(infos.getNetSalory());
					infoDTOS.setTotalDeductions(infos.getTotalDeduction());
					infoDTOS.setGrossEarings(infos.getGrossEarings());
					infoDTOS.setProvidentFund(infos.getProvidentFund());
					infoDTOS.setProfessionalTax(infos.getProfessionalTax());
					infoDTOS.setIncomeTax(infos.getIncomeTax());
					saloryInfoListDTOs.add(infoDTOS);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			new DatabaseException("Database is down");
		}
		return saloryInfoListDTOs;
	}

	@Override
	public SaloryInfo getSalaryProcessedOrNot(int employeeNumber) {

		SaloryInfo saloryInfo = null;
		try {
			Date today = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			saloryInfo = saloryRepository.findByEmployeeNumberAndYearAndMonth(employeeNumber, year, month);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DatabaseException("Database has down");
		}
		return saloryInfo;
	}
}
