package org.unical.resultProcessor.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.unical.resultProcessor.dao.UnitRepo;
import org.unical.resultProcessor.model.Unit;

@Controller
public class unitController {

	@Autowired
	UnitRepo unitRepo;
	
	@GetMapping("/unitForm")
	public String showUnitForm(Unit unit) {
		return "add-Unit";
	}
	
	@GetMapping("/viewUnit")
	public String viewUnit( Model model) {
		model.addAttribute("units", unitRepo.findAll());
		return "units";
	}
	
	@PostMapping("/addUnit")
	public String addUnit(@Valid Unit unit, BindingResult result, Model model) {
		List <Unit> searcher = unitRepo.findByFacultyAndDepartmentAndUnitDept(unit.getFaculty(), unit.getDepartment(), unit.getUnitDept());
		if(result.hasErrors()) {
			model.addAttribute("errorMessage", "Warning: Unit must be correctly completely before submitting*");
			return "add-Unit";
		} else if (searcher.size() == 1) {
			model.addAttribute("errorMessage", "Warning: Unit already exist*");
			return "add-Unit";
		}else {
			unitRepo.save(unit);
			model.addAttribute("units", unitRepo.findAll());
			model.addAttribute("successMessage", "Unit successfully registered");
			return "units";
		}
		
		
	}
	
	@GetMapping("/editUnit/{unitId}")
	public String showUpdatForm(@PathVariable("unitId") Integer unitId, Model model) {
		Unit unit = unitRepo.findById(unitId)
				.orElseThrow(()-> new IllegalArgumentException("invalid id:" + unitId));
		model.addAttribute("unit", unit);
		return "update-Unit";
	}
	
	@PostMapping("/updateUnit/{unitId}")
	public String updateUnit(@PathVariable("unitId") Integer unitId, @Valid Unit unit, BindingResult result, Model model) {		
		List <Unit> searcher = unitRepo.findByFacultyAndDepartmentAndUnitDept(unit.getFaculty(), unit.getDepartment(), unit.getUnitDept());
		if(result.hasErrors()) {
			model.addAttribute("errorMessage", "Warning: Unit must be correctly completed before submitting*");
			return "add-Unit";
		} else if (searcher.size() == 1) {
			model.addAttribute("errorMessage", "Warning: Unit already exist*");
			return "add-Unit";
		}else {
			unit.setUnitId(unitId);
			unitRepo.save(unit);
			model.addAttribute("units", unitRepo.findAll());
			model.addAttribute("successMessage", "Unit has been successfully updated");
			return "units";
		}
		
	}
	
	@GetMapping("/deleteUnit/{unitId}")
	public String deleteUnit(@PathVariable("unitId") Integer unitId, Model model) {
		Unit unit = unitRepo.findById(unitId)
				.orElseThrow(()-> new IllegalArgumentException("invalid id:" + unitId));
		unitRepo.delete(unit);
		model.addAttribute("units", unitRepo.findAll());
		model.addAttribute("successMessage", "Unit has been successfully deleted");
		return "units";
	}
}
