package com.example.AssignmentMovieTicket.Repository;

import com.example.AssignmentMovieTicket.Entity.movieShows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface movieShowsRepository extends JpaRepository<movieShows, Long> {
    List<movieShows> findAllByTheatreAddressCity(String city);
    //List<movieShows> findAllByTheatreAddressCityAndMovieNameAndShowDateTimeAfter(String city, String movieName,Date now);
    List<movieShows> findAllByTheatreAddressCityAndMovieName(String city, String movieName);
    //  Write a cleanup function which deletes expired shows
    //  void deleteByShowDateTimeBefore(Date expiryDate);
}
