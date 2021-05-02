	package com.de.tekup.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@IdClass(ClientId.class)
public class Client {	
	
	@Id
	private String nom;
	@Id
	private String prenom;
	@Id
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateDeNaissance;
	
	private String courriel;
	
	private String telephone;
	
	@OneToMany(mappedBy="clients",cascade=  CascadeType.REMOVE)
	@JsonIgnore
	List<Ticket> tickets ;

	public Client addTicket(Ticket ticket){
		
        this.tickets.add(ticket);
        return this;
    }



}
