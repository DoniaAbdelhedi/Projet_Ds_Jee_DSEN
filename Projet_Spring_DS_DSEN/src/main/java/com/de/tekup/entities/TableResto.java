package com.de.tekup.entities;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "numero")
public class TableResto {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numero;
	
	private Integer nbCouvert;
	
	private String type;
	
	@Enumerated(value = EnumType.STRING)
	private Supplemant supplemant;
	
	
	@OneToMany(mappedBy = "tables",cascade = CascadeType.REMOVE)
	@JsonIgnore
	List<Ticket> tickets ;
	

}
