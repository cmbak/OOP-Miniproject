package OOP.ec22697.MP;// File Candidate_ec22529.java

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Candidate_ec22529 extends Candidate {
    
    public String getName() {
        return "Ismail";
    }
    
    public String getSlogan() {
        return "Ismail for President";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        if (candidates.length == 0)
        {  
            return new Candidate_ec22529();
        }

        for (Candidate c : candidates)
            if (c.getSlogan().equals("Ismail for president"))
            {
                return c;
            }
        for (Candidate c : candidates)
            if (c.getName().equals("Ismail"))
                return c;
	    
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        if (votes.length == 0) 
            return new Candidate_ec22529();
        
        Candidate currentWinner = votes[0];
        
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
    
    public static void main(String[] args) {
        
    ArrayList<Candidate> candidates = new ArrayList<>();
    char option;

    do {
        option = selectOption();

        switch (option) {
            case 'a':
            case 'A':
                Candidate chosenCandidate = A3.getByUsername(
                askUser("What is the username of the candidate?"), A3.getCandidateArray());
                candidates.add(chosenCandidate);
                showCandidates(candidates.toArray(new Candidate[0]));
                break;

            case 'b':
            case 'B':
                Candidate randCandidate = randCandidate();
                candidates.add(randCandidate);
                showCandidates(candidates.toArray(new Candidate[0]));
                break;

            case 'c':
            case 'C':
                if (candidates.size() > 0) {
                    Candidate[] winners = countCandidates(candidates.toArray(new Candidate[0]));
                    Candidate finalWinner = locateWinner(winners);

                    System.out.println("The most commonly seen winner is: " + finalWinner.getName());

                    long otherWinnersCount = Arrays.stream(winners)
                        .filter(c -> !c.getName().equals(finalWinner.getName()))
                        .count();

                    String otherWinnersPresent = otherWinnersCount > 0 
                        ? "There were " + otherWinnersCount + " votes for other winners!" 
                        : "There were no other winners.";

                    System.out.println(otherWinnersPresent);
                } else {
                    System.out.println("There is no one to vote for in the array");
                }

                break;

            case 'd':
            case 'D':
                System.out.println("bye bye");
                break;

            default:
                System.out.println("Not a valid option. Please re enter an option from A,B,C,D");
                break;
        }
    } while (option != 'd' && option != 'D');
    }         
    

	public static int askNum(String question) {
        Scanner sc = new Scanner(System.in);
        int num;
        do {
        System.out.println(question);
        try {
            num = Integer.parseInt(sc.nextLine());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Please enter your response as an integer.");
        }
        } while (true);
        return num;
    }
    
    //This method was adapted and inspired thanks to ec22761
	private static Candidate searchForWinner(Candidate[] array, Candidate c) {
		Candidate[] votes = new Candidate[A3.getCandidateArray().length];
		 int numCandidates = A3.getCandidateArray().length;
		
		for(int i = 0; i < numCandidates; i++) {
			try {
				votes[i] = A3.getCandidateArray()[i].vote(array);
			} catch(Exception e) {
				System.out.println("Failed to get " + votes[i] + " candidate");
			}
		}
		return c.selectWinner(votes);
	}
    
    private static String askUser(String prompt) {
    System.out.println(prompt);

    try (Scanner sc = new Scanner(System.in)) {
        return sc.nextLine();
    }
    }
    
    private static Candidate locateWinner(Candidate[] votes) {
   	
        int highestCount = 0;
    	Candidate currentWinner = votes[0];
        for (Candidate cand : votes) {
            int a = 0;
            for (Candidate x : votes)
                //where variable x is a Candidate object
                //and a hold a value for the iteration 
                if (x == cand)
                    a++;
            if (a > highestCount) {
                highestCount = a;
                currentWinner = cand;
            }
        }
    	return currentWinner;
    }
    
    //Inspired from A3.java   
	private static Candidate[] countCandidates(Candidate[] candidates) {
        
    Candidate chosenCounter = A3.getByUsername(askUser("Who should count the votes?"), A3.getCandidateArray());
    int electionCount = askNum("How many times should we run the election?");
    Candidate[] winners = new Candidate[electionCount];
    
    for (int i = 0; i < electionCount; i++) {
        winners[i] = searchForWinner(candidates, chosenCounter);
    }
    
    return winners;
    }
    
   private static char selectOption() {
    Scanner sc = new Scanner(System.in);

    System.out.println("Would you like to...");
    System.out.println("A) Add a specific candidate");
    System.out.println("B) Add a candidate at random");
    System.out.println("C) Run the election?");
    System.out.println("D) Exit");

    char input = ' ';
    while (input != 'A' && input != 'B' && input != 'C' && input != 'D') {
        System.out.print("Enter your choice: ");
        input = sc.nextLine().toUpperCase().charAt(0);

        if (input != 'A' && input != 'B' && input != 'C' && input != 'D') {
            System.out.println("Invalid option. Please choose again.");
        }
    }

    return input;
}
    

    private static void showCandidates(Candidate[] candidates) {
    System.out.println("The list of candidates are:");
    Arrays.stream(candidates).map(Candidate::getName).forEach(System.out::println);
    }
    
    
    private static Candidate randCandidate() {
    	Random rand = new Random();
    	return A3.getCandidateArray()[rand.nextInt(A3.getCandidateArray().length - 1)];
    }
}
