package com.centraprise.hrmodule.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centraprise.hrmodule.entity.DeductionInfo;
import com.centraprise.hrmodule.exception.DatabaseException;
import com.centraprise.hrmodule.model.DeductionCommand;
import com.centraprise.hrmodule.model.DeductionsInfoListDTO;
import com.centraprise.hrmodule.repository.DeductionRepository;

@Service
public class DeductionServiceImpl implements DeductionService {

	private static final Logger log = LoggerFactory.getLogger(DeductionServiceImpl.class);

	@Autowired
	private DeductionRepository deductionRepository;

	@Override
	public void saveDeduction(DeductionCommand deductionCommand) {
		try {
			log.info("saveDeduction(DeductionCommand deductionCommand)===>" + deductionCommand);
			DeductionInfo deductionInfo = new DeductionInfo();
			BeanUtils.copyProperties(deductionCommand, deductionInfo);
			deductionInfo.setEmployeenumber(Integer.parseInt(deductionCommand.getEmployeeNumber()));
			System.out.println(deductionInfo);
			deductionRepository.save(deductionInfo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DatabaseException("Database is down");
		}
	}

	@Override
	public List<DeductionsInfoListDTO> getDeductionsInfo() {
		List<DeductionsInfoListDTO> infoListDTO = null;
		try {
			List<DeductionInfo> deductionInfo = deductionRepository.findAll();
			int i = 0;
			infoListDTO = new ArrayList<DeductionsInfoListDTO>();
			if (deductionInfo != null) {
				for (DeductionInfo decInfo : deductionInfo) {
					++i;
					DeductionsInfoListDTO deductions = new DeductionsInfoListDTO();
					deductions.setEmployeeNumber(decInfo.getEmployeenumber());
					deductions.setId(i);
					if (decInfo.getInsurance() != 0) {
						deductions.setC(decInfo.getInsurance());
					} else if (decInfo.getLifeInsurance() != 0) {
						deductions.setC(decInfo.getLifeInsurance());
					} else if (decInfo.getPpf() != 0) {
						deductions.setC(decInfo.getPpf());
					} else if (decInfo.getNsc() != 0) {
						deductions.setC(decInfo.getNsc());
					} else if (decInfo.getElss() != 0) {
						deductions.setC(decInfo.getElss());
					} else if (decInfo.getTaxsaver() != 0) {
						deductions.setC(decInfo.getTaxsaver());
					} else if (decInfo.getTutionfee() != 0) {
						deductions.setC(decInfo.getTutionfee());
					} else if (decInfo.getHousingloan() != 0) {
						deductions.setC(decInfo.getHousingloan());
					} else if (decInfo.getSss() != 0) {
						deductions.setC(decInfo.getSss());
					} else {
						deductions.setC(0);
					}
					deductions.setCcc(decInfo.getPension());
					deductions.setD(decInfo.getMediclaim());
					deductions.setDp(decInfo.getParents());
					deductions.setDs(decInfo.getSrcitizens());
					deductions.setCcg(decInfo.getInvestment());
					deductions.setDdb(decInfo.getTreatment());
					deductions.setDd(decInfo.getHandicapped());
					deductions.setU(decInfo.getPhysicallyhandi());
					deductions.setE(decInfo.getHigheredu());
					deductions.setEe(decInfo.getLoan());
					deductions.setCcd(decInfo.getCcd());
					infoListDTO.add(deductions);
				}
			}

		} catch (Exception e) {
			log.error("Exception Cacthed ====>" + e);
			e.printStackTrace();
			throw new DatabaseException("Database is down");

		}
		return infoListDTO;
	}

}
