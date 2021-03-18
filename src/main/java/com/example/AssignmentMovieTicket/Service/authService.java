package com.example.AssignmentMovieTicket.Service;

import com.example.AssignmentMovieTicket.Model.usersModel;

import java.util.Optional;

public interface authService {
    Optional<String> addNewUser(usersModel userDetails);
}
