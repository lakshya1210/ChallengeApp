package com.example.ChallengeApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChallengeController {
    private final ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService){
        this.challengeService = challengeService;
    }

    @GetMapping("/challenges")
    public List<Challenge> getAllChallenges(){
        return challengeService.getAllChallenges();
    }

    @PostMapping("/challenges")
    public String addChallenge(@RequestBody Challenge challenge){
        boolean isChallengeAdded = challengeService.addChallenge(challenge);

        if(isChallengeAdded) {
            return "challenge added successfully";
        } else{
            return "Failed, Challenge not add";
        }
    }

    @GetMapping("/challenges/{month}")
    public Challenge getChallenges(@PathVariable String month){
        return challengeService.getChallenge(month).getBody();
    }

    @PutMapping("/challenges/{id}")
    public ResponseEntity<String> updateChallenge (@PathVariable Long id, @RequestBody Challenge updatedChallenge){
        boolean isUpdated = challengeService.updateChallenge(id, updatedChallenge);

        if(isUpdated){
            return new ResponseEntity<>("Challenge updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("challenges not updated", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/challenges/{id}")
    public ResponseEntity<String> updateChallenge (@PathVariable Long id){
        boolean isDeleted = challengeService.deleteChallenge(id);

        if(isDeleted){
            return new ResponseEntity<>("Challenge deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("challenges not deleted", HttpStatus.NOT_FOUND);
        }
    }

}
