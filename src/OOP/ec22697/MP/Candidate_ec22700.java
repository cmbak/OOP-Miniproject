package OOP.ec22697.MP;// File Candidate_ec22700.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;


class Candidate_ec22700 extends Candidate {
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Candidate[] allCandidates = A3.getCandidateArray();
        int length = 1;
        Candidate[] candidates = new Candidate[length];

        System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        String choice = s.nextLine();
        
        if(choice.equals("a")){
            
            String name;
            
            System.out.println("Please enter a username.");
            name = s.nextLine();

            Candidate specific = A3.getByUsername(name, allCandidates);

            if(specific != null){
                
                System.out.println("user not found");
            }
            else{
                
                candidates[0] = specific;
                length = length + 1;
                Candidate[] temp = new Candidate[length];
                
                for(int i = 0; i<length-1; i++){
                    
                    temp[i+1] = candidates[i];
                }
                
                candidates = temp;
            }
        } else if(choice.equals("b")){

            int random = (new Random()).nextInt(allCandidates.length);
            candidates[0] = allCandidates[random];
            length = length + 1;
            Candidate[] temp = new Candidate[length];
            for(int i = 0; i<length-1; i++){

                temp[i+1] = candidates[i];
            }

            candidates = temp;
        }                    
        else if(choice.equals("c")){
            
            Candidate[] votes = new Candidate[allCandidates.length];

            for(int i = 0; i<allCandidates.length; i++){

                votes[i] = (allCandidates[i]).vote(candidates);
            }
        }
        
        return;
    
    }
    public String getName() {
        return "Winner";
    }

    public String getSlogan() {
        return "I win";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22700();

        if (candidates.length != 0) r = candidates[0];

            // Add code that inspects the array candidates
            // calling the various methods of the Candidate objects
            // and finds a Candidate object to vote for.
        if (candidates.length == 0) 
                return new Candidate_ec22700();

            // Search for candidate with same slogan as me.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("I win"))
                return c;

        return r;
    }

    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22700();

        if (votes.length != 0) r = votes[0];

            // Add code that either counts the votes
            // or uses some other way to return an element 
            // of the array vote as the winner of an election.
        if (votes.length == 0) 
            return new Candidate_ec22700();

        Candidate currentWinner = votes[0];

        int highestCount = 0;
        for (Candidate c : votes) {
            int count = 0;
            for (Candidate x : votes) 
                if (x == c)
                    count++;
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }

        return r;
    }

}
