package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22489 extends Candidate {

    public static void pr(String message) {
        System.out.println(message);
    }
    public String getName() {
        return "Sasuke Uchiha";
    }
    public String getSlogan() {
        return "make konoha great again";
    }
    public static String inputString(String message) {
        Scanner scanner = new Scanner(System.in);
        String answer;
        
        pr(message);
        answer = scanner.nextLine();
        
        return answer;   
    }

    public static void main(String[] args) {
        Candidate[] allCont = A3.getCandidateArray();
   
        int counter = 0;
        boolean action = true;
        
        while(action != true) {
        	
        	pr("Running Repeated Elections");
            pr("Candidates are");

            if(allCont != null) {
                for(int i = 0; i < allCont.length; i++) {
                    pr(allCont[i].getName());
                }
            }
            else {
            	pr("None");
            }
            
            String answer;
            answer = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            
            if(answer.equals("a")) {
            	String name = inputString("Please enter a username.");
            	if(A3.getByUsername(name, allCont) != null) {
            		allCont[counter] = A3.getByUsername(name, allCont);
            	}
            }
            else if(answer.equals("b")) {
            	Random random = new Random();
            	int x = random.nextInt(allCont.length);
            	allCont[counter] = allCont[x];
            }
            else if(answer.equals("c")) {
            	String name = inputString("Who should count the votes?");
            	int times = Integer.parseInt(inputString("How many times should we run the election?"));
            	
            	for(int i = 0; i < times; i++) {
            		Candidate voteCounter = A3.getByUsername(name, allCont);
            		Candidate winner = voteCounter.selectWinner(allCont);
            		pr(winner.getName() + " is the winner!");
            	}
            }
            counter += 1;
        }
            
        
        return;
    }
    
    public Candidate vote(Candidate[] c) {

        for(Candidate candidate : c)
        {
            if(candidate.getName().equals("Sasuke Uchiha")) {
                return candidate;
            }
        }

        Random random = new Random();
        int x = random.nextInt(c.length);

        return c[x];
    }

    public Candidate selectWinner(Candidate[] votesCast) {

        if(votesCast.length == 0) {
            return new Candidate_ec22489();
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
