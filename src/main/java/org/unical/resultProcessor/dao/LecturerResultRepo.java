package org.unical.resultProcessor.dao;

import org.springframework.data.repository.CrudRepository;
import org.unical.resultProcessor.model.LecturerResult;

public interface LecturerResultRepo extends CrudRepository<LecturerResult, Long>{

	LecturerResult findByLecturerResultId(Long lecturerResultId);
}
