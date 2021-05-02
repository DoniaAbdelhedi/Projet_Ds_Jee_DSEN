package com.de.tekup.entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public abstract class Met {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String Nom;
	private Double Prix;
	
	
	@ManyToMany(mappedBy = "mets",cascade = CascadeType.REMOVE)
	private List<Ticket> tickets= new ArrayList<>();
	
	public void addticket(Ticket ticket )
	{
		
		tickets.add(ticket);
	}


	public long getMetId(Met met )
	{
		
		return met.getId();
	}

	
	

}
