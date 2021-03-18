package com.example.AssignmentMovieTicket.Repository;

import com.example.AssignmentMovieTicket.Entity.theatres;
import com.example.AssignmentMovieTicket.Entity.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface theatresRepository extends JpaRepository<theatres, Long> {
    Optional<Set<theatres>> findAllByOwner(users users);
//    findAllByAddressCity(String city)
}
