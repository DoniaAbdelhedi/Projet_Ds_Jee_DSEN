package com.de.tekup.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

import com.de.tekup.entities.Ticket;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class MetRequest {
	

	private Integer id;
	private String Nom;
	private Double Prix;
	
	@ManyToMany(mappedBy = "mets",cascade = CascadeType.REMOVE)
	List<Ticket> tickets= new ArrayList<>();
	
	
	public MetRequest() {
	
	}
	
	public MetRequest( String Nom, Double Prix, List<Ticket> tickets) {
		super();
		this.Nom = Nom;
		this.Prix = Prix;
		this.tickets = tickets;
	}
	public long getMetId(MetRequest met )	{
		
		return met.getId();
	}

}
