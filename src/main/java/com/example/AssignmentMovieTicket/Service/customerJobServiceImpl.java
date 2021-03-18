package com.example.AssignmentMovieTicket.Service;

import com.example.AssignmentMovieTicket.Entity.*;
import com.example.AssignmentMovieTicket.Model.movieShowsGetModel;
import com.example.AssignmentMovieTicket.Model.ticketModel;
import com.example.AssignmentMovieTicket.Repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class customerJobServiceImpl implements customerJobService{
    @Autowired
    private movieShowsRepository movieShowsRepo;

    @Autowired
    private theatresRepository theatresRepo;

    @Autowired
    private addressRepository addressRepo;

    @Autowired
    private usersRepository usersRepo;

    @Autowired
    private ticketRepository ticketRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<Set<String>> getAllCitiesHavingOurTheatre(){
        Optional<Set<String>> allCitiesHavingOurTheatreResponse = addressRepo.findDistinctCity();
        return allCitiesHavingOurTheatreResponse;
    }

    @Override
    public Optional<Set<String>> getMoviesReleasedInACity(String city) {
        try {
            List<movieShows> allShowsInACity = movieShowsRepo.findAllByTheatreAddressCity(city);
            Set<String> movieSetInACity = new HashSet<>();
            for (movieShows ms : allShowsInACity) {
                movieSetInACity.add(ms.getMovieName());
            }
            return Optional.ofNullable(movieSetInACity);
        }
        catch (Exception e){
            System.out.println("[-] Error in getMoviesReleasedInACity");
            return Optional.empty();
        }
    }

    @Override
    public Optional<Set<movieShowsGetModel>> getAllShowsOfASpecificMovieInACity(String city,String movieName){
        try{
            List<movieShows> allShowsInACityOfAMovie = movieShowsRepo.findAllByTheatreAddressCityAndMovieName(city,movieName);
            Set<movieShowsGetModel> movieShowsInACityOfAMovieSet =  new HashSet<movieShowsGetModel>();
            for(movieShows mv: allShowsInACityOfAMovie){
                movieShowsInACityOfAMovieSet.add(modelMapper.map(mv,movieShowsGetModel.class));
            }
            return Optional.ofNullable(movieShowsInACityOfAMovieSet);
        }
        catch (Exception e){
            System.out.println("[-] Error in getAllShowsOfASpecificMovieInACity "+e.toString());
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Set<Integer>>> getAllSeatsAvailableAndOccupiedOfAShow(Long showId){
        try{
            Optional<movieShows> movieShowObj = movieShowsRepo.findById(showId);
            if(movieShowObj.isPresent()){
                Integer rowsInTheatre = movieShowObj.get().getTheatre().getSeatRows();
                Integer colsInTheatre = movieShowObj.get().getTheatre().getSeatCols();
                Set<Integer> occupiedSeatsOfThisShow = new HashSet<Integer>();
                for(seats st: movieShowObj.get().getSeatsSet()){
                    occupiedSeatsOfThisShow.add(st.getSeatNo());
                }
                Set<Integer> availableSeatsOfThisShow  = calculateAvailableSeats(rowsInTheatre,colsInTheatre,occupiedSeatsOfThisShow);
                List<Set<Integer>> seatsAvailableAndOccupiedOfAShow = new ArrayList<>();
                seatsAvailableAndOccupiedOfAShow.add(availableSeatsOfThisShow);
                seatsAvailableAndOccupiedOfAShow.add(occupiedSeatsOfThisShow);
                return Optional.ofNullable(seatsAvailableAndOccupiedOfAShow);
            }
            else{
                return Optional.empty();
            }
        }
        catch (Exception e){
            System.out.println("[-] Error in getAllSeatsAvailableAndOccupiedOfAShow "+e.toString());
            return Optional.empty();
        }
    }


    private Set<Integer> calculateAvailableSeats(Integer rows,Integer cols,Set<Integer> occupiedSeats){
        Integer totalSeats = rows*cols;
        Set<Integer> availableSeats = new HashSet<>();
        for(int i=1;i<=totalSeats;i++){
            if(!occupiedSeats.contains(i)){
                availableSeats.add(i);
            }
        }
        return availableSeats;
    }

    @Override
    public Optional<String> bookTickets(ticketModel ticketDetails,String email) {
        try{
            Optional<users> usersDetails = usersRepo.findByEmail(email);
            if(usersDetails.isPresent()){
                Optional<movieShows> show = movieShowsRepo.findById(ticketDetails.getShowId());
                if(show.isPresent()){
                   Integer maxSeatNoAppliedFor = Collections.max(ticketDetails.getSeatsNoSet());
                   Integer minSeatNoAppliedFor = Collections.min(ticketDetails.getSeatsNoSet());
                   if(maxSeatNoAppliedFor <= show.get().getTheatre().getSeatRows()*show.get().getTheatre().getSeatCols() && minSeatNoAppliedFor>=1){
                       ticket newTicket = new ticket();
                       Set<seats> seatsSet = new HashSet<seats>();
                       newTicket.setShows(show.get());
                       newTicket.setCustomer(usersDetails.get());
                       for(Integer seatNo: ticketDetails.getSeatsNoSet()){
                           seats newSeat = new seats();
                           newSeat.setSeatNo(seatNo);
                           newSeat.setShow(show.get());
                           newSeat.setTicket(newTicket);
                           seatsSet.add(newSeat);
                       }
                       newTicket.setSeatsNoSet(seatsSet);
                       ticketRepo.save(newTicket);
                       return Optional.ofNullable("Success");
                   }
                   else{
                       return Optional.ofNullable("Seat number not in available seats pool");
                   }
                }
                else{
                    return Optional.ofNullable("Show is not present");
                }
            }
            else{
                return Optional.ofNullable("User is not present");
            }
        }
        catch (Exception e){
            System.out.println("[-] Error in bookTickets "+e.toString());
            return Optional.ofNullable("Failure");
        }
    }
}
