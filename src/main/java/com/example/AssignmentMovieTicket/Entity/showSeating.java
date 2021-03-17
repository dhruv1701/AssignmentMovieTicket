package com.example.AssignmentMovieTicket.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table( indexes = @Index(name = "indexOnCShowId", columnList = "show_id"))
public class showSeating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showSeatingId;
    @OneToMany(mappedBy = "showSeating",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<seats> bookedSeatsSet;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "show_id")
    private movieShows show;
}
