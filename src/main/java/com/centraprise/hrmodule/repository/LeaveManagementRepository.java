package com.centraprise.hrmodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centraprise.hrmodule.entity.ManageLeave;

public interface LeaveManagementRepository extends JpaRepository<ManageLeave, Integer> {

	// ManageLeave findByEmployeeNumberAndYear(int employeeNumber, int year);

	// List<ManageLeave> findByYear(int year);

	ManageLeave findByEmployeeNumber(int employeeNumber);

	// @Query(value = "SELECT * FROM ManageLeave e LEFT JOIN e.monthLeavesInfo m
	// where m.year = :year ", nativeQuery = true)
	// List<ManageLeave> findYear(@Param("year") int year);
}
