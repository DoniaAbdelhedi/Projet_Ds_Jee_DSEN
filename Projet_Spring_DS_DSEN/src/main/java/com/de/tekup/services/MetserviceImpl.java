package com.de.tekup.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.de.tekup.dto.MetRequest;
import com.de.tekup.entities.Dessert;
import com.de.tekup.entities.Entree;
import com.de.tekup.entities.Met;
import com.de.tekup.entities.Plat;
import com.de.tekup.repositories.MetRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class MetserviceImpl implements Metservice{
	

	private  MetRepository reposMet;
	private ModelMapper mapper;
	

	
	@Override
	public  void deleteById(long id) {
	
		reposMet.deleteById(id);
 
	}
	
	
	@Override
	public List<Met> getMets() {
		
		return reposMet.findAll();
	}
	
    public List<MetRequest> listMets() {
		
		return reposMet.findAll().stream()
								.map(u -> mapper.map(u, MetRequest.class))
								.collect(Collectors.toList());
	}
    @Override
	public Met getMetById(long id) {
		
		return reposMet.findById(id)
						.orElseThrow(()-> new NoSuchElementException("MeT with this id is not found"));
	}
    @Override
	public MetRequest getMetRequestById(long id) {
		Met met =reposMet.findById(id)
		.orElseThrow(()-> new NoSuchElementException("no Ticket with this id"));
		
		MetRequest req = mapper.map(met, MetRequest.class);
		return req;
	}

	@Override
	public MetRequest saveEntree(MetRequest request) {
		// save 
		Met met = mapper.map(request, Met.class);
		
		 Entree entree = new Entree();
	        entree.setNom(met.getNom());
	        entree.setPrix(met.getPrix());
	        reposMet.save(entree);
	
		
		// update
		return null;
	}
	public MetRequest savePlat(MetRequest request) {
		// save 
		Met met = mapper.map(request, Met.class);
		
		 Plat plat = new Plat();
		 plat.setNom(met.getNom());
		 plat.setPrix(met.getPrix());
	        reposMet.save(plat);
	
		
		// update
		return null;
	}
	
	public MetRequest saveDessert(MetRequest request) {
		// save 
		Met met = mapper.map(request, Met.class);
		
		 Dessert dessert = new Dessert();
		 dessert.setNom(met.getNom());
		 dessert.setPrix(met.getPrix());
	        reposMet.save(dessert);
	
		
		// update
		return null;
	}
	public Met saveDessert(Met request) {
		// save 
		Met met = mapper.map(request, Met.class);
		
		 Dessert dessert = new Dessert();
		 dessert.setNom(met.getNom());
		 dessert.setPrix(met.getPrix());
	        reposMet.save(dessert);
	
		
		// update
		return null;
	}


}
