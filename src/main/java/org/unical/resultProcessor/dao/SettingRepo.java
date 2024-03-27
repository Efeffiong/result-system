package org.unical.resultProcessor.dao;

import org.springframework.data.repository.CrudRepository;
import org.unical.resultProcessor.model.Setting;

public interface SettingRepo extends CrudRepository<Setting, Integer>{

	Setting findByDeptAndSessionAndCourseType(String dept, String session, String courseType);
}
