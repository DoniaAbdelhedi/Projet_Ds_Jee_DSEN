package com.de.tekup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.de.tekup.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {

}
