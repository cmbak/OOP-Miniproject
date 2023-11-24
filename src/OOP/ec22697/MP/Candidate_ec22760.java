package OOP.ec22697.MP;// File Candidate_ec22760.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22760 extends Candidate {
    
    public static void main(String[] argz){
        Scanner scanner = new Scanner(System.in);
        
        String option;
        int position = 0;
        boolean exit = false;
        Candidate[] candidates = A3.getCandidateArray();
        Candidate[] election_candidates = new Candidate[candidates.length];
        
        while(!exit){
        System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit?");
        option = scanner.nextLine();
        
        if(option.equals("a")){
            
            position = newCandidate(candidates, election_candidates, position);
        
            System.out.println("Candidates are:");
            
            for(int i = 0; i<position; i++){
                try{
                    System.out.println(i+1 + ". " + election_candidates[i].getName());
                }
                catch(Exception e){
                    System.out.println("Error. Inavlid user");
                }
            }
        }
        else if(option.equals("b")){
            Random random = new Random();
            int ran_num = random.nextInt(candidates.length);
            
            election_candidates[position] = candidates[ran_num];
            
            position++;
        }
        else if(option.equals("c")){
            
            System.out.println("Who should count the votes?");
            String counting_user = scanner.nextLine();
            Candidate counting_candidate = A3.getByUsername(counting_user, candidates);
            
            int num_of_times = enter_int("How many times should we run the election");
            
            election(candidates,election_candidates,num_of_times,counting_candidate);
        }
        else if(option.equals("d")){
        exit = true;
        }
        }
    }
    
    public static int newCandidate(Candidate[]candidates, Candidate[]election_candidates, int position){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter a username");
        String specific_username = scanner.nextLine();
        
        Candidate specific_candidate = A3.getByUsername(specific_username, candidates);
        
        if(specific_candidate != null){
            election_candidates[position] = specific_candidate;
            position++;
        }
        else{
            System.out.println("User not found");
        }
        
        return position;
    }   
    
    public static void election(Candidate[] candidates, Candidate[] election_candidates, int num_of_times, Candidate counting_candidate){
        
        Candidate[] votes = new Candidate[election_candidates.length];
        Candidate[] winners = new Candidate[num_of_times];
        
        for(int i = 0; i<election_candidates.length; i++){
            try{
                votes[i] = election_candidates[i].vote(candidates);
            }
            catch(Exception e){
                votes[i] = new Candidate_ec22760();
            }
        }
        
        for(int i = 0; i<num_of_times; i++){
            try{
                winners[i] = counting_candidate.selectWinner(votes);
            }
            catch(Exception e){
                winners[i] = (new Candidate_ec22760()).selectWinner(votes);
            }
        }
        
        int highest_count = 0;
        int pos = 0; 
        for(int i = 0; i<num_of_times; i++){
            int count = 0;
            for(int z = 0; z< winners.length; z++){
                if(winners[i] == winners[z]){
                    count++;
                }
            }
            if(count>highest_count){
                highest_count = count;
                pos = i;
            }
        }
        
        try{
            System.out.println("The most common winner is " + winners[pos].getName());
        }
        catch(Exception e){
            System.out.println("error. invalid user");
        }
        
    }
    
    public static int enter_int(String q){
        Scanner scanner = new Scanner(System.in);
        
        int in = 0;
        boolean correct = false;
        System.out.println(q);
        
        while(!correct){
        try{
            in = Integer.parseInt(scanner.nextLine());
            correct = true;
        }
        catch(Exception e){
            System.out.println("Please enter a valid integer");
        }
        }
        return in;
    }
    
    
    public String getName() {
        return "McQueen";
    }
    
    public String getSlogan() {
        return "I am speed";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // if array is empty, return an instance of this class.
        if(candidates.length == 0){
            return new Candidate_ec22760();
        }
        
        // Start by searching Adam from the list of candidates.
        for(int i = 0; i<candidates.length; i++){
            if(candidates[i].getName().equals("Adam")){
                return candidates[i];
            }
        }
        
        // Then, search for this slogan from the list.
        for(int z = 0; z<candidates.length; z++){
            if(candidates[z].getSlogan().equals("More Trees!")){
                return candidates[z];
            }
        }
         
        // Otherwise, default to the last candidate. 
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // if array is empty, return an instance of this class.
        if(votes.length == 0) return new Candidate_ec22760();
        
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
