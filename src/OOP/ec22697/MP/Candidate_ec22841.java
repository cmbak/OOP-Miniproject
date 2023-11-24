package OOP.ec22697.MP;// File Candidate_ec22841.java
//
// Machine generated stub for Assignment 2

import java.util.*;
import java.util.Scanner;


class Candidate_ec22841 extends Candidate {
    
    public String getName() {
        return "Batman";
    }
    
    public String getSlogan() {
        return "I am vengance";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22841();
        if (candidates.length == 0){
            
            return new Candidate_ec22841();
        }
        
        return r;
    }
        
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22841();
        
        if (votes.length == 0)     
        {
            return new Candidate_ec22841(); 
        }

        Candidate winner = votes[0]; 

        int currentHighest = 0;

        for (int i=0; i<votes.length; i++)
        {
            int count = 0;

            for (int j=0; j<i; j++)
            {
                if (votes[i].getName() == votes[j].getName()) {count++;}
            }

            if (count > currentHighest)
            {
                currentHighest = count;
                winner = votes[i];
            }
        }

        return winner;
    }
    
    public static String checkInput(String message)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println(message);
        String inp = sc.nextLine();
        return inp;
    }
    public static int checkIntInput(String message)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println(message);
        int inp = sc.nextInt();
        return inp;
    }
        
    public static void main(String[] args){
        
       
            
            
        int number_of_candidates = checkIntInput("How many candidates?");

        String user_choice = checkInput("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        Candidate[] getCandidates = A3.getCandidateArray();
        Candidate[] candidateList = new Candidate[number_of_candidates];
        int count = 0;
        while(number_of_candidates > 0){

            if(user_choice == "a"){
                String user_input = checkInput("Please enter a username");
                candidateList[count] = A3.getByUsername(user_input, getCandidates);
                count = count + 1;
                number_of_candidates = number_of_candidates -1;
            }
            else if(user_choice == "b"){
                Random stream_of_random_numbers = new Random();
                int randomNumber = stream_of_random_numbers.nextInt(getCandidates.length);
                candidateList[count] = getCandidates[randomNumber];
                count = count + 1;
                number_of_candidates = number_of_candidates -1;


            }
            else if(user_choice == "c"){
                String vote_counter_string = checkInput("Who should count the votes?");
                int election_number = checkIntInput("How many times should the election run?");

                Candidate[] votes = new Candidate[number_of_candidates * election_number];
                Candidate vote_counter = A3.getByUsername(vote_counter_string, candidateList);
                for (int i = 0; i < election_number; i++){
                    for (int j = 0; j < number_of_candidates; j++){
                        votes[j+(i*number_of_candidates)] = candidateList[j].vote(candidateList);
                    }
                }

                Candidate winner = vote_counter.selectWinner(votes);
                System.out.println("The winner was " + winner.getName());
                return;
            }


        }

        
    }
        
    
    
}
