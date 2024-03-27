package org.unical.resultProcessor.dao;


import org.springframework.data.repository.CrudRepository;
import org.unical.resultProcessor.model.Grade;

public interface GradeRepo extends CrudRepository<Grade, Integer>{

	
}
