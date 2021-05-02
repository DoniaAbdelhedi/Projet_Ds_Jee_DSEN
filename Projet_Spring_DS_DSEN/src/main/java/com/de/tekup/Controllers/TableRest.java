package com.de.tekup.Controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.de.tekup.dto.TableRequest;
import com.de.tekup.services.Tableservice;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class TableRest {
	
private Tableservice serviceTable;

	
	@GetMapping({"/tables"})
	public String getIndex(Model model) {
		model.addAttribute("tables", serviceTable.getTables());
		return "/tables/index";
	}
	
	
	// Add new table
	@GetMapping({"tables/add"})
	public String newTable(@ModelAttribute("table") TableRequest table, Model model) {
		model.addAttribute("table", table);
		return "/tables/add";
	}

		
		@PostMapping("/tables/addtables")
		public String saveOrUpdate(@Valid @ModelAttribute("table")TableRequest table,BindingResult bindingResult,Model model) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("table", table);
			
				return "/tables/add";

		      }
			else
			{
				serviceTable.saveTable(table);
			return "redirect:/tables";
			}
		}
		
		
	@GetMapping("/tables/{tableId}/delete")
	public String deleteTable(@PathVariable("tableId") Integer id) {
		
		serviceTable.deleteById(id);
		return "redirect:/tables";
	}


}
