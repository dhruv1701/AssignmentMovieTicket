package com.example.AssignmentMovieTicket.Model;

import com.example.AssignmentMovieTicket.Entity.address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class theatresModel {
    private String name;
    private address address;
    private Integer seatRows;
    private Integer seatCols;
}
