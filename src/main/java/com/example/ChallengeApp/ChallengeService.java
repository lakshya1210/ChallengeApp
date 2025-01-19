package com.example.ChallengeApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChallengeService {
    private final List<Challenge> challenges = new ArrayList<>();

    public ChallengeService(){
    }

    public List<Challenge> getAllChallenges(){
        return challenges;
    }
    private Long nextId = 1L;
    public boolean addChallenge(Challenge challenge){
        if (challenge!= null){
            challenge.setId(nextId++);
            challenges.add(challenge);
            return true;
        } else{
            return false;
        }
    }

    public Long getNextId() {
        return nextId;
    }

    public void setNextId(Long nextId) {
        this.nextId = nextId;
    }

    public ResponseEntity<Challenge> getChallenge(String month) {

        for(Challenge challenge : challenges){
            if(challenge.getMonth().equals(month)){
                return new ResponseEntity<>(challenge, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public boolean updateChallenge(Long id, Challenge updatedChallenge) {
        for(Challenge challenge : challenges){
            if(challenge.getId().equals(id)){
                challenge.setMonth(updatedChallenge.getMonth());
                challenge.setDescription(updatedChallenge.getDescription());
                return true;
            }
        }
        return false;
    }

    public boolean deleteChallenge(Long id) {
        return challenges.removeIf(challenge-> challenge.getId().equals(id));
    }
}
