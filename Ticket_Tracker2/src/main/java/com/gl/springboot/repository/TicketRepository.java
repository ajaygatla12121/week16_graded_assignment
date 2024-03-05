package com.gl.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gl.springboot.model.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer>{

	List<Ticket> findByTicketTitleContainingIgnoreCase(String title);
}
