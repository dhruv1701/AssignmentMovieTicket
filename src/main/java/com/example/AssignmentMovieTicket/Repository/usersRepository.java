package com.example.AssignmentMovieTicket.Repository;

import com.example.AssignmentMovieTicket.Entity.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface usersRepository extends JpaRepository<users, Long> {
    Optional<users> findByEmail(String email);
}
