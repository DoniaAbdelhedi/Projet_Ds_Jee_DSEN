package com.de.tekup.Controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.de.tekup.dto.ClientRequest;
import com.de.tekup.entities.ClientId;
import com.de.tekup.services.Clientservice;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ClientRest {
	
private Clientservice serviceClient;

	
	@GetMapping({"/clients"})
	public String getIndex(Model model) {
		model.addAttribute("clients", serviceClient.getClients());
		return "/clients/index";
	}
	
	
	// Add new Client
	@GetMapping({"clients/add"})
	public String newClient(@ModelAttribute("client") ClientRequest client, Model model) {
		model.addAttribute("client", client);
		return "/clients/add";
	}

		
		@PostMapping("/clients/addClient")
		public String saveOrUpdate(@Valid @ModelAttribute("client")ClientRequest client,BindingResult bindingResult,Model model) {
			// if errors of validation return to form
			if (bindingResult.hasErrors()) {
				model.addAttribute("client", client);
			
				return "/clients/add";

		      }
			else
			{
			serviceClient.saveClient(client);
			return "redirect:/clients";
			}
		}
		
		
	@GetMapping("/clients/{clientId}/delete")
	public String deleteClient(@PathVariable("clientId") ClientId id) {
		
		serviceClient.deleteById(id);
		return "redirect:/clients";
	}


}
