package com.de.tekup.Controllers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.de.tekup.dto.MetRequest;
import com.de.tekup.dto.TableRequest;
import com.de.tekup.dto.TicketRequest;
import com.de.tekup.entities.Client;
import com.de.tekup.entities.ClientId;
import com.de.tekup.entities.Met;
import com.de.tekup.entities.Plat;
import com.de.tekup.entities.TableResto;
import com.de.tekup.entities.Ticket;
import com.de.tekup.services.Clientservice;
import com.de.tekup.services.Metservice;
import com.de.tekup.services.Tableservice;
import com.de.tekup.services.Ticketservice;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class TicketRest {
	
	private Ticketservice serviceTicket;
	private Clientservice serviceClient;
	private Tableservice serviceTable;
	private Metservice serviceMet;
	
	@GetMapping({"/tickets"})
	public String getIndex(Model model) {
		model.addAttribute("tickets", serviceTicket.getTickets());
		return "/tickets/index";
	}
	
	@GetMapping({"/tickets/{ticketId}/met/add"})
	public String addMet(@PathVariable("ticketId") Integer ticketId,Model model) {
	TicketRequest ticket =serviceTicket.findITicketRequestById(ticketId);
	Client client = ticket.getClient();
	TableRequest table =ticket.getTable();
	   List<MetRequest> metslist=ticket.getMets();
		model.addAttribute("ticket", ticket);
		model.addAttribute("client", client);
		model.addAttribute("table", table);
		model.addAttribute("metslist", metslist);
		
		model.addAttribute("mets", serviceMet.listMets());
		return "/tickets/addMet";
	}
	
	
	
	@GetMapping("/tickets/{ticketId}/addmet/{metId}")
	public String savemet(@PathVariable("ticketId") Integer ticketId,@PathVariable("metId") Integer metId, Model model) {
		
		
	MetRequest met=serviceMet.getMetRequestById(metId);
	TicketRequest ticket= serviceTicket.findITicketRequestById(ticketId);
	Client client= serviceClient.getClientById(new ClientId(ticket.getClient().getNom(),ticket.getClient().getPrenom(),ticket.getClient().getDateDeNaissance()));
	TableRequest table= serviceTable.getTableById(ticket.getTable().getId());
		         ticket.addMet(met);
		         ticket.setAddition(ticket.getAddition()+met.getPrix());
		         model.addAttribute("client", client);
		         model.addAttribute("ticket", ticket);
		         model.addAttribute("table", table);
		         model.addAttribute("mets", serviceMet.listMets());
		         serviceTicket.saveTicket(ticket);
	return "redirect:/tickets/{ticketId}/met/add";
	
	}
	
		@GetMapping("/Clients/{clientId}/ticket/add")
		public String newTicket(@PathVariable("clientId") ClientId clientId,
				Model model) {
			TicketRequest ticket = new TicketRequest();
			Client client= serviceClient.getClientById(clientId);
			ticket.setClient(client);
			
			
			model.addAttribute("ticket", ticket);
			model.addAttribute("tables", serviceTable.listTables());
			model.addAttribute("mets", serviceMet.listMets());

			return "Clients/addticket";
		}
		

		
	
		@PostMapping("/Clients/{clientId}/addticket")
		public String save(@PathVariable("clientId") ClientId clientId,@ModelAttribute("ticket")TicketRequest ticket,@ModelAttribute("table1")TableRequest table1, Model model) {
			
			
		
			Client client= serviceClient.getClientById(clientId);
			ticket.setClient(client);
			ticket.setDate(LocalDateTime.now());
			
		    Integer idtab = ticket.getTable().getId();
			TableRequest table =serviceTable.getTableById(idtab);
			Double Suppelement =table.getSupplement();
			ticket.setAddition(Suppelement);
			
	
			
			serviceTicket.saveTicket(ticket);
			
			return "redirect:/tickets";
		
		}
		
		
		@GetMapping("/tickets/{ticketId}/delete")
		public String deleteTable(@PathVariable("ticketId") Integer id) {
			
			serviceTicket.deleteById(id);
			return "redirect:/tickets";
		}
		
		
		@GetMapping({"/tickets/clientfidele"})
		public String clientfidele(Model model) {
			List<TicketRequest> tickets = serviceTicket.listTickets();
			
			List<ClientId> ids = serviceTicket.clientfidele(tickets);
			List<Client> clients = serviceClient.getClients();
			
			List<Client> clientfidele =new ArrayList<Client>();
			
			for(ClientId id : ids) { 
				
				for(Client client : clients) { 
					String Nomc = client.getNom();
					String Prenomc = client.getPrenom();
					LocalDate DateNaissance = client.getDateDeNaissance();
					ClientId clientf = new ClientId(Nomc,Prenomc,DateNaissance);
					
					ClientId idc= (ClientId) clientf; 
					if ( idc==id){
						
						clientfidele.add(client);
						
					}
				
					}
			
				}			
			
			model.addAttribute("clients", clientfidele);
			
			return "/tickets/clientfidele";
		}
		
		@GetMapping({"/tickets/meilleurtable"})
		public String meilleurtable(Model model) {
			List<TicketRequest> tickets = serviceTicket.listTickets();
			
			List<Integer> ids = serviceTicket.meilleurtable(tickets);
			List<TableResto> tables = serviceTable.getTables();
			
			List<TableResto> meillleurtable =new ArrayList<TableResto>();
			
			for(int id : ids) { 
				
				for(TableResto table : tables) { 
					Integer cid=table.getNumero();  
					int idc=(int)cid; 
					if ( idc==id){
						
						meillleurtable.add(table);
						
					}
				
					}
			
				}
			
			
			
			
			
			model.addAttribute("tables", meillleurtable);
			
			return "/tickets/meilleurtable";
		}
		
		@GetMapping({"/tickets/{clientId}/meilleurjours"})
		public String meilleurjour(@PathVariable("clientId") ClientId clientId,Model model) {
			List<TicketRequest> tickets = serviceTicket.listTickets();
			
			List<Integer> ids = serviceTicket.meilleurjour(tickets);
			
			 ids = ids.stream()
				     .distinct()
				     .collect(Collectors.toList());
			
			List<DayOfWeek> jours =new ArrayList<DayOfWeek>();
			
			Client client = serviceClient.getClientById(clientId);
			for(int id : ids) { 
				
				for(TicketRequest ticket : tickets) { 
					long tid=ticket.getId();  
					int idt=(int)tid; 
					if ( idt==id && client.getNom()==ticket.getClient().getNom() && client.getPrenom()==ticket.getClient().getPrenom() && client.getDateDeNaissance()==ticket.getClient().getDateDeNaissance()){
						
						jours.add(ticket.getDate().getDayOfWeek());
						
					}
				
					}
			
				}
			
			
			
			List<DayOfWeek> jour = jours.stream()
				     .distinct()
				     .collect(Collectors.toList());
			
			model.addAttribute("meillleurjours", jour);
			model.addAttribute("client", client);
			return "/tickets/meilleurjours";
		}
		
		@GetMapping("/tickets/revenue")
		public String RevenuParJour(Model model) {
			
			List<TicketRequest> tickets = serviceTicket.listTickets();
	        HashMap<DayOfWeek, Double> revparjour = new HashMap<DayOfWeek, Double>(); 
	        HashMap<Month, Double> revparmois = new HashMap<Month, Double>(); 
	        HashMap<Integer, Double> revparsemaine = new HashMap<Integer, Double>(); 
	        WeekFields weekFields  = WeekFields.of(DayOfWeek.MONDAY, 1); 
	        TemporalField weekOfMonth  = weekFields.weekOfMonth(); 
             Double revenueparjour=0.0;
             Double revenueparmois=0.0;
             Double revenueparsemaine=0.0;
             Double revenueTotale=0.0;
             
			for(TicketRequest ticket : tickets) { 
				
				
				for(TicketRequest ticketdate : tickets) { 
					
				if (ticketdate.getDate().getDayOfWeek()==ticket.getDate().getDayOfWeek()) {
					
					revenueparjour=revenueparjour+ticketdate.getAddition();
					
				}
				if (ticketdate.getDate().getMonth()==ticket.getDate().getMonth()) {
					
					revenueparmois=revenueparmois+ticketdate.getAddition();
				}
                if (ticketdate.getDate().get(weekOfMonth)==ticket.getDate().get(weekOfMonth)) {
					
                	revenueparsemaine=revenueparsemaine+ticketdate.getAddition();
				}

					}
			
				revparjour.put(ticket.getDate().getDayOfWeek(), revenueparjour);
				revparmois.put(ticket.getDate().getMonth(), revenueparmois);
				revparsemaine.put(ticket.getDate().get(weekOfMonth), revenueparsemaine);
				revenueparjour=0.0;
				revenueparmois=0.0;
				revenueparsemaine=0.0;
				revenueTotale=revenueTotale+ticket.getAddition();
				}
			
			model.addAttribute("revparjour", revparjour);
			model.addAttribute("revparmois", revparmois);
			model.addAttribute("revparsemaine", revparsemaine);
			model.addAttribute("revenueTotale", revenueTotale);
		
			return "/tickets/revenue";
			
			
		}
		
		
		
		
		@PostMapping("/tickets/plat/search")
		public String saveplat(Model model) {
			
			List<Ticket> tickets = serviceTicket.getTickets();
			List<Met> mets = new ArrayList<Met>();
		
			
			for(Ticket ticket : tickets) { 
					
					for(Met met : ticket.getMets()) { 
						
						if (met instanceof Plat) {
							mets.add(met);
						}
					
					}
				
			
				}
			List<Integer> ids = serviceTicket.meilleurplat(mets);
			 ids = ids.stream()
				     .distinct()
				     .collect(Collectors.toList());
			
				List<Met> metsplat ;
				List<Met> palt= new ArrayList<Met>() ;

				for(int id : ids) { 
					
					for(Ticket ticket : tickets) { 
						metsplat=ticket.getMets();
						
						for (Met met : metsplat) {
							long pid=met.getId();
							int idt=(int)pid; 
							if ( idt==id ){
										
								palt.add(met);
						}
						
							
						}
					
						}
				
					}
				palt = palt.stream()
					     .distinct()
					     .collect(Collectors.toList());
			
			
			
			
			model.addAttribute("mets", palt);
			return "tickets/searchplat";
		
		}

}
