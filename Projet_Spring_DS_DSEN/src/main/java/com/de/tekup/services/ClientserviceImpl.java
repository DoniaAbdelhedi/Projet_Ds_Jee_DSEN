package com.de.tekup.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.de.tekup.dto.ClientRequest;
import com.de.tekup.entities.Client;
import com.de.tekup.entities.ClientId;
import com.de.tekup.repositories.ClientRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientserviceImpl implements Clientservice {
	
	private  ClientRepository reposClient;
	private ModelMapper mapper;
	@Override
	public  void deleteById(ClientId id) {
	
		reposClient.deleteById(id);
 
	}
	
	
	@Override
	public Client getClientById(ClientId id) {
		
		return reposClient.findById(id)
						.orElseThrow(()-> new NoSuchElementException("Client with this id is not found"));
	}

	@Override
	public List<Client> getClients() {
		
		return reposClient.findAll();
	}


	@Override
	public ClientRequest saveClient(ClientRequest request) {
		// save 
		Client client = mapper.map(request, Client.class);
		
		reposClient.save(client);
		
		// update
		return null;
	}



}
