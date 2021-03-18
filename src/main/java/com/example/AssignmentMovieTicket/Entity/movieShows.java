package com.example.AssignmentMovieTicket.Entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table( indexes = {@Index(name = "indexOnTheatreId", columnList = "theatre_id"), @Index(name = "indexOnMovieName", columnList = "movieName")})
public class movieShows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showId;
    @NotNull
    @JsonBackReference(value = "movieShows-theatre")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_id")
    private theatres theatre;
    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date showDateTime;
    @NotNull
    private String movieName;
    @JsonManagedReference(value = "movieShows-seats")
    @OneToMany(mappedBy = "show",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<seats> seatsSet;
    @JsonManagedReference(value = "movieShows-ticket")
    @OneToMany(mappedBy = "shows",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<ticket> ticket;
}
