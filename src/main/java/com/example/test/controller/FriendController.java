package com.example.test.controller;

import com.example.test.model.Friend;
import com.example.test.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class FriendController {

    @Autowired
    FriendService friendService;

    @PostMapping("/friend")
    public Friend create(@Valid @RequestBody Friend friend){
        return friendService.save(friend);
    }

    @GetMapping("/friend")
    public Iterable<Friend> read(){
        return friendService.findAll();
    }

    @PutMapping("/friend")
    public ResponseEntity<Friend> update(Friend friend){
        if(friendService.findById(friend.getId()).isPresent())
            return new ResponseEntity(friendService.save(friend), HttpStatus.OK);
        else
            return new ResponseEntity(friend, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/friend/{id}")
    public void delete(@PathVariable Integer id){
        friendService.deleteById(id);
    }

    @GetMapping("/friend/{id}")
    Optional<Friend> findById(@PathVariable Integer id){
        return friendService.findById(id);
    }

    @GetMapping("/friend/search")
    Iterable<Friend> findByQuery(@RequestParam(value = "first", required = false) String firstName, @RequestParam(value = "last", required = false) String lastName){
        if(firstName != null && lastName != null)
            return friendService.findByFirstNameAndLastName(firstName, lastName);
        else if(firstName != null)
            return friendService.findByFirstName(firstName);
        else if(lastName != null)
            return friendService.findByLastName(lastName);
        else
            return friendService.findAll();

    }
}