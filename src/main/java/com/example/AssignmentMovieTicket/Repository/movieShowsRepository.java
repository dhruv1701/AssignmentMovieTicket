package com.example.AssignmentMovieTicket.Repository;

import com.example.AssignmentMovieTicket.Entity.movieShows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface movieShowsRepository extends JpaRepository<movieShows, Long> {
}
