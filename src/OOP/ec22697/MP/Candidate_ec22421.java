package OOP.ec22697.MP;// File Candidate_ec22421.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;

import java.util.Random;

class Candidate_ec22421 extends Candidate {
    
    public static void main(String[] args) {
        Candidate[] allContributions = A3.getCandidateArray();
   
        int counter = 0;
        boolean run = true;
        
        while(run != true) {
        	
        	prl("= Running Repeated Elections =");
            prl("Candidates are");

            if(allContributions != null) {
                for(int i = 0; i < allContributions.length; i++) {
                    prl(allContributions[i].getName());
                }
            }
            else {
            	prl("None");
            }
            
            String answer;
            answer = getString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            
            if(answer.equals("a")) {
            	String name = getString("Please enter a username.");
            	if(A3.getByUsername(name, allContributions) != null) {
            		allContributions[counter] = A3.getByUsername(name, allContributions);
            	}
            }
            else if(answer.equals("b")) {
            	Random random = new Random();
            	int x = random.nextInt(allContributions.length);
            	allContributions[counter] = allContributions[x];
            }
            else if(answer.equals("c")) {
            	String name = getString("Who should count the votes?");
            	int times = Integer.parseInt(getString("How many times should we run the election?"));
            	
            	for(int i = 0; i < times; i++) {
            		Candidate voteCounter = A3.getByUsername(name, allContributions);
            		Candidate winner = voteCounter.selectWinner(allContributions);
            		prl(winner.getName() + " is the winner!");
            	}
            }
            counter += 1;
        }
            
        
        return;
    }
    
    public static String getString(String message) {
        Scanner scanner = new Scanner(System.in);
        String answer;
        
        prl(message);
        answer = scanner.nextLine();
        
        return answer;
        
    }
    
    public static void prl(String message) {
        System.out.println(message);
    }

    public String getName() {
        return "Yagiz Halil";
    }

    public String getSlogan() {
        return "Vote for me.";
    }

    public Candidate vote(Candidate[] c) {

        for(Candidate candidate : c)
        {
            if(candidate.getName().equals("Yagiz Halil")) {
                return candidate;
            }
        }

        Random random = new Random();
        int x = random.nextInt(c.length);

        return c[x];
    }

    public Candidate selectWinner(Candidate[] votesCast) {

        if(votesCast.length == 0) {
            return new Candidate_ec22421();
        }

        Candidate winner = votesCast[0];

        int highest_vote = 0;

        for(Candidate candidate : votesCast) {

            int vote = 0;
            for(Candidate candidate_2 : votesCast) {
                if(candidate == candidate_2) {
                    vote++;
                }
                if(vote >= highest_vote) {
                    highest_vote = vote;
                    winner = candidate;
                }
            }
        }

        return winner;
    }
}
