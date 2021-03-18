package com.example.AssignmentMovieTicket.Entity;

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
@Table( indexes = @Index (name = "indexOnEmail", columnList = "email",unique = true))
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersId;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private Boolean isOwner ;
    @JsonManagedReference(value = "theatres-users")
    @OneToMany(mappedBy = "owner",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<theatres> theatresSet;
    @JsonManagedReference(value = "ticket-users")
    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<ticket> ticket;
}
