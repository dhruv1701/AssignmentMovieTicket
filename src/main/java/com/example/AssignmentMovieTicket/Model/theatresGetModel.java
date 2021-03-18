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
public class theatresGetModel {
    private Long theatreId;
    private String name;
    private addressModel address;
    private Integer seatRows;
    private Integer seatCols;
}
