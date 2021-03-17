package com.example.AssignmentMovieTicket.Repository;

import com.example.AssignmentMovieTicket.Entity.theatres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface theatresRepository extends JpaRepository<theatres, Long> {
}
