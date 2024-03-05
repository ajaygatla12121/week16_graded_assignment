package com.gl.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.springboot.model.Ticket;
import com.gl.springboot.repository.TicketRepository;

@Service
public class TicketServiceImpl {
	
	@Autowired
	TicketRepository ticketRepository;

	public List<Ticket> ticketsList() {
		List<Ticket> listOfTickets = (List<Ticket>) ticketRepository.findAll();
		
		return listOfTickets;
	}


	public Ticket addTicket(Ticket ticket) {
		
		return ticketRepository.save(ticket);
	}

	public Ticket updateTicketById(int slNo) {
		
		Optional<Ticket> optional = ticketRepository.findById(slNo);
		Ticket ticket = null;
		if(optional.isPresent()) {
			ticket = optional.get();
		}else {
			throw new RuntimeException("Ticket not found for id : " + slNo);
		}
		
		return ticket;
	}

	public void deleteTicketById(int slNo) {
		
		ticketRepository.deleteById(slNo);
	}

	public Ticket viewTicketById(int slNo) {
		Optional<Ticket> optional = ticketRepository.findById(slNo);
		Ticket ticket = optional.get();
		return ticket;
	}
	
	public List<Ticket> searchTicketsByTitle(String title) {
	    return ticketRepository.findByTicketTitleContainingIgnoreCase(title);
	}

	
}
