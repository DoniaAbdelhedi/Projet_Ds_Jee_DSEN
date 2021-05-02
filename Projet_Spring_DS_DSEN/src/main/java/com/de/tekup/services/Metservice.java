package com.de.tekup.services;

import java.util.List;

import com.de.tekup.dto.MetRequest;
import com.de.tekup.entities.Met;

public interface Metservice {

	void deleteById(long id);
	MetRequest saveEntree(MetRequest request);
	MetRequest savePlat(MetRequest request);
	MetRequest saveDessert(MetRequest request);
	List<Met> getMets();
	List<MetRequest> listMets();
	Met getMetById(long id);
	MetRequest getMetRequestById(long id);


}
