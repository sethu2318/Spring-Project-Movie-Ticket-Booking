package com.example.movieticket.controller;

import com.example.movieticket.model.Ticket;
import com.example.movieticket.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // POST /api/tickets  -> book a ticket
    @PostMapping
    public ResponseEntity<Ticket> bookTicket(@Valid @RequestBody Ticket ticket) {
        Ticket saved = ticketService.bookTicket(ticket);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // GET /api/tickets  -> all tickets
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    // GET /api/tickets/1  -> one ticket
    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }

    // GET /api/tickets/movie/Leo  -> tickets of a movie
    @GetMapping("/movie/{movieName}")
    public List<Ticket> getByMovie(@PathVariable String movieName) {
        return ticketService.getTicketsByMovie(movieName);
    }

    // PUT /api/tickets/1  -> update
    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable Long id, @Valid @RequestBody Ticket ticket) {
        return ticketService.updateTicket(id, ticket);
    }

    // DELETE /api/tickets/1  -> cancel
    @DeleteMapping("/{id}")
    public Map<String, String> cancelTicket(@PathVariable Long id) {
        ticketService.cancelTicket(id);
        return Map.of("message", "Ticket cancelled successfully");
    }
}
