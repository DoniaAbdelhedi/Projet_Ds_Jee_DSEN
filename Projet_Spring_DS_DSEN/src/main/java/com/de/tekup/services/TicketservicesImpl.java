package com.de.tekup.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.de.tekup.dto.TicketRequest;
import com.de.tekup.entities.Client;
import com.de.tekup.entities.ClientId;
import com.de.tekup.entities.Met;
import com.de.tekup.entities.TableResto;
import com.de.tekup.entities.Ticket;
import com.de.tekup.repositories.ClientRepository;
import com.de.tekup.repositories.MetRepository;
import com.de.tekup.repositories.TableRestoRepository;
import com.de.tekup.repositories.TicketRepository;


@Service
public class TicketservicesImpl implements Ticketservice {

	private TicketRepository reposTicket;
	private MetRepository reposMet;
	private ClientRepository reposClient;
	private TableRestoRepository reposTable;
	private ModelMapper map;
	
	@Override
	public void deleteById(Integer id) {
		
		reposTicket.deleteById(id);;
		
	}
	@Override
	public List<Ticket> getTickets() {
		return reposTicket.findAll();
	}
	@Override
	public List<TicketRequest> listTickets() {
		return reposTicket.findAll().stream()
				.map(u -> map.map(u, TicketRequest.class))
				.collect(Collectors.toList());
	}
	@Override
	public TicketRequest saveTicket(TicketRequest request) {
		Ticket ticket = map.map(request, Ticket.class);	
		reposTicket.save(ticket);
		return null;
	}
	@Override
	public TicketRequest findITicketRequestById(Integer id) {
		Ticket tic =reposTicket.findById(id)
					.orElseThrow(()-> new NoSuchElementException("no Ticket with this id"));
				
		TicketRequest req = map.map(tic, TicketRequest.class);
		return req;
	}
	
	
	@Override
	public List<ClientId> clientfidele(List<TicketRequest> tickets) {
		int nbrtic = 0;
		int nbr;
		List<ClientId> ids =new ArrayList<ClientId>();
		List<Client> clients=reposClient.findAll();
		
		for(Client client : clients) { 
			nbr=0;			
		
		for(TicketRequest ticket : tickets) { 
			
			if (client.getNom()==ticket.getClient().getNom() && client.getPrenom()==ticket.getClient().getPrenom() && client.getDateDeNaissance()==ticket.getClient().getDateDeNaissance() ) {
				
				nbr=nbr+1;			
			}
		
			}

		if (nbr>0 && nbr>nbrtic) {
			
				nbrtic=nbr;				
				ids.clear();
				String Nomc = client.getNom();
				String Prenomc = client.getPrenom();
				LocalDate DateNaissance = client.getDateDeNaissance();
				ClientId clientf = new ClientId(Nomc,Prenomc,DateNaissance);
				ids.add(clientf);
		}
		
		else if (nbr>0 && nbr==nbrtic) {
			
			nbrtic=nbr;
			
			String Nomc = client.getNom();
			String Prenomc = client.getPrenom();
			LocalDate DateNaissance = client.getDateDeNaissance();
			ClientId clientf = new ClientId(Nomc,Prenomc,DateNaissance);
			ids.add(clientf);
			
	}
		
		
		}
	
		return ids;
	}
	@Override
	public List<Integer> meilleurtable(List<TicketRequest> tickets) {
		int nbrtic = 0;
		int nbr;
	

		List<Integer> ids =new ArrayList<Integer>();
		List<TableResto> tables=reposTable.findAll();
		
		for(TableResto table : tables) { 
			nbr=0;
			
			ids.add(0);
		for(TicketRequest ticket : tickets) { 
			
			if (table.getNumero() == ticket.getTable().getId()) {
				
				nbr=nbr+1;
			
			}
		
			}

		if (nbr>0 && nbr>nbrtic) {
			
				nbrtic=nbr;
				
				ids.clear();
				long tid = table.getNumero();  
				int id=(int)tid;  
				ids.add(id);
		}
		
		else if (nbr>0 && nbr==nbrtic) {
			
			nbrtic=nbr;
			
			long tid=table.getNumero();  
			int id=(int)tid;  
			ids.add(id);
	}
		
		
		}
	
		return ids;
 
	}
	@Override
	public List<Integer> meilleurjour(List<TicketRequest> tickets) {
		int nbrjours = 0;
		int nbr;
		DayOfWeek day;
		List<Integer> ids =new ArrayList<Integer>();
		
		
		for(TicketRequest ticket : tickets) { 
			nbr=0;
		
			day=ticket.getDate().getDayOfWeek();
	  
			
		for(TicketRequest ticketdate : tickets) { 
			
			if (ticketdate.getDate().getDayOfWeek()==day) {
				
				nbr=nbr+1;
			
			}
		
			}
		
		if (nbr>0 && nbrjours==0 ) {
			
			nbrjours=nbr;
			
		
			long tid=ticket.getId();  
			int id=(int)tid;  
			ids.add(id);
	}
	
	else if (nbr>0 && nbr>nbrjours) {
		
		nbrjours=nbr;
		ids.clear();
		long tid=ticket.getId();  
		int id=(int)tid;  
		ids.add(id);
   }
else if (nbr==nbrjours) {
		
		nbrjours=nbr;
		long tid=ticket.getId();  
		int id=(int)tid;  
		ids.add(id);
   }
		}
	
		return ids;
 
	}
	@Override
	public List<Integer> meilleurplat(List<Met> mets) {
		int nbrplats = 0;
		int nbr;
		
		List<Integer> ids =new ArrayList<Integer>();

		
		for(Met met : mets) { 
			nbr=0;

		for(Met metplat : mets) { 
			
			if (metplat.getId()==met.getId()) {
				
				nbr=nbr+1;
			
			}
		
			}
		
		if (nbr>0 && nbrplats==0 ) {
			
			nbrplats=nbr;
			
		
			long pid=met.getId();  
			int id=(int)pid;  
			ids.add(id);
	}
	
	else if (nbr>0 && nbr>nbrplats) {
		
		nbrplats=nbr;
		ids.clear();
		long pid=met.getId();  
		int id=(int)pid;  
		ids.add(id);
   }
else if (nbr==nbrplats) {
		
	nbrplats=nbr;
		long pid=met.getId();  
		int id=(int)pid;  
		ids.add(id);
   }	
			}
	
		return ids;
 
	} 

	

}
