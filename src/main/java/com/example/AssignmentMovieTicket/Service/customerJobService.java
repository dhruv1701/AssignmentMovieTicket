package com.example.AssignmentMovieTicket.Service;

import com.example.AssignmentMovieTicket.Model.movieShowsGetModel;
import com.example.AssignmentMovieTicket.Model.ticketModel;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface customerJobService {
    Optional<Set<String>> getAllCitiesHavingOurTheatre();
    Optional<Set<String>> getMoviesReleasedInACity(String city);
    Optional<Set<movieShowsGetModel>> getAllShowsOfASpecificMovieInACity(String city, String movieName);
    Optional<List<Set<Integer>>> getAllSeatsAvailableAndOccupiedOfAShow(Long showId);
    Optional<String> bookTickets(ticketModel ticketDetails,String email);
}
