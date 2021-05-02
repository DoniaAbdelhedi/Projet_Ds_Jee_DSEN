package com.de.tekup.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class TableRequest {
	

	private Integer id;
	private Integer NbCouvert;
	private Double Supplement;
	@NotBlank
	@Size(min = 2)
	private String Type;
	


public long getTableId(TableRequest table )
{
	
	return table.getId();
}

}
