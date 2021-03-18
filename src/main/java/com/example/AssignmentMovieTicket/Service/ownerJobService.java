package com.example.AssignmentMovieTicket.Service;


import com.example.AssignmentMovieTicket.Model.movieShowsModel;
import com.example.AssignmentMovieTicket.Model.theatresGetModel;
import com.example.AssignmentMovieTicket.Model.theatresModel;

import java.util.Optional;
import java.util.Set;

public interface ownerJobService {
    Optional<String> addNewTheatre(theatresModel theatreDetails,String loggedInUserEmail);
    Optional<String> addNewMovieShows(movieShowsModel movieShowDetails,String loggedInUserEmail);
    Optional<Set<theatresGetModel>> getMyTheatres(String loggedInUserEmail);
}
