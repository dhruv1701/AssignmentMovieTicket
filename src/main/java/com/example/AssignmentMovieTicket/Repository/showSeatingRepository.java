package com.example.AssignmentMovieTicket.Repository;

import com.example.AssignmentMovieTicket.Entity.showSeating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface showSeatingRepository extends JpaRepository<showSeating, Long> {
}
