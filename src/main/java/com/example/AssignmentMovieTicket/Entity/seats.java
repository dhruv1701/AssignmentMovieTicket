package com.example.AssignmentMovieTicket.Entity;

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
@Table( indexes = {@Index(name = "indexOnTicketId", columnList = "ticket_id"),@Index(name = "indexOnShowSeatingId", columnList = "show_seating_id")})
public class seats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ticket_id")
    private ticket ticket;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="show_seating_id")
    private showSeating showSeating;
    @NotNull
    private Integer seatNo;
}
