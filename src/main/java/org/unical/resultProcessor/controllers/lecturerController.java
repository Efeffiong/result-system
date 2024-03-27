package org.unical.resultProcessor.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.unical.resultProcessor.dao.CourseRepo;
import org.unical.resultProcessor.dao.LecturerResultRepo;
import org.unical.resultProcessor.model.Course;
import org.unical.resultProcessor.model.LecturerResult;
import org.unical.resultProcessor.model.User;
import org.unical.resultProcessor.services.ExcelStorageService;

@Controller
public class lecturerController {

	@Autowired
	CourseRepo courseRepo;
	
	@Autowired
	LecturerResultRepo lecturerResultRepo;
	
	@GetMapping("/lecturerUploadResultForm")
	public String lecturerUploadResultForm(@SessionAttribute("userInfo") User userInfo, Model model, LecturerResult lecturerResult) {
		String dept = userInfo.getPosition();
		String session = userInfo.getSession();
		List<Course> courseInfo = courseRepo.findByDepartmentAndCourseSession(dept, session);
		model.addAttribute("courseInfo", courseInfo);
		model.addAttribute("userInfo", userInfo);
		Long lecturerResultId = System.nanoTime();
		model.addAttribute("id", lecturerResultId);
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDate localDate = localDateTime.toLocalDate();
		model.addAttribute("currentDate", localDate);
		return "lecturerSubmitResult";
	}
	
	@PostMapping("/uploadLecturerResult")
	public String lecturerSubmitResult(@RequestParam("resultFile") MultipartFile file, @RequestParam("courseId") Long resultId, @Valid LecturerResult lecturerResult,BindingResult result, Model model, @SessionAttribute("userInfo") User userInfo, ExcelStorageService excelStorageService) throws IOException{
		
		String dept = userInfo.getPosition();
		String session = userInfo.getSession();
		List<Course> courseInfo = courseRepo.findByDepartmentAndCourseSession(dept, session);
		model.addAttribute("courseInfo", courseInfo);
		model.addAttribute("userInfo", userInfo);
		Long lecturerResultId = System.nanoTime();
		model.addAttribute("id", lecturerResultId);
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDate localDate = localDateTime.toLocalDate();
		model.addAttribute("currentDate", localDate);
		
		if( file.isEmpty()) {
			
			model.addAttribute("errorMessage", "No file is selected!");
			return "lecturerSubmitResult";
		}
		
		else {
            String status = "Not Compiled";
            String fileName = StringUtils.cleanPath(((MultipartFile) file).getOriginalFilename());
            try {
            	if(fileName.contains("..")) {
                    model.addAttribute("errorMessage", "Oops! Incorrect file name");
                }
            	LecturerResult lecturerResul = new LecturerResult(lecturerResultId, resultId, file.getBytes(), fileName, file.getContentType(), status, userInfo.getSurname(), userInfo.getFirstname(), userInfo.getOthername(), localDate, localDate);
    			lecturerResultRepo.save(lecturerResul);
    			model.addAttribute("successMessage", "file has successfully been uploaded");
			} catch (IOException e) {
				model.addAttribute("errorMessage", "Input/Output exception error");
			}
            List<LecturerResult> excelResults = (List<LecturerResult>) lecturerResultRepo.findAll();
			model.addAttribute("excelResults", excelResults);
			return "viewLecturerResult";
		}
	}
	
	 @GetMapping("/downloadFile/{fileId}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
	        // Load file from database
	         LecturerResult lecturerResult = ExcelStorageService.getFile(fileId);

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(lecturerResult.getFileType()))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + lecturerResult.getFileName() + "\"")
	                .body(new ByteArrayResource(lecturerResult.getResultFile()));
	    }
}
