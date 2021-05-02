package com.de.tekup.services;

import java.util.List;

import com.de.tekup.dto.ClientRequest;
import com.de.tekup.entities.Client;
import com.de.tekup.entities.ClientId;



public interface Clientservice {
	
	void deleteById(ClientId id);
	ClientRequest saveClient(ClientRequest request);
	List<Client> getClients();
	Client getClientById(ClientId id);

}
