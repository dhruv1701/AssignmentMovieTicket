package com.example.AssignmentMovieTicket.Repository;

import com.example.AssignmentMovieTicket.Entity.movieShows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface movieShowsRepository extends JpaRepository<movieShows, Long> {
    List<movieShows> findAllByTheatreAddressCity(String city);
    List<movieShows> findAllByTheatreAddressCityAndMovieName(String city, String movieName);
}
