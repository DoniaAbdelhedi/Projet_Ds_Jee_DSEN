package com.de.tekup.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor

public class ClientId implements Serializable {

	private String nom;
	private String prenom;
	private LocalDate dateDeNaissance;

	public ClientId(String nom, String prenom, LocalDate dateDeNaissance) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
	}
	
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        ClientId clientid = (ClientId) o;
	        return nom.equals(clientid.nom) &&
	               prenom.equals(clientid.prenom) && 
	               dateDeNaissance.equals(clientid.dateDeNaissance) ;
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(nom, prenom,dateDeNaissance);
	    }
}
