
package com.centraprise.hrmodule.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centraprise.hrmodule.entity.EmployeeDetails;
import com.centraprise.hrmodule.entity.ManageLeave;
import com.centraprise.hrmodule.entity.MonthLeave;
import com.centraprise.hrmodule.exception.CentrapriseException;
import com.centraprise.hrmodule.exception.DateParserException;
import com.centraprise.hrmodule.helper.DateParser;
import com.centraprise.hrmodule.model.FinalSalaryInfoDTO;
import com.centraprise.hrmodule.model.LeaveForm;
import com.centraprise.hrmodule.model.LeavesInfoDTO;
import com.centraprise.hrmodule.repository.EmployeeRepository;
import com.centraprise.hrmodule.repository.LeaveManagementRepository;

@Service
public class LeaveManagementServiceImpl implements LeaveManagementService {

	private static final Logger log = LoggerFactory.getLogger(LeaveManagementServiceImpl.class);
	@Autowired
	private LeaveManagementRepository leaveManagementRepository;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DateParser dateParser;

	@Autowired
	private List<Calendar> leavesInfo;

	@Override
	public List<LeavesInfoDTO> getLeavesInfo() {
		List<LeavesInfoDTO> infoDtos = new ArrayList<LeavesInfoDTO>();
		try {
			Date today = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			int year = cal.get(Calendar.YEAR);
			List<ManageLeave> manageLeave = leaveManagementRepository.findAll();
			LeavesInfoDTO dto = null;
			if (manageLeave != null) {
				int i = 1;
				for (ManageLeave leave : manageLeave) {
					for (MonthLeave monthlyLeaves : leave.getMonthLeavesInfo()) {
						dto = new LeavesInfoDTO();
						dto.setId(i++);
						dto.setEmployeeNumber(leave.getEmployeeNumber());
						dto.setLeaveEndDate(monthlyLeaves.getLeaveEndDate());
						dto.setLeaveStartDate(monthlyLeaves.getLeaveStartDate());
						dto.setLeaveType(monthlyLeaves.getLeaveType());
						dto.setMonth(monthlyLeaves.getMonth());
						dto.setNumberOfdays(monthlyLeaves.getNumberOfDaysLeave());
						infoDtos.add(dto);
						log.info("Leaves Info====>" + infoDtos);
					}
				}
			} else {
				infoDtos.add(new LeavesInfoDTO());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return infoDtos;
	}

	@Override
	@Transactional
	public void insertEmployeeLeavesDetails(LeaveForm leaveForm) {

		try {

			String[] str = leaveForm.getEndDate().split("/");
			int year = Integer.parseInt(str[0]);

			String[] str1 = leaveForm.getStartDate().split("/");
			int startYear = Integer.parseInt(str1[0]);

			if (startYear != year) {
				for (int i = 0; i < 2; i++) {
					if (i == 0) {
						insertLeaveInfo(leaveForm, startYear, leaveForm.getStartDate(), startYear + "/12/31");
					} else {
						insertLeaveInfo(leaveForm, year, year + "/01/01/", leaveForm.getEndDate());
					}
				}

			} else {
				insertLeaveInfo(leaveForm, year, leaveForm.getStartDate(), leaveForm.getEndDate());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void insertLeaveInfo(LeaveForm leaveForm, int year, String startDate, String endDate) {

		log.info("insertLeaveInfo(LeaveForm leaveForm, int year, String startDate, String endDate)====>" + leaveForm);
		log.info("year==>" + year);
		log.info("startDate==>" + startDate);
		log.info("endDate==>" + endDate);

		ManageLeave leave = leaveManagementRepository
				.findByEmployeeNumber(Integer.parseInt(leaveForm.getEmployeenumber()));

		if (leave != null) {
			log.info("Employee Already Exists or not=====>" + leave.getEmployeeNumber());
			leave.setEmployeeNumber(Integer.parseInt(leaveForm.getEmployeenumber()));

			Set<MonthLeave> monthleave = leave.getMonthLeavesInfo();
			MonthLeave mon = new MonthLeave();
			mon.setMonth(leaveForm.getMonth_list());
			// mon.setNumberOfDaysLeave(leaveForm.getNumberofdays());
			mon.setLeaveEndDate(leaveForm.getEndDate());
			mon.setLeaveStartDate(leaveForm.getStartDate());
			mon.setYear(year);
			try {
				mon.setNumberOfDaysLeave(findNumberOfdaysLeave(startDate, endDate));
			} catch (Exception e) {
				e.printStackTrace();
				throw new DateParserException("Error while parsing date");
			}
			mon.setLeaveType(leaveForm.getLeaveType());
			mon.setManageLeave(leave);
			monthleave.add(mon);
			leave.setMonthLeavesInfo(monthleave);
			// leaveManagementRepository.getOne(man);
			em.merge(leave);
		} else {
			ManageLeave manageLeave = new ManageLeave();
			manageLeave.setEmployeeNumber(Integer.parseInt(leaveForm.getEmployeenumber()));
			Set<MonthLeave> monthLeave = new HashSet<>();
			MonthLeave mLeave = new MonthLeave();
			mLeave.setMonth(leaveForm.getMonth_list());
			mLeave.setLeaveEndDate(leaveForm.getEndDate());
			mLeave.setLeaveStartDate(leaveForm.getStartDate());
			mLeave.setLeaveType(leaveForm.getLeaveType());
			mLeave.setYear(year);
			try {
				mLeave.setNumberOfDaysLeave(findNumberOfdaysLeave(startDate, endDate));
			} catch (Exception e) {
				e.printStackTrace();
				throw new DateParserException("Error while parsing date");
			}
			mLeave.setManageLeave(manageLeave);
			monthLeave.add(mLeave);

			manageLeave.setMonthLeavesInfo(monthLeave);
			// leaveManagementRepository.save(manageLeave);
			em.persist(manageLeave);
		}
	}

	public int findNumberOfdaysLeave(String strtDate, String eDate) throws ParseException {
		int numberOfdays = 1;
		// Date startDate = dateParser.parseDate(strtDate);
		// Date endDate = dateParser.parseDate(eDate);

		Date startDate = dateParser.parseDateYYYY(strtDate);
		Date endDate = dateParser.parseDateYYYY(eDate);
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(startDate);
		cal2.setTime(endDate);
		while (cal1.before(cal2)) {
			if (isBusinessDay(cal1)) {
				++numberOfdays;
			}
			cal1.add(Calendar.DATE, 1);
		}
		return numberOfdays;
	}

	public boolean isBusinessDay(Calendar cal) {

		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK)) {

			return false;
		} else {
			for (Calendar calen : leavesInfo) {
				if (calen.get(Calendar.MONTH) == cal.get(Calendar.MONTH)
						&& calen.get(Calendar.DATE) == cal.get(Calendar.DATE)) {
					return false;
				}
			}
			return true;
		}

	}

	@Override
	public List<Integer> getEmployeeList() {
		try {
			List<EmployeeDetails> details = employeeRepository.findByEmployeeActive(true);
			List<Integer> employeeNumbers = new ArrayList<>();
			if (details != null) {
				for (EmployeeDetails det : details) {
					employeeNumbers.add(det.getEmployeeNumber());
				}
				return employeeNumbers;
			} else {
				return employeeNumbers;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CentrapriseException("Database is down");
		}
	}

	@Override
	public List<FinalSalaryInfoDTO> getFinalSaloryInfo() {
		try {

			Date today = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			List<FinalSalaryInfoDTO> finalDto = new ArrayList<FinalSalaryInfoDTO>();
			List<EmployeeDetails> employeeDetails = employeeRepository.findAll();
			if (employeeDetails != null && employeeDetails.size() > 0) {
				for (EmployeeDetails empDetails : employeeDetails) {
					FinalSalaryInfoDTO finalInfoDTO = new FinalSalaryInfoDTO();
					finalInfoDTO.setEmployeeNumber(empDetails.getEmployeeNumber());
					ManageLeave manageLeave = leaveManagementRepository
							.findByEmployeeNumber(empDetails.getEmployeeNumber());
					if (manageLeave != null) {
						int i = 1;
						float creditedLeaves = 22;
						int usedLeaves = 0;
						// finalInfoDTO.setEmployeeNumber(empDetails.getEmployeeNumber());
						finalInfoDTO.setId(i++);
						finalInfoDTO.setYear(year);
						for (MonthLeave leave : manageLeave.getMonthLeavesInfo()) {
							if (leave.getYear() == year) {
								usedLeaves = usedLeaves + leave.getNumberOfDaysLeave();
							}
						}
						float balanceLeaves = 0;
						boolean flag = false;
						if (creditedLeaves < usedLeaves) {
							balanceLeaves = usedLeaves - creditedLeaves;
							flag = true;
						} else {
							balanceLeaves = creditedLeaves - usedLeaves;
						}
						finalInfoDTO.setCreditedLeavs(creditedLeaves);
						finalInfoDTO.setAvailed(usedLeaves);
						finalInfoDTO.setBalance(balanceLeaves);
						if (flag) {
							finalInfoDTO.setLossOfPay(balanceLeaves);
						} else {
							finalInfoDTO.setLossOfPay(0);
						}
						finalDto.add(finalInfoDTO);
					} else {
						finalInfoDTO.setAvailed(0);
						finalInfoDTO.setBalance(22);
						finalInfoDTO.setCreditedLeavs(22);
						finalInfoDTO.setYear(year);
						finalInfoDTO.setLossOfPay(0);
						finalDto.add(finalInfoDTO);
					}
				}
			}

			return finalDto;

		} catch (Exception e) {
			e.printStackTrace();
			throw new CentrapriseException("Database is down");
		}
	}
}
