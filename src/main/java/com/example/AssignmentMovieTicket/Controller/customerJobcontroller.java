package com.example.AssignmentMovieTicket.Controller;

import com.example.AssignmentMovieTicket.Model.movieShowsGetModel;
import com.example.AssignmentMovieTicket.Model.ticketModel;
import com.example.AssignmentMovieTicket.Service.customerJobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class customerJobcontroller {

    @Autowired
    private customerJobServiceImpl customerJobService;

    @RequestMapping(value = "/moviesreleasedin/{city}",method = RequestMethod.GET)
    private ResponseEntity<Optional<Set<String>>> getMovieReleasedInACity(@PathVariable String city){
        Optional<Set<String>> getMovieReleasedInACityResponse = customerJobService.getMoviesReleasedInACity(city);
        return ResponseEntity.status(HttpStatus.OK).body(getMovieReleasedInACityResponse);
    }

    @RequestMapping(value = "/showsofamovieincity/{movie}/{city}",method = RequestMethod.GET)
    private ResponseEntity<Optional<Set<movieShowsGetModel>>> getAllShowsOfASpecificMovieInACity(@PathVariable("movie") String movie,@PathVariable("city") String city){
        Optional<Set<movieShowsGetModel>> getAllShowsOfASpecificMovieInACityResponse = customerJobService.getAllShowsOfASpecificMovieInACity(city,movie);
        return ResponseEntity.status(HttpStatus.OK).body(getAllShowsOfASpecificMovieInACityResponse);
    }

    @RequestMapping(value = "/seatsofashow/{show_id}",method = RequestMethod.GET)
    private ResponseEntity<Optional<List<Set<Integer>>>> getAllSeatsAvailableAndOccupiedOfAShow(@PathVariable Long show_id){
        Optional<List<Set<Integer>>> getAllSeatsAvailableAndOccupiedOfAShowResponse = customerJobService.getAllSeatsAvailableAndOccupiedOfAShow(show_id);
        return ResponseEntity.status(HttpStatus.OK).body(getAllSeatsAvailableAndOccupiedOfAShowResponse);
    }

    @RequestMapping(value = "/citieshavingtheatre",method = RequestMethod.GET)
    private ResponseEntity<Optional<Set<String>>> getAllCitiesHavingOurTheatre(){
        Optional<Set<String>> getAllCitiesHavingOurTheatreResponse = customerJobService.getAllCitiesHavingOurTheatre();
        return ResponseEntity.status(HttpStatus.OK).body(getAllCitiesHavingOurTheatreResponse);
    }

    @RequestMapping(value = "/bookticket",method = RequestMethod.POST)
    @Transactional
    private ResponseEntity<Optional<String>> bookTickets(Principal principal, @RequestBody ticketModel ticketDetails){
        String loggedInUserEmail = principal != null ? principal.getName() : null;
        if(loggedInUserEmail!=null){
            Optional<String> bookTicketResponse = customerJobService.bookTickets(ticketDetails,loggedInUserEmail);
            if(bookTicketResponse.isPresent()){
                if(bookTicketResponse.get().equals("Success")){
                    return ResponseEntity.status(HttpStatus.CREATED).body(bookTicketResponse);
                }
                else if(bookTicketResponse.get().equals("Failure")){
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bookTicketResponse);
                }
                else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bookTicketResponse);
                }
            }
            else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Optional.ofNullable("Error in booking ticket"));
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Optional.ofNullable("User not authorised"));
        }
    }
}
