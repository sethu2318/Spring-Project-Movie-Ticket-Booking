package com.example.movieticket.service;

import com.example.movieticket.exception.ResourceNotFoundException;
import com.example.movieticket.exception.SeatAlreadyBookedException;
import com.example.movieticket.model.Ticket;
import com.example.movieticket.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // Book a new ticket
    public Ticket bookTicket(Ticket ticket) {
        if (ticketRepository.existsByMovieNameAndSeatNumber(ticket.getMovieName(), ticket.getSeatNumber())) {
            throw new SeatAlreadyBookedException(
                    "Seat " + ticket.getSeatNumber() + " is already booked for " + ticket.getMovieName());
        }
        return ticketRepository.save(ticket);
    }

    // Get all tickets
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // Get one ticket by id
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id " + id));
    }

    // Get all tickets for one movie
    public List<Ticket> getTicketsByMovie(String movieName) {
        return ticketRepository.findByMovieName(movieName);
    }

    // Update a ticket
    public Ticket updateTicket(Long id, Ticket updated) {
        Ticket existing = getTicketById(id);
        existing.setMovieName(updated.getMovieName());
        existing.setCustomerName(updated.getCustomerName());
        existing.setSeatNumber(updated.getSeatNumber());
        existing.setTicketPrice(updated.getTicketPrice());
        return ticketRepository.save(existing);
    }

    // Cancel a ticket
    public void cancelTicket(Long id) {
        Ticket existing = getTicketById(id);
        ticketRepository.delete(existing);
    }
}
