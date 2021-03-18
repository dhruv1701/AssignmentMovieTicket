package com.example.AssignmentMovieTicket.Controller;


import com.example.AssignmentMovieTicket.Model.movieShowsModel;
import com.example.AssignmentMovieTicket.Model.theatresGetModel;
import com.example.AssignmentMovieTicket.Model.theatresModel;
import com.example.AssignmentMovieTicket.Service.ownerJobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

@RestController
public class ownerJobController {

    @Autowired
    private ownerJobServiceImpl ownerJobService;

    @RequestMapping(value = "/addnewtheatre",method = RequestMethod.POST)
    private ResponseEntity<String> addNewTheatre(Principal principal, @RequestBody theatresModel theatreDetails ){
        //checkIfUserLoggedInIsOwner() send user details
        String loggedInUserEmail = principal != null ? principal.getName() : null;
        if(loggedInUserEmail!=null){
            Optional<String>  addNewTheatreResponse = ownerJobService.addNewTheatre(theatreDetails,loggedInUserEmail);
            if(addNewTheatreResponse.isPresent()){
                if(addNewTheatreResponse.get().equals("Success")){
                    return ResponseEntity.status(HttpStatus.CREATED).body(addNewTheatreResponse.get());
                }
                else if(addNewTheatreResponse.get().equals("Failure")){
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(addNewTheatreResponse.get());
                }
                else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(addNewTheatreResponse.get());
                }
            }
            else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in adding new Theatre");
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not authorised");
        }

    }
    @RequestMapping(value = "/addnewmovieshow", method = RequestMethod.POST)
    private ResponseEntity<String> addNewMovieShow(Principal principal,@RequestBody movieShowsModel movieShowDetails){
        //checkIfUserLoggedInIsOwner() send user details
        String loggedInUserEmail = principal != null ? principal.getName() : null;
        if(loggedInUserEmail!=null){
            Optional<String> addNewMovieShowResponse = ownerJobService.addNewMovieShows(movieShowDetails,loggedInUserEmail);
            if(addNewMovieShowResponse.isPresent()){
                if(addNewMovieShowResponse.get().equals("Success")){
                    return ResponseEntity.status(HttpStatus.CREATED).body(addNewMovieShowResponse.get());
                }
                else if(addNewMovieShowResponse.get().equals("Failure")){
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(addNewMovieShowResponse.get());
                }
                else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(addNewMovieShowResponse.get());
                }
            }
            else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in adding new Theatre");
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not authorised");
        }
    }
    @RequestMapping(value = "/getmytheatres",method = RequestMethod.GET)
    private ResponseEntity<Optional<Set<theatresGetModel>>> getMyTheatres(Principal principal){
        String loggedInUserEmail = principal != null ? principal.getName() : null;
        if(loggedInUserEmail!=null){
            Optional<Set<theatresGetModel> > setOfMyTheatres = ownerJobService.getMyTheatres(loggedInUserEmail);
            if(setOfMyTheatres.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(setOfMyTheatres);
            }
            else{
                return ResponseEntity.status(HttpStatus.OK).body(Optional.empty());
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Optional.empty());
        }
    }
}
