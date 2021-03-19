package com.example.AssignmentMovieTicket.Repository;

import com.example.AssignmentMovieTicket.Entity.ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ticketRepository extends JpaRepository<ticket, Long> {
//    findAllByOwner(users user)
//    findAllByShows(movieShows show)
//    void deleteByShowsShowDateTimeAfter(Date expiryDate);
}
