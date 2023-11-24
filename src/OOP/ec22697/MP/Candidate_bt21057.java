package OOP.ec22697.MP;

import java.util.Random;
 import java.util.Scanner;

class Candidate_bt21057 extends Candidate {

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
        return "LEBRON JAMES";
    }

    public String getSlogan() {
        return "CMON MAN, THATS TOO EASY!";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_bt21057();
        
        if(candidates.length==0)
        {
            return new Candidate_bt21057();
        }
        
  

        return r;
    }

    public Candidate selectWinner(Candidate[] votes) {
  
          if (votes.length == 0)      // If nothing in the array
        {
            return new Candidate_bt21057(); // Claim I won.
        }

      

        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
         // Default to first item.
        Candidate winner = votes[0]; 

        // Track highest votes currently
        int currentHighest = 0;

        //Loop over every vote
        for (int i=0; i<votes.length; i++)
        {
            int currentCount = 0;

            //count how many times a vote has been repeated so far
            for (int j=0; j<i; j++)
            {
                if (votes[i].getName() == votes[j].getName()) {currentCount++;}
            }

            // keep track of current highest count (winner)
            if (currentCount > currentHighest)
            {
                currentHighest = currentCount;
                winner = votes[i];
            }
        }

        return winner;


      
    }
    
}
