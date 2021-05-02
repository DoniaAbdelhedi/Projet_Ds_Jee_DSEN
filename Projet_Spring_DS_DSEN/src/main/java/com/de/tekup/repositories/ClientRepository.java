package com.de.tekup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.de.tekup.entities.Client;
import com.de.tekup.entities.ClientId;


public interface ClientRepository extends JpaRepository<Client,ClientId>{

}
