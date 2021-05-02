package com.de.tekup.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id",callSuper = false)
public class Plat extends Met{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String Nom;
	private Double Prix;

	public Plat( String Nom, Double Prix,String Type ) {
		super();
		super.setNom(Nom);
		super.setPrix(Prix);
	
	}

}
