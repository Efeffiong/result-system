package org.unical.resultProcessor.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.unical.resultProcessor.model.Unit;

public interface UnitRepo extends CrudRepository<Unit, Integer> {
	
	List <Unit> findByFacultyAndDepartmentAndUnitDept(String faculty, String dept, String unit);
	
	Unit findByUnitDept(String unit);
}
