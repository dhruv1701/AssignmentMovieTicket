package com.example.AssignmentMovieTicket.Repository;

import com.example.AssignmentMovieTicket.Entity.seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface seatsRepository extends JpaRepository<seats, Long> {
}
