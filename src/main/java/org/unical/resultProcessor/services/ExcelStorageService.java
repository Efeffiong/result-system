package org.unical.resultProcessor.services;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.unical.resultProcessor.dao.LecturerResultRepo;

import org.unical.resultProcessor.model.LecturerResult;

public class ExcelStorageService {

	@Autowired
	static
	LecturerResultRepo lecturerResultRepo;
	
	public String uploadfile(MultipartFile file, LecturerResult result) throws IOException {
		String fileName = StringUtils.cleanPath(((MultipartFile) file).getOriginalFilename());
		
            Long lecturerResultId = System.nanoTime();
            Long courseId = result.getCourseId();
            String status = "Not Compiled";
            String lecturerSurname = result.getLecturerSurname();
            String lecturerFirstname = result.getLecturerFirstname();
            String lecturerMiddlename = result.getLecturerMiddlename();
            LocalDateTime localDateTime = LocalDateTime.now();
    		LocalDate localDate = localDateTime.toLocalDate();
    		
    		System.out.println("this is the file	" +file);
            LecturerResult lecturerResult = new LecturerResult(lecturerResultId, courseId, file.getBytes(), fileName, file.getContentType(), status, lecturerSurname, lecturerFirstname, lecturerMiddlename, localDate, localDate);
            try {
            	System.out.println(lecturerResult);
                lecturerResultRepo.save(lecturerResult);
                return "file has successfully been uploaded";
			} catch (Exception e) {
				System.out.println("error message" +e);
				 return "Ooops! file was not successfully uploaded";
			}
           
        
	}
	public static LecturerResult getFile(Long lecturerResultId) {
		LecturerResult lecturerResult = lecturerResultRepo.findByLecturerResultId(lecturerResultId);
		
		return lecturerResult;
                
    }
}
