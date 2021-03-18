package com.example.AssignmentMovieTicket.Controller;

import com.example.AssignmentMovieTicket.Model.usersModel;


import com.example.AssignmentMovieTicket.Service.authServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class authController {

    @Autowired
    private authServiceImpl authService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    private ResponseEntity<String> signup(@RequestBody usersModel userDetails){
        Optional<String> addNewUserResponse = authService.addNewUser(userDetails);
        if(addNewUserResponse.isPresent()){
            if(addNewUserResponse.get().equals("Success")){
                return ResponseEntity.status(HttpStatus.CREATED).body(addNewUserResponse.get());
            }
            else if(addNewUserResponse.get().equals("Failure")){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(addNewUserResponse.get());
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(addNewUserResponse.get());
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in signing up");
        }
    }

//    @RequestMapping(value ="/login",method = RequestMethod.POST)
//    private ResponseEntity<String> login(@RequestBody usersModel userDetails){
//        Optional<String> loginUserResponse= authService.loginUser(userDetails);
//        if(loginUserResponse.isPresent()){
//            if(loginUserResponse.get() == "Successful"){
//                return ResponseEntity.status(HttpStatus.OK).body(loginUserResponse.get());
//            }
//            else if(loginUserResponse.get() == "Failure"){
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(loginUserResponse.get());
//            }
//            else{
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginUserResponse.get());
//            }
//        }
//        else{
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in logging in");
//        }
//    }
}
