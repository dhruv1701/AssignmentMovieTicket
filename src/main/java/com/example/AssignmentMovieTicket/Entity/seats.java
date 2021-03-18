package com.example.AssignmentMovieTicket.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table( indexes = {@Index(name = "indexOnTicketId", columnList = "ticket_id"),@Index(name = "indexOnShowId", columnList = "show_id")},uniqueConstraints=
        {@UniqueConstraint(columnNames={"show_id", "seatNo"}),@UniqueConstraint(columnNames = {"ticket_id","seatNo"})})
public class seats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;
    @JsonBackReference(value = "seats-ticket")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ticket_id")
    private ticket ticket;
    @JsonBackReference(value = "movieShows-seats")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="show_id")
    private movieShows show;
    @NotNull
    private Integer seatNo;
}
