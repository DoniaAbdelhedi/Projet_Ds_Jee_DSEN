package com.de.tekup.services;

import java.util.List;

import com.de.tekup.dto.TableRequest;
import com.de.tekup.entities.TableResto;

public interface Tableservice {

	 void deleteById(Integer id);
	 TableRequest saveTable(TableRequest request);
	List<TableResto> getTables();
	TableRequest getTableById(Integer id);
	List<TableRequest> listTables();
}
