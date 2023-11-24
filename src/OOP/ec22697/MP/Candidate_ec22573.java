package OOP.ec22697.MP;// File Candidate_ec22573.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;
class Candidate_ec22573 extends Candidate {
    
    public String getName() {
        return "DnlKhn";
    }
    
    public String getSlogan() {
        return "NoSlogan";
    }
    
    public static void main(String args[]){
        
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] votes = new Candidate[allCandidates.length];
        
        final String terminated = "EXIT";
        int numVotes = 0;
        
        System.out.println("= Running Repeated Elections =");
        String input = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election? d) Quit").toLowerCase();
        
        while(!input.equals(terminated)){
            
            //if user choses to exit 
            if(input.equals("d")){
                input = terminated;
                break;
            }
            //adding a specific candidate
            else if(input.equals("a")){
                
                String username = inputString("Please enter the username of the candidate you would like to add:");
                Candidate c = A3.getByUsername(username, allCandidates);
                
                if (c == null) {
                    System.out.println("Candidate does not exist");
                } else {
                    votes[numVotes] = c;
                    numVotes++;
                }
            }
            //adding a candidate at random
            else if(input.equals("b")){
                Random rs = new Random();
                int randomInt = rs.nextInt(allCandidates.length);
                votes[numVotes] = allCandidates[randomInt];
                numVotes++;
            }
            //running the election
            else if(input.equals("c")){
                //asking for someone to count the votes 
                String userChoice = inputString("Who should count the votes?");
                Candidate electionInspector = A3.getByUsername(userChoice, allCandidates);
                int numTimes = Integer.parseInt(inputString("How many times shall we run the election?"));
        
                
                Candidate[] newElection = new Candidate[numVotes];
                for (int i = 0; i < numVotes; i++) {
                    newElection[i] = votes[i];
                }
                
                Candidate[] winningCandidates = new Candidate[(numTimes * numVotes)];
                
               
                for (int i = 0; i < numTimes; i++) {
                    for (int j = 0; j < numVotes; j++) {
                        try {
                            winningCandidates[(numVotes * i) + j] = newElection[j].vote(newElection);
                        }
                        //vote for myself if any errors were to occur
                        catch (Exception e) {
                            winningCandidates[(numVotes * i) + j] = new Candidate_ec22573();
                        }
                    }
                }
                Candidate actualWinner = electionInspector.selectWinner(winningCandidates);
                //Prints out the winner
                System.out.println("The most common winner is: " + actualWinner.getName());
            }
            else {
                System.out.println("Incorrect input. Please try again.");
            }
            input = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election? d) Quit").toLowerCase();
        }
        
        System.out.println("Program Terminated.");
    }
    
    
    
    public Candidate vote(Candidate[] candidates) {

            // If array is empty, return instance of this class.
            if (candidates.length == 0) 
                return new Candidate_ec22573();

            // First search for Ades Name on the list of candidates.
            for (Candidate c : candidates)
                if (c.getName().equals("Adewale"))
                    return c;

            // Otherwise, choose at random from list.
            Random r = new Random();
            int c = r.nextInt(candidates.length);
            return candidates[c]; 
        }
    
        public Candidate selectWinner(Candidate[] votes) {

            // If array is empty, return instance of this class.
            if (votes.length == 0) 
                return new Candidate_ec22573();

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
                if (count >= highestCount) {
                    highestCount = count;
                    currentWinner = c;
                }
            }

            return currentWinner;
        }

    //reusable inputString method
    public static String inputString(String message){
        
      Scanner scanner = new Scanner(System.in);
      System.out.println(message);
      String ans = scanner.nextLine();
        
        return ans;
      }  
    
    
    
        
    }
    
    



