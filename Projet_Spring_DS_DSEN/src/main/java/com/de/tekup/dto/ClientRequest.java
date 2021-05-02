package com.de.tekup.dto;

import java.time.LocalDate;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ClientRequest {
	
	private String Nom;
	private String Prenom;
	private String Courriel;
	@Size(min = 8, max = 8)
	private String Tel;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate DateNec;
	
	public LocalDate DateNec() {
		   return DateNec;
		}

	public void setDateNec(LocalDate date) {
		   this.DateNec = date;
		}

}
