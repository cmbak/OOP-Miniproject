package OOP.ec22697.MP;// File Candidate_ec22612.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

class Candidate_ec22612 extends Candidate {

    public static void main(String[] args) {
        
        Candidate[] available_Candidates = A3.getCandidateArray();
        String menu_option = "";
        
        while (!menu_option.equals("a"))
        {
            menu_option = "";
            menu_option = main_menu();
            if (menu_option.equals("b")){
                 run_same_elec(available_Candidates);
            }
        }
    }
    //From A3
    private static <T> void p(T s) {
        
        System.out.print(s);
    }
    
    private static <T> void pr(T s) { 
        
        System.out.println(s);
    }
    
    public static int randomInt(int bound) {
        Random r = new Random();
        return r.nextInt(bound);
    }
    
    private static String input (String message) //Takes message from the method call as a parameter
        
    {
        Scanner keyboard = new Scanner(System.in); // A Scanner object called keybaord is inititalised for inputs.
        
        pr (message); //pr method is used to print the message
        
        String input = keyboard.nextLine(); // stores the input from user in a string variable. The input is returned.
        
        
        return input;
    
    
    }
    
    
    private static String main_menu(){
        
        String menu_message = ("Would you like to  \na) exit  \nb) run same election many times");
        
        String option = input(menu_message);
        

        
        while(!(option.equals("a") || option.equals("b")))
        {
            option = input("Invalid Input Please Try again \n" + menu_message);
        }
        return option;
    }
    
    private static String run_same_elec_menu(){
        String menu_message = ("Would you like to \na) add a specific candidate \nb) add a candidate at random \nc) run the election?");
        
        String option = input(menu_message);
        

        
        while(!(option.equals("a") || option.equals("b") || option.equals("c")))
        {
            option = input("Invalid Input Please Try again \n" + menu_message);
        }
        return option;
    }
    
    private static void run_same_elec(Candidate[] available_Candidates){
        
        //Candidate[] selected_Candidates = new Candidate[available_Candidates.length];
        ArrayList<Candidate> selected_Candidates = new ArrayList<Candidate>();
        String menu_option = "";
        
        
        
        
        while(!menu_option.equals("c")){
        
            pr("= Running Repeated Elections = \nCandidates are");
              
            if (selected_Candidates.size() == 0){
                pr("None");
            }

            else{
                for(int i = 0; i<selected_Candidates.size(); i++){
                    
                pr(selected_Candidates.get(i).getName());
                    
                }
            }

            menu_option = run_same_elec_menu();

            if (menu_option.equals("a")){

                Candidate Specific_Candidate = Get_Specific_Candidate(available_Candidates);

                selected_Candidates.add(Specific_Candidate);
            }
            
            else if(menu_option.equals("b")){
                
                Candidate Random_Candidate = Get_Random_Candidate(available_Candidates);
                
                int count = 0;

                
                selected_Candidates.add(Random_Candidate);
            }
            
            else if(menu_option.equals("c")){
                
                Candidate vote_counter = Get_vote_counter(available_Candidates);
                
                run_election(selected_Candidates,vote_counter, available_Candidates);
                
            }
            
        }
                
                
        
    }
              
    private static Candidate Get_Specific_Candidate(Candidate[] available_Candidates){
        
        String specificName = input("Please enter a username.");
        Candidate specificCandidate = A3.getByUsername(specificName, available_Candidates);
    
        while(specificCandidate == null){
            specificName = input("Please enter a valid username.");
            specificCandidate = A3.getByUsername(specificName, available_Candidates);
        }
        
        return specificCandidate;
        
    }

    private static Candidate Get_Random_Candidate(Candidate[] available_Candidates){
        return available_Candidates[randomInt(available_Candidates.length)];
    }
    
    private static Candidate Get_vote_counter(Candidate[] available_Candidates){
        
        String vote_counter_name = input("Who should count the votes?");
        Candidate vote_counter = A3.getByUsername(vote_counter_name, available_Candidates);
    
        while(vote_counter == null){
            vote_counter_name = input("Invalid username, try again \nWho should count the votes?");
            vote_counter = A3.getByUsername(vote_counter_name, available_Candidates);
        }
        
        return vote_counter;
        
    }
    
    private static void run_election(ArrayList<Candidate> selected_Candidates, Candidate vote_counter, Candidate[] voters){
        int number_of_elections = Integer.parseInt(input("How many times shall we run the election?"));
        Candidate[] winners = new Candidate[number_of_elections];
        Candidate[] Votes;
        
        Candidate[] election_Candidates = selected_Candidates.toArray(new Candidate[0]);
        
        for (int i = 0; i<number_of_elections; i++){
            
            
            Votes = new Candidate[voters.length];
            
            for (int j = 0; j<voters.length+1; j++){
                try{
                    Votes[j] = voters[j].vote(election_Candidates);
                }
                catch(Exception a)
                {
                }
            }
            
            try{
                winners[i] = vote_counter.selectWinner(Votes);
            }
            
            catch(Exception a)
                {
            
            }
            
            
        }
        
        
        int largest_count = 0;
        Candidate most_common = election_Candidates[0];
        
        
        for (int j = 0; j<winners.length; j++){
            int count = 0;
            
            for( int i = 0; i<winners.length; i++){
                
                if (winners[j]==winners[i]){
                    count++;
                }
                
            }
            
            if (count>largest_count){
                largest_count = count;
                most_common = election_Candidates[j];
                    
            }
             
        }
        
        pr("Most common winner is "+most_common.getName());
        
        
    
    }
    
    public String getName() {
         return "Sakibul Islam";
    }

    public String getSlogan() {
        return "More funding for public services";
    }

    public Candidate vote(Candidate[] candidates) {
        // If array is empty, return instance of this class.
        if (candidates.length == 0) 
            return new Candidate_ec22612();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("More funding for public services"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Andrew"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
         // If array is empty, return instance of this class.
        if (votes.length == 0) 
            return new Candidate_ec22612();
        
        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];
        
        // Count the votes for each object in the array,
        // selecting one with the most.
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
        
        return currentWinner;
    }
    
}