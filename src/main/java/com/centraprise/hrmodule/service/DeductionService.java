package com.centraprise.hrmodule.service;

import java.util.List;

import com.centraprise.hrmodule.model.DeductionCommand;
import com.centraprise.hrmodule.model.DeductionsInfoListDTO;

public interface DeductionService {

	void saveDeduction(DeductionCommand deductionCommand);

	List<DeductionsInfoListDTO> getDeductionsInfo();
}
