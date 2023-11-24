package OOP.ec22697.MP;// File Candidate_ec22652.java

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22652 extends Candidate {

    public static void main(String[] args) {
        System.out.println("= Running Repeated Elections =");
	
	Scanner scanner = new Scanner(System.in);

	Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] currentCandidates = {};

        printCurrentCandidates(currentCandidates);
	String option = takeOption();
        while (!option.equals("d")) {
	    if (option.equals("a")) {
	        System.out.println("Please enter a username:");
	        String username = scanner.nextLine();
                Candidate selectedCandidate = A3.getByUsername(username, allCandidates);
		if (selectedCandidate != null) {
	            currentCandidates = addCandidate(currentCandidates, selectedCandidate);
		}
		else if (selectedCandidate == null) {
		    System.out.println("Not a valid candidate.");
		}
	    }
	    else if (option.equals("b")) {
                Candidate randomCandidate = allCandidates[(new Random()).nextInt(allCandidates.length)];
	        currentCandidates = addCandidate(currentCandidates, randomCandidate);
	    }
	    else if (option.equals("c")) {
		System.out.println("Who should count the votes?");
        	String user = scanner.nextLine();
        	System.out.println("How many times shall we run the election?");
        	String times = scanner.nextLine();
		try {
		    int timesRan = Integer.parseInt(times);

        	    Candidate[] winners = {};
        	    Candidate[] votedCandidates = addCandidate(currentCandidates, new Candidate_ec22652());
        	    for (int i = 0; i < timesRan; i++) {
		        int totalVotes = 0;
            	        for (int j = 0; j < currentCandidates.length; j++) {
			    try {
                                votedCandidates[totalVotes] = currentCandidates[j].vote(currentCandidates);
			        totalVotes += 1;
			    } catch (Exception e) {
			        System.out.println(currentCandidates[j].getName() + " couldn't vote.");
			    }
            	        }
            	        Candidate[] votedFor = new Candidate[totalVotes];
            	        for (int j = 0; j < totalVotes; j++) {
                            votedFor[j] = votedCandidates[j];
            	        }
                        Candidate winner = A3.getByUsername(user, allCandidates).selectWinner(votedFor);
                        winners = addCandidate(winners, winner);
                    }
	            Candidate[] topWinners = findWinners(winners);
	            System.out.println("Most common winners:");
	            for (int i = 0; i < topWinners.length; i++) {
	                System.out.println(topWinners[i].getName());
	            }
		} catch (Exception e) {
		    System.out.println("Something went wrong.");
		}
	    }
            printCurrentCandidates(currentCandidates);
            option = takeOption();
        }
    }

    static Candidate[] findWinners(Candidate[] winners) {
        Candidate[] allWinners = {};
        for (Candidate w : winners) {
	    boolean winnerExists = false;
	    for (Candidate c : allWinners) {
		if (w == c) {
		    winnerExists = true;
		}
            }
	    if (!winnerExists) {
		allWinners = addCandidate(allWinners, w);
	    }
	}
	int[] numberOfWins = new int[allWinners.length];
	for (Candidate w : winners) {
	    for (int i = 0; i < allWinners.length; i++) {
		if (w == allWinners[i]) {
		    numberOfWins[i] += 1;
		}
	    }
	}
	int highestWins = -1;
	for (int i = 0; i < numberOfWins.length; i++) {
	    if (numberOfWins[i] > highestWins) {
		highestWins = numberOfWins[i];
	    }
	}
	Candidate[] topWinners = {};
	for (int i = 0; i < numberOfWins.length; i++) {
	    if (highestWins == numberOfWins[i]) {
		topWinners = addCandidate(topWinners, allWinners[i]);
	    }
	}
	return topWinners;
    }

    static Candidate[] addCandidate(Candidate[] oldCandidates, Candidate candidate) {
        Candidate[] newCandidates = new Candidate[oldCandidates.length + 1];
        for (int i = 0; i < oldCandidates.length; i++) {
            newCandidates[i] = oldCandidates[i];
        }
	newCandidates[newCandidates.length - 1] = candidate;
        return newCandidates;
    }

    static String takeOption() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to:");
	System.out.println("a) Add a specific candidate");
	System.out.println("b) Add a candidate at random");
	System.out.println("c) Run the election");
	System.out.println("d) Exit");
	String option = scanner.nextLine();

        return option;
    }

    static void printCurrentCandidates(Candidate[] currentCandidates) {
        System.out.println("Candidates are:");
        for (int i = 0; i < currentCandidates.length; i++) {
            System.out.println((i + 1) + ". " + currentCandidates[i].getName());
        }
    }

    public String getName() {
        return "Recursion";
    }
    
    public String getSlogan() {
        return "When will it end?";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22652();
        
        //Goes through all the candidates
        //Votes for the first with the name Recursion
        for (Candidate c : candidates) {
            if (c.getName().equals("Recursion")) {
                return c;
            }
        }
        
        //No candidate with the name recursion
        //Vote for the first element in the array
        if (candidates.length != 0) {
            r = candidates[0]; 
        }
 
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22652();
        
        //Goes through all the candidates
        //Selects the first with the name Recursion as the winner
        for (Candidate c : votes) {
            if (c.getName().equals("Recursion")) {
                return c;
            }
        }

	//No candidate with the name recursion
	//Winner is first element in the array
	if (votes.length != 0) {
	    r = votes[0];
	}
 
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
        
        return r;
    }
    
}
