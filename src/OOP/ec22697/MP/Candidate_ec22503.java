package OOP.ec22697.MP;// File Candidate_ec22503.java
//
// Machine generated stub for Assignment 2

import java.util.*;

class Candidate_ec22503 extends Candidate {
    
    public String getName() {
        return "Buzz";
    }
    
    public String getSlogan() {
        return "To the infinity and beyond";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // Search for Buzz on the the list of candidates.
        for (Candidate c : candidates)
            if (c.getName().equals("Buzz"))
                return c;
        
        // If not choose random candidate on list.
        Random randome = new Random();
        int n = randome.nextInt(candidates.length);
        return candidates[n];
    }
    
    public Candidate selectWinner(Candidate[] votesCast) {
        
        // If array is empty return instance of this clas
        if (votesCast.length == 0) 
            return new Candidate_ec22503();
        
        // Choose winner random 
        Random random = new Random();
        int n = random.nextInt(votesCast.length);
        return votesCast[n];        
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Candidate[] allContributions = A3.getCandidateArray();

        int count = 0;
        boolean running = true;
        String name;

        while(running) {

        	System.out.println("= Running Repeated Elections =");
            System.out.println("Candidates are");

            if(allContributions != null) {
                for(int i = 0; i < allContributions.length; i++) {
                    System.out.println(allContributions[i].getName());
                }
            }
            else {
            	System.out.println("None");
            }

            
          System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
          String choice = scanner.nextLine();

            if(choice.equals("a")) {
            	System.out.println("Please enter a username.");
                 name = scanner.nextLine();
            	if(A3.getByUsername(name, allContributions) != null) {
            		allContributions[count] = A3.getByUsername(name, allContributions);
            	}
            }
            else if(choice.equals("b")) {
            	Random random = new Random();
            	int rand = random.nextInt(allContributions.length);
            	allContributions[count] = allContributions[rand];
            }
            else if(choice.equals("c")) {
            	 System.out.println("Who should count the votes?");
                 name = scanner.nextLine();
            	int runTimes = Integer.parseInt("How many times should we run the election?");
                runTimes = scanner.nextInt();

            	for(int i = 0; i < runTimes; i++) {
            		Candidate vote = A3.getByUsername(name, allContributions);
            		Candidate winner = vote.selectWinner(allContributions);
            		System.out.println(winner.getName() + " is the winner!");
            	}
            }
            count += 1;
        }


        return;
    }

}

