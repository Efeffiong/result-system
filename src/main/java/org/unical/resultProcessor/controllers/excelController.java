package org.unical.resultProcessor.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.unical.resultProcessor.dao.CourseRepo;
import org.unical.resultProcessor.dao.ResultRepo;
import org.unical.resultProcessor.dao.UnitRepo;
import org.unical.resultProcessor.model.Result;
import org.unical.resultProcessor.model.User;

@Controller
public class excelController {

	@Autowired
	UnitRepo unitRepo;
	
	@Autowired
	CourseRepo courseRepo;
	
	@Autowired
	ResultRepo resultRepo;
	
	@GetMapping("/getExcelFile")
	public String getExcelFile(HttpServletRequest request, @SessionAttribute("userInfo") User userInfo) {
		
		request.setAttribute("courses", courseRepo.findByDepartmentAndCourseSession(userInfo.getPosition(), userInfo.getSession()));
		return "getExcelFile";
	}
	
	@SuppressWarnings("resource")
	@PostMapping("/import")
	public String mapReapExcelDatatoDB(@SessionAttribute("userInfo") User userInfo, @RequestParam("file") MultipartFile reapExcelDataFile, @ModelAttribute("courseId") Long courseId, HttpServletRequest request,
			@ModelAttribute("sheetNo") Integer sheetNo, @ModelAttribute("startingRow") Integer startingRow, 
			@ModelAttribute("endingRow") Integer endingRow, @ModelAttribute("matricNo") Integer matricNo,
			@ModelAttribute("asses") Integer asses, @ModelAttribute("exam") Integer exam) throws IOException {

	   List<Result> tempStudentList = new ArrayList<Result>();
	    XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(sheetNo - 1);

	    for(int i=startingRow;i<=endingRow ;i++) {
	        Result tempStudent = new Result();
	        
	        XSSFRow row = worksheet.getRow(i);
	        
	        tempStudent.setMatricNo(row.getCell(matricNo - 1).getStringCellValue());
	        tempStudent.setAsses(row.getCell(asses - 1).getNumericCellValue());
	        tempStudent.setExam(row.getCell(exam - 1).getNumericCellValue());
	        tempStudent.setUnit(userInfo.getPosition());
	        tempStudent.setCourseId(courseId);
	        resultRepo.save(tempStudent);
	        
	        tempStudentList.add(tempStudent); 
	        
	        
	    }
	   
	    request.setAttribute("results", tempStudentList);
	    return "viewExcelResults";
	}
}
