package org.unical.resultProcessor.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.unical.resultProcessor.model.Result;

public interface ResultRepo extends CrudRepository<Result, Integer>{
	
	List<Result> findByCourseIdAndMatricNoAndUnit(Long courseId, String matricNo, String unit);
	
}
