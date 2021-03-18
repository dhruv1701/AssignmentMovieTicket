package com.example.AssignmentMovieTicket.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ticketModel {
    private Long showId;
    private Set<Integer> seatsNoSet;
}
