package com.gl.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.springboot.model.Ticket;
import com.gl.springboot.service.TicketServiceImpl;

@Controller
@RequestMapping("/admin")
public class TicketController {
	
	@Autowired
	TicketServiceImpl ticketSvc;

	@GetMapping("/tickets")
	public String viewAllTickets(Model model) {
		model.addAttribute("ticketList", ticketSvc.ticketsList());
		
		return "index";
	}
	
	@GetMapping("/newTicket")
	public String addTicket(Model model) {
		Ticket ticket = new Ticket();
		model.addAttribute("ticket", ticket);
		
		return "addTicket";
	}
	
	@PostMapping("/saveTicket")
	public String saveTicket(@ModelAttribute("ticket") Ticket ticket) {
		ticketSvc.addTicket(ticket);
		
		return "redirect:/admin/tickets";
	}
	
	@GetMapping("/editTicket/{slNo}")
	public String editTicket(@PathVariable(value = "slNo")int slNo, Model model) {
		Ticket ticket = ticketSvc.updateTicketById(slNo);
		model.addAttribute("ticket", ticket);
		
		return "editTicket";
	}
	
	@GetMapping("/{slNo}/view")
	public String viewTicket(@ModelAttribute("slNo") int slNo, Model model) {
		Ticket ticket = ticketSvc.viewTicketById(slNo);
		model.addAttribute("ticket", ticket);
		
		return "viewTicket";
	}
	
	@GetMapping("/{slNo}/delete")
	public String deleteTicket(@PathVariable(value="slNo") int slNo) {
		ticketSvc.deleteTicketById(slNo);
		return "redirect:/admin/tickets";
	}
	
	@GetMapping("/search")
	public String searchTickets(@RequestParam(name = "title") String title, Model model) {
	    List<Ticket> searchResults = ticketSvc.searchTicketsByTitle(title);
	    model.addAttribute("searchResults", searchResults);
	    return "search";
	}
}
