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
@Table( indexes = @Index (name = "indexOnName", columnList = "name"))
public class theatres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theatreId;
    @NotNull
    private  String name;
    @NotNull
    @JsonBackReference(value = "address-theatre")
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private address address;
    @JsonManagedReference(value = "movieShows-theatre")
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "theatre",cascade = CascadeType.ALL)
    private Set<movieShows> showsSet;
    @NotNull
    private Integer seatRows;
    @NotNull
    private Integer seatCols;
    @JsonBackReference(value = "theatres-users")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private users owner;
}
