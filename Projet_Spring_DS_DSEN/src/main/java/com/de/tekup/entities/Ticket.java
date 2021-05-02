package com.de.tekup.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numero;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDateTime date;

	private Integer nbcouvert;

	private Double addition;

	@ManyToOne
	@JsonIgnore
	private TableResto tables;

	@ManyToOne
	@JsonIgnore
	private Client clients;

	@ManyToMany
	@JoinTable(name = "Compose")
	@JsonIgnore
	private List<Met> mets=new ArrayList<Met>();
	
	public Ticket() {
	}
	
	public Ticket( LocalDateTime date, Integer nbCouvert, Double addiction, TableResto table) {
		super();
		this.date = date;
		this.nbcouvert = nbCouvert;
		this.addition = addiction;
		this.tables = table;
	}
	
	public void addMet(Met met )
	{		
		mets.add(met);
	}
	
	public long getTableId(TableResto table )
	{		
		return table.getNumero();
	}

	

}
