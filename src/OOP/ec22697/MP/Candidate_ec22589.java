package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22589 extends Candidate {
    
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        
        Candidate[] all_candidates = A3.getCandidateArray();     // Every candidate
        Candidate[] election = new Candidate[all_candidates.length];  // Array for the election with length equal to all candidates array
        int count = 0;  // For the next empty index
        
        System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        String option = scanner.nextLine();
        
        if (option.equals("a")){
            System.out.println("Please enter a username: ");
            String username = scanner.nextLine();
            
            Candidate new_candidate = A3.getByUsername(username, all_candidates);  // Get that candidate
            election[count] = new_candidate;   // Add candidate to election
            count++;  // For the next candidate
            
            System.out.println("The candidates are: ");

            for (int i=0; i<count; i++){
               System.out.println(i+1 + ". " + election[i].getName());
            }
            
        }
        else if (option.equals("b")){
            Random random = new Random();
            int random_num = random.nextInt(all_candidates.length);   // 0 to the length
            
            Candidate random_candidate = all_candidates[random_num];
            
            election[count] = random_candidate;
            count++;
        }
        
        else if (option.equals("c")){
            System.out.println("Who should count the votes? ");
            String name = scanner.nextLine();
            
            Candidate chosen_candidate = A3.getByUsername(name, all_candidates);  // Get that candidate
            
            System.out.println("How many times should we run the election? ");
            int loop = Integer.parseInt(scanner.nextLine());
            
            Candidate[] votes = new Candidate[count];  // Stores the candidates that people voted for
            
            for (int i=0; i<loop; i++){                                
                for (int j=0; j<count; j++){
                    votes[i] = election[i].vote(election);   // Get every candidate to vote                                       
                }
                
                Candidate winner = chosen_candidate.selectWinner(votes);
                System.out.println("The most common winner is: " + winner.getName());
            }
                      
        }                
    }
    
    public String getName() {
        return "Fuhou";
    }
    
    public String getSlogan() {
        return "More money";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of this class.
        if (candidates.length == 0) 
            return new Candidate_ec22589();
        
        // Otherwise, default to ramdom candidate on list.
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of this class.
        if (votes.length == 0) 
            return new Candidate_ec22589();
        
        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];
        
        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        
        for (int i=0; i<votes.length; i++){
            int count = 0;
            
            for (int j=0; j<votes.length; j++){
                if (votes[j] == votes[i]){
                   count ++;               
                }
            }
                
            if (count >= highestCount) {
                highestCount = count;
                currentWinner = votes[i];
            }
                
        }
        
        return currentWinner;
    }
}
