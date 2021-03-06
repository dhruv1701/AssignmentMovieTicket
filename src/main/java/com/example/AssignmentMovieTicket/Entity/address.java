package com.example.AssignmentMovieTicket.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table( indexes = @Index(name = "indexOnCity", columnList = "city"))
public class address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @NotNull
    private String city;
    @NotNull
    private String description;
    @NotNull
    private Integer pinCode;
    @JsonManagedReference(value = "address-theatre")
    @OneToOne(mappedBy = "address")
    private theatres theatre;
}
