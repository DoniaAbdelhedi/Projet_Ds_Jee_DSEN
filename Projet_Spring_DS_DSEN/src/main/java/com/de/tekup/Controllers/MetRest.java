package com.de.tekup.Controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.de.tekup.dto.MetRequest;
import com.de.tekup.services.Metservice;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MetRest {
	
	private Metservice serviceMet;

	@GetMapping({"/mets"})
	public String getIndex(Model model) {
		model.addAttribute("mets", serviceMet.listMets());
		return "/mets/index";
	}
	

		@GetMapping({"mets/addEntree"})
		public String newEntree(@ModelAttribute("met") MetRequest met, Model model) {
			model.addAttribute("met", met);
			return "/mets/addEntree";
		}
		@GetMapping({"mets/addPlat"})
		public String newPlat(@ModelAttribute("met") MetRequest met, Model model) {
			model.addAttribute("met", met);
			return "/mets/addPlat";
		}
		@GetMapping({"mets/addDessert"})
		public String newDessert(@ModelAttribute("met") MetRequest met, Model model) {
			model.addAttribute("met", met);
			return "/mets/addDessert";
		}
		
		@PostMapping("/mets/addEntree")
		public String saveentree(@Valid @ModelAttribute("met") MetRequest met ,BindingResult bindingResult,Model model) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("met", met);
			
				return "/mets/add";

		      }
			else
			{
			serviceMet.saveEntree(met);
			return "redirect:/mets";
			}
		}
		@PostMapping("/mets/addPlat")
		public String savePlat(@Valid @ModelAttribute("met") MetRequest met ,BindingResult bindingResult,Model model) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("met", met);
			
				return "/mets/add";

		      }
			else
			{
			serviceMet.savePlat(met);
			return "redirect:/mets";
			}
		}
		@PostMapping("/mets/addDessert")
		public String saveDessert(@Valid @ModelAttribute("met") MetRequest met ,BindingResult bindingResult,Model model) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("met", met);
			
				return "/mets/add";

		      }
			else
			{
			serviceMet.saveDessert(met);
			return "redirect:/mets";
			}
		}
	
	@GetMapping("/mets/{metId}/delete")
	public String deleteMet(@PathVariable("metId") long id) {
		
		serviceMet.deleteById(id);
		return "redirect:/mets";
	}

}
