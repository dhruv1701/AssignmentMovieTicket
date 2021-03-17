package com.example.AssignmentMovieTicket.Repository;

import com.example.AssignmentMovieTicket.Entity.address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface addressRepository extends JpaRepository<address, Long> {

}
