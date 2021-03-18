package com.example.AssignmentMovieTicket.Config.Auth;

import com.example.AssignmentMovieTicket.Entity.users;
import com.example.AssignmentMovieTicket.Repository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class customUserDetailsService implements UserDetailsService {

    @Autowired
    private usersRepository usersRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<users> user = usersRepo.findByEmail(email);
        if(user.isPresent()){
            return new customUserDetails(user.get());
        }
        else{
            throw new UsernameNotFoundException("[-] Email not Found");
        }
    }
}
