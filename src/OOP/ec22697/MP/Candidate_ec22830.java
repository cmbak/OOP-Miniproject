package OOP.ec22697.MP;// File Candidate_ec22830.java
//
// Machine generated stub for Assignment 2

import java.util.*;

class Candidate_ec22830 extends Candidate {
    
    public static void main(String[] args) {
        
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] votes = new Candidate[allCandidates.length];
        int counter=0; 
        
        String choice = inputString("Would you like to a) add a specific candidate b) run the election?"); 
        
        while (choice.equals("a") | choice.equals("a)")){
            
            String username = inputString("Which candidate would you like to add?"); 
            
            Candidate temp = A3.getByUsername(username, allCandidates);
            
            if (temp == null){
                System.out.println("We did not find this person!");
            }
            else{
                votes[counter]=temp; 
            }
                
            counter++; 
            choice = inputString("If you want to enetr a new candidate press 'a', otherwise press 'b'");
        }
        
        System.out.println("Candidates are:");
        
        for (Candidate c : votes){
            System.out.println(c.getName()); 
        }
        
        String runner = inputString("Who should run the vote");
        Candidate tempRunner = A3.getByUsername(runner, allCandidates);
        Candidate voteRunner = null; 
        
        if (tempRunner == null){
                System.out.println("We did not find this person!");
            }
        else{
            voteRunner = tempRunner;
        }
            
        int howManyTimes = inputInt("How many times would you like to run the elections");
        Candidate[] winners = new Candidate[howManyTimes];
        Candidate[] allVotes = new Candidate[allCandidates.length];
        
        for (int i=0; i<=counter; i++){
            for (int j=0; j<allCandidates.length; j++){
                allVotes[j]=allCandidates[i].vote(votes);
            }
            winners[i] = voteRunner.selectWinner(allVotes);
        }
        Candidate finalWinner = voteRunner.selectWinner(winners); 
        System.out.println("The winner is "+ finalWinner.getName() + " with the slogan "+ finalWinner.getSlogan());
        

    }
         
    public static int inputInt(String message) {
        int answer; 
        Scanner scanner = new Scanner(System.in); 
        
        System.out.println(message); 
        answer = scanner.nextInt(); 
        
        return answer; 
    }    
    
    public static String inputString(String message) {
        String answer; 
        Scanner scanner = new Scanner(System.in); 
        
        System.out.println(message); 
        answer = scanner.nextLine(); 
        
        return answer; 
    }        
        
    public String getName() {
        return "Arthur";
    }    
    public String getSlogan() {
        return "More Trees!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22830();
        
        if (candidates.length == 0){
            return new Candidate_ec22830();
        }
        
        for (Candidate c : candidates){
            if (c.getName().equals("Mr. Bean")){
                return c; 
            }
        }
        
        for (Candidate c : candidates){
            if (c.getSlogan().equals("Teddy!")){
                return c;
            }
        }

        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
        return r;
    }

	
    //This method will select the winner 
    public Candidate selectWinner(Candidate[] votes) {
        
        if (votes.length == 0){
            return new Candidate_ec22830();
        }
        
        Candidate r = votes[0]; 
        
        int Highest=0;
        for (Candidate c : votes){
            int count=0;
            for (Candidate x :votes){
                if (x==c){
                    count++; 
                }
                if (count>=Highest){
                    Highest=count; 
                    r = c; 
                }
            }
        }
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
        
        return r;
    }
    
}
