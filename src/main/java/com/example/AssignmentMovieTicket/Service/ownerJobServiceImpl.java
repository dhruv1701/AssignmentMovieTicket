package com.example.AssignmentMovieTicket.Service;

import com.example.AssignmentMovieTicket.Entity.movieShows;
import com.example.AssignmentMovieTicket.Entity.theatres;
import com.example.AssignmentMovieTicket.Entity.users;
import com.example.AssignmentMovieTicket.Model.movieShowsModel;
import com.example.AssignmentMovieTicket.Model.theatresGetModel;
import com.example.AssignmentMovieTicket.Model.theatresModel;
import com.example.AssignmentMovieTicket.Repository.movieShowsRepository;
import com.example.AssignmentMovieTicket.Repository.theatresRepository;
import com.example.AssignmentMovieTicket.Repository.usersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ownerJobServiceImpl implements ownerJobService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private theatresRepository theatresRepo;

    @Autowired
    private usersRepository usersRepo;

    @Autowired
    private movieShowsRepository movieShowsRepo;

    @Override
    @Transactional
    public Optional<Set<theatresGetModel>> getMyTheatres(String loggedInUserEmail) {
        try{
            Optional<users> loggedInUserDetail = usersRepo.findByEmail(loggedInUserEmail);
            if(loggedInUserDetail.isPresent()){
                if(loggedInUserDetail.get().getIsOwner()){
                    Optional<Set<theatres>> setOfMyTheatres = theatresRepo.findAllByOwner(loggedInUserDetail.get());
                    if(setOfMyTheatres.isPresent()){
                        Set<theatresGetModel> getMyTheatresSystemResponse= new HashSet<theatresGetModel>();
                        for(theatres th: setOfMyTheatres.get()){
                            getMyTheatresSystemResponse.add(modelMapper.map(th,theatresGetModel.class));
                        }
                        return Optional.ofNullable(getMyTheatresSystemResponse);
                    }
                    else{
                        return Optional.empty();
                    }
                }
                else{
                    return Optional.empty();
                }
            }
            else{
                return Optional.empty();
            }
        }
        catch (Exception e){
            System.out.println("[-] Error in getMyTheatres "+e.toString());
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<String> addNewTheatre(theatresModel theatreDetails,String loggedInUserEmail) {
        try{
            Optional<users> loggedInUserDetail =usersRepo.findByEmail(loggedInUserEmail);
            if(loggedInUserDetail.isPresent()){
                if(loggedInUserDetail.get().getIsOwner()){
                    theatres newTheatreDetails = modelMapper.map(theatreDetails,theatres.class);
                    newTheatreDetails.setOwner(loggedInUserDetail.get());
                    theatresRepo.save(newTheatreDetails);
                    return Optional.ofNullable("Success");
                }
                else{
                    return Optional.ofNullable("User is not owner");
                }
            }
            else{
                return Optional.ofNullable("User not registered");
            }
        }
        catch (Exception e){
            System.out.println("[-] Error in adding new theatre "+e.toString());
            return Optional.ofNullable("Failed to add new theatre");
        }
    }

    @Override
    @Transactional
    public Optional<String> addNewMovieShows(movieShowsModel movieShowDetails,String loggedInUserEmail) {
        try {
            Optional<users> loggedInUserDetail =usersRepo.findByEmail(loggedInUserEmail);
            if(loggedInUserDetail.isPresent()){
                if(loggedInUserDetail.get().getIsOwner()){
                    Optional<theatres> theatreInWhichShowIs = theatresRepo.findById(movieShowDetails.getTheatre_id());
                    if(theatreInWhichShowIs.isPresent()){
                        movieShows newMovieShowDeatails = modelMapper.map(movieShowDetails, movieShows.class);
                        newMovieShowDeatails.setTheatre(theatreInWhichShowIs.get());
                        movieShowsRepo.save(newMovieShowDeatails);
                        return Optional.ofNullable("Success");
                    }
                    else{
                        return Optional.ofNullable("theatre_id not vaild");
                    }
                }
                else{
                    return Optional.ofNullable("User is not owner");
                }
            }
            else{
                return Optional.ofNullable("User not registered");
            }
        }
        catch (Exception e){
            System.out.println("[-] Error in adding new movie show "+e.toString());
            return Optional.ofNullable("Failure");
        }
    }
}
