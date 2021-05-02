package com.de.tekup.services;
import java.util.List;

import com.de.tekup.dto.TicketRequest;
import com.de.tekup.entities.ClientId;
import com.de.tekup.entities.Met;
import com.de.tekup.entities.Ticket;


public interface Ticketservice  {

	void deleteById(Integer id);
	List<Ticket> getTickets();
	List<TicketRequest> listTickets();
	TicketRequest saveTicket(TicketRequest request);
	TicketRequest findITicketRequestById(Integer ticketId);

	List<ClientId> clientfidele(List<TicketRequest> tickets);
	List<Integer> meilleurtable(List<TicketRequest> tickets);
	List<Integer> meilleurjour(List<TicketRequest> tickets);
	List<Integer> meilleurplat(List<Met> mets);
	
	
	
}
