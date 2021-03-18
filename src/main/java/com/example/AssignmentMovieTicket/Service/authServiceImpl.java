package com.example.AssignmentMovieTicket.Service;

import com.example.AssignmentMovieTicket.Entity.users;
import com.example.AssignmentMovieTicket.Model.usersModel;
import com.example.AssignmentMovieTicket.Repository.usersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class authServiceImpl implements authService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private usersRepository usersRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Optional<String> addNewUser(usersModel userDetails) {
        try{
            Optional<users> findByEmailResponse= usersRepo.findByEmail(userDetails.getEmail());
            if(findByEmailResponse.isPresent()){
                return Optional.ofNullable("email already present");
            }
            else{
                users newUser = modelMapper.map(userDetails, users.class);
                newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
                usersRepo.save(newUser);
                return Optional.ofNullable("Success");
            }
        }
        catch (Exception e){
            System.out.println("[-] Error while signing up a new user "+ e.toString());
            return Optional.ofNullable("Failure");
        }
    }

//    @Override
//    public Optional<String> loginUser(usersModel userDetails) {
//        try{
//            Optional<users> findByEmailResponse = usersRepo.findByemail(userDetails.getEmail());
//            if(findByEmailResponse.isPresent()){
//                if(findByEmailResponse.get().getPassword().equals(userDetails.getPassword())){
//                    //addUserIntoSessionVariable(userDetails.getEmail(),userDetails.getIsOwner())
//                    return Optional.ofNullable("Successful");
//                }
//                else{
//                    return Optional.ofNullable("Password Mismatch");
//                }
//            }
//            else {
//                return Optional.ofNullable("Email Not Found");
//            }
//        }
//        catch (Exception e){
//            System.out.println("[-] Error while logging in "+e.toString());
//            return Optional.ofNullable("Failure");
//        }
//    }
}
