package com.example.AssignmentMovieTicket.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class usersModel {
    private String email;
    private String password;
    private Boolean isOwner = false;
}



