package com.example.movieticket.repository;

import com.example.movieticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Spring writes the query for us from the method name
    List<Ticket> findByMovieName(String movieName);

    boolean existsByMovieNameAndSeatNumber(String movieName, String seatNumber);
}
