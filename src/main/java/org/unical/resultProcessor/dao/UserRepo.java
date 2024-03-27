package org.unical.resultProcessor.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.unical.resultProcessor.model.User;

public interface UserRepo extends CrudRepository<User, Long>{

	User findByUsername(String username);
	
	@Query("from User where username=?1 and password=?2")
	User findByUser(String username, String password);
	
	List<User> findBySessionAndPositionAndUserType(String session, String position, String userType);
	
	User findByUsernameAndPassword(String username, String password);
	
	User findByUserId(Long userId);
	
	User findByPosition(String position);
	
	List<User> findByFirstnameAndSurnameAndOthernameAndSessionAndPositionAndUserType(String firstname, String surname, String middlename, String session, String dept, String userType);
}
