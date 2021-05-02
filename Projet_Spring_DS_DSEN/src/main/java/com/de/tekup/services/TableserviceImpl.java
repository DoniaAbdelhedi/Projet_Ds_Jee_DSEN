package com.de.tekup.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.de.tekup.dto.TableRequest;
import com.de.tekup.entities.TableResto;
import com.de.tekup.repositories.TableRestoRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class TableserviceImpl implements Tableservice {

	private  TableRestoRepository reposTables;
	private ModelMapper mapper;
	
	@Override
	public  void deleteById(Integer id) {
	
		reposTables.deleteById(id);
 
	}
	
	
	@Override
	public TableRequest getTableById(Integer id) {
		TableResto tab =reposTables.findById(id)
		.orElseThrow(()-> new NoSuchElementException("no Ticket with this id"));
		
		TableRequest req = mapper.map(tab, TableRequest.class);
		return req;
	}
	
public List<TableRequest> listTables() {
		
		return reposTables.findAll().stream()
								.map(u -> mapper.map(u, TableRequest.class))
								.collect(Collectors.toList());
	}




	@Override
	public List<TableResto> getTables() {
		
		return reposTables.findAll();
	}


	@Override
	public TableRequest saveTable(TableRequest request) {
		// save 
		TableResto table = mapper.map(request, TableResto.class);
		
		reposTables.save(table);
		
		// update
		return null;
	}
	
}
