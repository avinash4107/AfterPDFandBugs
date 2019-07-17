package com.centraprise.hrmodule.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.centraprise.hrmodule.entity.AssignmentInformation;
import com.centraprise.hrmodule.entity.BankInformation;
import com.centraprise.hrmodule.entity.EmployeeAddress;
import com.centraprise.hrmodule.entity.EmployeeDetails;
import com.centraprise.hrmodule.entity.ProvidentFundInformation;
import com.centraprise.hrmodule.exception.DatabaseException;
import com.centraprise.hrmodule.exception.DateParserException;
import com.centraprise.hrmodule.helper.DateParser;
import com.centraprise.hrmodule.model.EmployeeCommand;
import com.centraprise.hrmodule.model.EmployeeInfoListDTO;
import com.centraprise.hrmodule.model.EmployeeListDTO;
import com.centraprise.hrmodule.repository.EmployeeRepository;

@Service
//@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	@Autowired
	private DateParser dateParser;
	@Autowired
	private EmployeeRepository employeeRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<EmployeeListDTO> getEmployeesList() {
		List<EmployeeDetails> employeeDetails = null;
		List<EmployeeListDTO> employeeListDTOs = new ArrayList<EmployeeListDTO>();
		try {
			employeeDetails = employeeRepository.findByEmployeeActive(true);
			if (employeeListDTOs != null) {
				int i = 1;
				for (EmployeeDetails empDetais : employeeDetails) {
					EmployeeListDTO dto = new EmployeeListDTO();
					dto.setId(i++);
					dto.setPrimaryEmployeeId(empDetais.getEmpId());
					dto.setPanNumber(empDetais.getPanNumber());
					dto.setEmployeeId(empDetais.getEmployeeNumber());
					for (AssignmentInformation info : empDetais.getAssignmentInfo()) {
						dto.setJoiningDate(dateParser.parseDateToString(info.getDateOfJoining()));
					}
					dto.setName(empDetais.getEmployeeName());
					employeeListDTOs.add(dto);
				}
			}
			log.info("Returningn Employee details===>" + employeeListDTOs);
		} catch (Exception e) {
			log.error("Database Exception Catched===>" + e.getMessage());
			e.printStackTrace();
			throw new DatabaseException("Datebase is Down");
		}
		return employeeListDTOs;
	}

	@Override
	public void saveEmployee(EmployeeCommand employeeDetails) {
		log.info("saveEmployee(EmployeeCommand employeeDetails) invoked with data ====>" + employeeDetails);
		EmployeeDetails empDetails = new EmployeeDetails();
		EmployeeAddress address = new EmployeeAddress();
		AssignmentInformation assignmentInfo = new AssignmentInformation();
		BankInformation bankInfo = new BankInformation();
		ProvidentFundInformation providentInfo = new ProvidentFundInformation();
		Set<AssignmentInformation> infos = new HashSet<>();
		Set<EmployeeAddress> empaddress = new HashSet<>();
		Set<ProvidentFundInformation> fundinfos = new HashSet<ProvidentFundInformation>();
		Set<BankInformation> bankInfos = new HashSet<BankInformation>();
		try {
			System.out.println("here ok");
			empDetails.setEmployeeNumber(employeeDetails.getEmployeenumber());
			empDetails.setEmployeeName(employeeDetails.getName());
			empDetails.setSex(employeeDetails.getGender());
			empDetails.setPanNumber(employeeDetails.getPan());
			empDetails.setMaritalStatus(employeeDetails.getMaritalstatus());
			empDetails.setEmailAddress(employeeDetails.getEmail());
			empDetails.setPhoneNumber(employeeDetails.getPhone());
			empDetails.setPassword(employeeDetails.getPassword());
			empDetails.setEmployeeActive(true);
			address.setFlatNumber(employeeDetails.getDoornum());
			address.setVillage(employeeDetails.getVlg());
			address.setMandal(employeeDetails.getMandal());
			address.setCountry(employeeDetails.getCountry());
			address.setState(employeeDetails.getState());
			address.setDistrict(employeeDetails.getDistrict());
			address.setPincode(Integer.parseInt(employeeDetails.getPin()));
			try {
				empDetails.setDateOfBirth(dateParser.parseDate(employeeDetails.getBday()));
				assignmentInfo.setAssignmentEndDate(dateParser.parseDate(employeeDetails.getEndDate()));
				assignmentInfo.setAssignmentStartDate(dateParser.parseDate(employeeDetails.getStartdate()));
				assignmentInfo.setDateOfJoining(dateParser.parseDate(employeeDetails.getJoindate()));
				providentInfo.setDateOfLeaving(dateParser.parseDate(employeeDetails.getPreviousempleavingdate()));
				providentInfo.setStartDate(dateParser.parseDate(employeeDetails.getPreviousempstartdate()));
				providentInfo.setEndDate(dateParser.parseDate(employeeDetails.getPreviousempenddate()));
			} catch (DateParserException e) {
				e.printStackTrace();
				log.error("Exception while parsing date =======>" + e);
				throw new DateParserException("error while parsing date");
			}
			address.setEmployeeDetails(empDetails);
			empaddress.add(address);
			empDetails.setAddress(empaddress);
			assignmentInfo.setJob(employeeDetails.getJob());
			assignmentInfo.setManager(employeeDetails.getManager());
			assignmentInfo.setYearsOfService(employeeDetails.getService());
			assignmentInfo.setEmployeeDetails(empDetails);
			infos.add(assignmentInfo);
			empDetails.setAssignmentInfo(infos);

			bankInfo.setAccountNumber(employeeDetails.getAccountnumber());
			bankInfo.setBankName(employeeDetails.getBankname());
			bankInfo.setIfscCode(employeeDetails.getIfsc());
			bankInfo.setEmployeeDetails(empDetails);
			bankInfos.add(bankInfo);
			empDetails.setBankInfo(bankInfos);
			providentInfo.setUanNumber(employeeDetails.getUan());
			providentInfo.setAdharNumber(employeeDetails.getAdhar());
			providentInfo.setPrevoiusEmployee(employeeDetails.getPreviousemp());

			fundinfos.add(providentInfo);
			empDetails.setProvidentInfo(fundinfos);
			providentInfo.setEmployeeDetails(empDetails);
			employeeRepository.save(empDetails);
		} catch (Exception e) {
			log.error("Exception Catched =======>" + e);
			e.printStackTrace();
			throw new DatabaseException("Parsing exception miss match in data types");
		}

	}

	@Override
	public EmployeeInfoListDTO getEmployeeById(int employeenumber) {
		EmployeeDetails employeeDetails = null;
		EmployeeInfoListDTO dtos = new EmployeeInfoListDTO();
		try {
			employeeDetails = employeeRepository.findByEmpIdAndEmployeeActive(employeenumber, true);
			log.info("EmployeeDetails=========" + employeeDetails);
			System.out.println(employeeDetails);
			dtos.setEmployeenumber(employeeDetails.getEmployeeNumber());
			dtos.setName(employeeDetails.getEmployeeName());
			dtos.setEmpId(employeeDetails.getEmpId());
			dtos.setGender(employeeDetails.getSex());
			try {
				dtos.setBday(dateParser.parseDateToString(employeeDetails.getDateOfBirth()));
			} catch (Exception e) {
				log.error("Error while parsing date===>" + e.getMessage());
				e.printStackTrace();
				throw new DateParserException("Error While parsing date");

			}
			dtos.setPan(employeeDetails.getPanNumber());
			dtos.setMaritalstatus(employeeDetails.getMaritalStatus());
			dtos.setEmail(employeeDetails.getEmailAddress());
			dtos.setPhone(employeeDetails.getPhoneNumber());
			dtos.setPassword(employeeDetails.getPassword());
			if (employeeDetails.getAddress() != null) {
				for (EmployeeAddress add : employeeDetails.getAddress()) {
					dtos.setDoornum(add.getFlatNumber());
					dtos.setVlg(add.getVillage());
					dtos.setState(add.getState());
					dtos.setMandal(add.getMandal());
					dtos.setDistrict(add.getDistrict());
					dtos.setCountry(add.getCountry());
					dtos.setPin(String.valueOf(add.getPincode()));
				}
			}
			if (employeeDetails.getAssignmentInfo() != null) {
				for (AssignmentInformation assignInfo : employeeDetails.getAssignmentInfo()) {

					try {
						dtos.setStartdate(dateParser.parseDateToString(assignInfo.getAssignmentStartDate()));
						dtos.setEndDate(dateParser.parseDateToString(assignInfo.getAssignmentEndDate()));
						dtos.setJoindate(dateParser.parseDateToString(assignInfo.getDateOfJoining()));
					} catch (Exception e) {
						e.printStackTrace();
						log.error("Error while parsing date===>" + e.getMessage());
						throw new DateParserException("Error While parsing date");
					}

					dtos.setJob(assignInfo.getJob());

					dtos.setService(String.valueOf(assignInfo.getYearsOfService()));
					dtos.setManager(assignInfo.getManager());
				}
			}
			if (employeeDetails.getBankInfo() != null) {
				for (BankInformation bankInfo : employeeDetails.getBankInfo()) {
					dtos.setAccountnumber(bankInfo.getAccountNumber());
					dtos.setBankname(bankInfo.getBankName());
					dtos.setIfsc(bankInfo.getIfscCode());
				}
			}
			if (employeeDetails.getProvidentInfo() != null) {
				for (ProvidentFundInformation proviInformation : employeeDetails.getProvidentInfo()) {
					dtos.setUan(proviInformation.getUanNumber());
					dtos.setAdhar(proviInformation.getAdharNumber());
					dtos.setPreviousemp(proviInformation.getPrevoiusEmployee());
					try {
						dtos.setPreviousempleavingdate(
								dateParser.parseDateToString(proviInformation.getDateOfLeaving()));
						dtos.setPreviousempstartdate(dateParser.parseDateToString(proviInformation.getStartDate()));
						dtos.setPreviousempenddate(dateParser.parseDateToString(proviInformation.getEndDate()));
					} catch (Exception e) {
						e.printStackTrace();
						log.error("Error while parsing date===>" + e.getMessage());
						throw new DateParserException("Error While parsing date");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error in  database===>" + e.getMessage());
			throw new DatabaseException("Datebase is Down");
		}
		log.info("Returning data with========>" + dtos);
		return dtos;
	}

	@Override
	@Transactional
	// @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	// @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	// @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor =
	// Exception.class, readOnly = false)
	public void updateEmployee(EmployeeCommand employeeCommand) throws Exception {

		try {
			EmployeeDetails employeeDetails = employeeRepository
					.findByEmpIdAndEmployeeActive(employeeCommand.getEmpId(), true);
			if (employeeCommand.getEmployeenumber() != 0) {
				employeeDetails.setEmployeeNumber(employeeCommand.getEmployeenumber());
			}
			employeeDetails.setEmployeeName(employeeCommand.getName());
			employeeDetails.setSex(employeeCommand.getGender());
			try {
				employeeDetails.setDateOfBirth(dateParser.parseDate(employeeCommand.getBday()));
			} catch (DateParserException e) {
				log.error("Exception while parsing date =======>" + e);
				e.printStackTrace();
				throw new DatabaseException("error while parsing date");
			}
			employeeDetails.setPanNumber(employeeCommand.getPan());
			employeeDetails.setMaritalStatus(employeeCommand.getMaritalstatus());
			employeeDetails.setEmailAddress(employeeCommand.getEmail());
			employeeDetails.setPhoneNumber(employeeCommand.getPhone());
			employeeDetails.setPassword(employeeCommand.getPassword());
			employeeDetails.setEmployeeActive(true);
			Set<EmployeeAddress> address = employeeDetails.getAddress();
			for (EmployeeAddress addr : address) {
				addr.setCountry(employeeCommand.getCountry());
				addr.setDistrict(employeeCommand.getDistrict());
				addr.setFlatNumber(employeeCommand.getDoornum());
				addr.setVillage(employeeCommand.getVlg());
				addr.setMandal(employeeCommand.getMandal());
				addr.setPincode(Integer.parseInt(employeeCommand.getPin()));
				addr.setState(employeeCommand.getState());
				addr.setEmployeeDetails(employeeDetails);
				address.add(addr);

			}
			Set<AssignmentInformation> assignmentInformtion = employeeDetails.getAssignmentInfo();
			for (AssignmentInformation assignInfo : assignmentInformtion) {
				try {
					assignInfo.setAssignmentEndDate(dateParser.parseDate(employeeCommand.getEndDate()));
					assignInfo.setAssignmentStartDate(dateParser.parseDate(employeeCommand.getStartdate()));
					assignInfo.setDateOfJoining(dateParser.parseDate(employeeCommand.getJoindate()));
				} catch (DateParserException e) {
					log.error("Exception while parsing date =======>" + e);
					e.printStackTrace();
					throw new DatabaseException("error while parsing date");
				}

				assignInfo.setJob(employeeCommand.getJob());
				assignInfo.setManager(employeeCommand.getManager());
				assignInfo.setYearsOfService(employeeCommand.getService());
				assignInfo.setEmployeeDetails(employeeDetails);
				assignmentInformtion.add(assignInfo);
			}
			Set<BankInformation> bankInformations = employeeDetails.getBankInfo();
			for (BankInformation bankInformation : bankInformations) {
				bankInformation.setAccountNumber(employeeCommand.getAccountnumber());
				bankInformation.setIfscCode(employeeCommand.getIfsc());
				bankInformation.setBankName(employeeCommand.getBankname());
				bankInformation.setEmployeeDetails(employeeDetails);
				bankInformations.add(bankInformation);
			}
			Set<ProvidentFundInformation> providentFundInformations = employeeDetails.getProvidentInfo();
			for (ProvidentFundInformation pInfo : providentFundInformations) {
				pInfo.setAdharNumber(employeeCommand.getAdhar());
				try {
					pInfo.setDateOfLeaving(dateParser.parseDate(employeeCommand.getPreviousempleavingdate()));
					pInfo.setStartDate(dateParser.parseDate(employeeCommand.getPreviousempstartdate()));
					pInfo.setEndDate(dateParser.parseDate(employeeCommand.getPreviousempenddate()));
				} catch (DateParserException e) {
					log.error("Exception while parsing date =======>" + e);
					e.printStackTrace();
					throw new DatabaseException("error while parsing date");
				}
				pInfo.setPrevoiusEmployee(employeeCommand.getPreviousemp());
				pInfo.setUanNumber(employeeCommand.getUan());
				pInfo.setEmployeeDetails(employeeDetails);
				providentFundInformations.add(pInfo);
			}
			employeeDetails.setAddress(address);
			employeeDetails.setAssignmentInfo(assignmentInformtion);
			employeeDetails.setBankInfo(bankInformations);
			employeeDetails.setProvidentInfo(providentFundInformations);
			entityManager.merge(employeeDetails);
		} catch (Exception e) {
			log.error("Database Exception=======>" + e);
			e.printStackTrace();
			throw new DatabaseException("Datebase is Down");
		}
	}

	@Override
	@Transactional
	public void deleteEmployeeById(int employeeId) {
		int count = 0;
		try {
			log.info("deleteEmployeeById(int employeeId) invoked==>" + employeeId);
			// count = employeeRepository.updateByEmpId(employeeId);
			EmployeeDetails details = entityManager.find(EmployeeDetails.class, employeeId);
			details.setEmployeeActive(false);
			entityManager.merge(details);
			System.out.println(count);
		} catch (Exception e) {
			log.error("Database Exception=======>" + e);
			e.printStackTrace();
			throw new DatabaseException("Datebase is Down");
		}

	}

	@Override
	public EmployeeDetails employeeNumberExistsOrNot(int empId) {

		EmployeeDetails details = null;
		try {
			details = employeeRepository.findByEmpIdAndEmployeeActive(empId, true);
			log.info("Employeee number already exists with number=======>" + empId);
		} catch (Exception e) {
			log.error("Database Exception=======>" + e);
			e.printStackTrace();
			throw new DatabaseException("Datebase is Down");
		}
		return details;
	}

}
