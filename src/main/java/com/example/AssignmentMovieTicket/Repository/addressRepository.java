package com.example.AssignmentMovieTicket.Repository;

import com.example.AssignmentMovieTicket.Entity.address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface addressRepository extends JpaRepository<address, Long> {
    @Query("SELECT DISTINCT a.city FROM address a")
    Optional<Set<String>> findDistinctCity();
}
