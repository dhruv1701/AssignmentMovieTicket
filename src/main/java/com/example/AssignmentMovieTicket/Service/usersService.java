package com.example.AssignmentMovieTicket.Service;

import com.example.AssignmentMovieTicket.Model.usersModel;

import java.util.Optional;

public interface usersService {
    Optional<String> addNewUser(usersModel userDetails);
    Optional<String> loginUser(usersModel userDetails);
}
