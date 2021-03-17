package com.example.AssignmentMovieTicket.Controller;

import com.example.AssignmentMovieTicket.Model.usersModel;


import com.example.AssignmentMovieTicket.Service.usersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class usersController {

    @Autowired
    private usersServiceImpl usersService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    private ResponseEntity<String> signup(@RequestBody usersModel userDetails){
        Optional<String> addNewUserResponse = usersService.addNewUser(userDetails);
        if(addNewUserResponse.isPresent()){
            if(addNewUserResponse.get() == "Success"){
                return ResponseEntity.status(HttpStatus.OK).body(addNewUserResponse.get());
            }
            else if(addNewUserResponse.get() == "Failure"){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(addNewUserResponse.get());
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(addNewUserResponse.get());
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("hello");
        }
    }

    @RequestMapping(value ="/login",method = RequestMethod.POST)
    private ResponseEntity<String> login(@RequestBody usersModel userDetails){
        Optional<String> loginUserResponse= usersService.loginUser(userDetails);
        if(loginUserResponse.isPresent()){
            if(loginUserResponse.get() == "Successful"){
                return ResponseEntity.status(HttpStatus.OK).body(loginUserResponse.get());
            }
            else if(loginUserResponse.get() == "Failure"){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(loginUserResponse.get());
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginUserResponse.get());
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(loginUserResponse.get());
        }
    }
}
