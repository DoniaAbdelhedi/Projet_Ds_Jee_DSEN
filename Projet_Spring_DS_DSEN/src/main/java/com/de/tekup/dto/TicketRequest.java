package com.de.tekup.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.modelmapper.ModelMapper;

import com.de.tekup.entities.Client;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;


@Getter@Setter
public class TicketRequest {
	
	private static final ModelMapper mapper = new ModelMapper();

	private Integer id;
	@JsonFormat(pattern="yyyy-MM-dd ")
	private LocalDateTime  date;
	@Positive
	private Integer nbCouvert;
	@NotBlank
	private Double addition;
	

	private TableRequest table;
	
    private Client client;
	
	private List<MetRequest> mets=new ArrayList<MetRequest>();

	
	public TicketRequest( ) {
		
	}
	public TicketRequest( LocalDateTime date, Integer nbCouvert, Double addiction) {
		super();
		this.date = date;
		this.nbCouvert = nbCouvert;
		this.addition = addiction;
	}
	public List<MetRequest> getMets(){
		return mets.stream()
			.map(d -> mapper.map(d, MetRequest.class))
				.collect(Collectors.toList());
	}	
	
	public void addMet(MetRequest met )
	{
		
		mets.add(met);
	}


}
