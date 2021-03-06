package com.example.AssignmentMovieTicket.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table( indexes = {@Index(name = "indexOnUsersId", columnList = "users_id"),@Index(name = "indexOnShowId", columnList = "show_id")})
public class ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    @NotNull
    @JsonBackReference(value = "movieShows-ticket")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="show_id")
    private movieShows shows;
    @NotNull
    @JsonBackReference(value = "ticket-users")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="users_id")
    private users customer;
    @NotNull
    @JsonManagedReference(value = "seats-ticket")
    @OneToMany(mappedBy = "ticket",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<seats> seatsNoSet;
}
