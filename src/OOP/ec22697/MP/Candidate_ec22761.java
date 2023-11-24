package OOP.ec22697.MP;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Candidate_ec22761 extends Candidate {
	
	public String getName() {
	    return "Mr Write In";
	}
    
    public String getSlogan() {
        return "The original";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return myself.
        if (candidates.length == 0) 
            return new Candidate_ec22761();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan() != null && c.getSlogan().equals("The original"))
                return c;
        
        // Otherwise, search for myself again.
        for (Candidate c : candidates)
            if (c.getName() != null && c.getName().equals("Mr Write In"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of myself.
        if (votes.length == 0) 
            return new Candidate_ec22761();
        
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
    
    /**
     * Checks if a candidate is present in the passed array with the
     * passed username. Returns true if so.
     * 
     * @param username The candidate to search for
     * @param candidates The candidates to search through
     * @return True if the candidate is present
     */
    private static boolean candidateNameInArray(String username, Candidate[] candidates) {
    	
    	boolean isValid = false;
    	for(Candidate c : candidates) {
    		if(c.un.equals(username)) {
    			isValid = true; break;
    		}
    	}
    	
    	return isValid;
    }
    
	/**
	 * Asks the user a question requiring an integer answer. If an answer of a
	 * different type is used, it recursively calls itself
	 * 
	 * @param question The question you want to ask
	 * @param scanner  The currently opened scanner
	 * @return The integer response from the user
	 */
	public static int askIntQuestion(String question, Scanner scanner) {
		System.out.println(question);
		try {
			return Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Please enter your response as an integer.");
			System.out.println();
			return askIntQuestion(question, scanner);
		}
	}
       
	/**
	 * Asks the user for a candidate to count votes, as well
	 * as the amount of times the vote process should be run. Returns
	 * an array of all the winners from all the tests.
	 * 
	 * @see #findWinner(Candidate[], Candidate) for each check run
	 * @param array The array of candidates to be voted for
	 * @return All winners from all checks
	 */
	private static Candidate[] tallyCandidates(Candidate[] array) {
		
		
		
		String name = askStringQuestion("Who should count the votes?");
		while(!candidateNameInArray(name, A3.getCandidateArray())) {
			System.out.println("Not a valid candidate!");
			name = askStringQuestion("Who should count the votes?");
		}
		Candidate counter = A3.getByUsername(name, A3.getCandidateArray());
		
		Scanner scanner = new Scanner(System.in);
		int numberOfTimes = askIntQuestion("How many times should we run the election?", scanner);
		Candidate[] winners = new Candidate[numberOfTimes];
		
		for(int i = 0; i < numberOfTimes; i++) {
			winners[i] = findWinner(array, counter);
		}
		
		return winners;
	}
    
    /**
     * Allows all users to vote for their chosen candidate.
     * Once done, this returns the single candidate with the
     * most votes, according to the passed counting candidate.
     * 
     * @param array The candidates that can be voted for.
     * @param counter The candidate whose {@link Citizen#vote(Candidate[])} method will be called
     * @return The winning candidate
     */
	private static Candidate findWinner(Candidate[] array, Candidate counter) {
		Candidate[] votes = new Candidate[A3.getCandidateArray().length];
		
		// Populate array with votes
		for(int i = 0; i < A3.getCandidateArray().length; i++) {
			try {
				votes[i] = A3.getCandidateArray()[i].vote(array);
			} catch(Exception e) {
				// Log that the candidate could not vote
			}
		}
		
		// Find most present candidate in array
		return counter.selectWinner(votes);
	}
    
    /**
     * A simple method for finding the actual winner in
     * the passed in array.
     * 
     * @param votes The candidates to search through
     * @return The candidate with the most votes
     */
    private static Candidate findActualWinner(Candidate[] votes) {
   	
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

    /**
     * @return A random candidate object from the array in A3
     */
    private static Candidate getRandomCandidate() {
    	Random r = new Random();
    	return A3.getCandidateArray()[r.nextInt(A3.getCandidateArray().length - 1)];
    }
    
    /**
     * Simply displays the name of each candidate in the passed array
     * @param candidates Any array of candidates
     */
    private static void displayCandidates(Candidate[] candidates) {
    	System.out.println("Candidates are:");
    	for(Candidate c : candidates) {
    		System.out.println(c.getName());
    	}
    }
    
    /**
     * Asks the user a question requiring a String response
     * 
     * @param question the question to ask
     * @return The user's response
     */
    private static String askStringQuestion(String question) {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println(question);
    	return scanner.nextLine();
    }
    
    /**
     * Asks the user to select an option (a-d) and returns the
     * first character of their response
     * 
     * @return The user's response
     */
    private static char selectOption() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Would you like to... \nA) Add a specific candidate \nB)"
    			+ " Add a candidate at random \nC) Run the election? \nD) Display the current candidates slogans? \nE) Exit");
		return scanner.nextLine().charAt(0);
    }
    
    /**
     * Provides the user a series of options for voting and
     * running elections on Candidates found in A3 
     */
    public static void main(String[] args) {
    	    
    	char selection = selectOption();
    	ArrayList<Candidate> list = new ArrayList<Candidate>();
    	
    	while (selection != 'e' && selection != 'E') {
    		
    		/*
    		 * Jenkins is not a fan of switch statements!
    		 */
    		
    		if(selection == 'a' || selection == 'A') {
    			
    			String name = askStringQuestion("What is the username of the candidate?");
    			while(!candidateNameInArray(name, A3.getCandidateArray())) {
    				System.out.println("Not a valid candidate!");
    				name = askStringQuestion("What is the username of the candidate?");
    			}
    			
    			list.add(A3.getByUsername(name, A3.getCandidateArray()));
				displayCandidates(list.toArray(new Candidate[0]));
    		}
    		else if(selection == 'b' || selection == 'B') {
    			list.add(getRandomCandidate());
				displayCandidates(list.toArray(new Candidate[0]));
    		}
    		else if(selection == 'c' || selection == 'C') {
    			
    			if(list.size() > 0) {
	    			Candidate[] winners = tallyCandidates(list.toArray(new Candidate[0]));
	    			Candidate winner = findActualWinner(winners);
	    			System.out.println("The most present winner was: " + winner.getName() + "!");
	    			
	    			int otherWinners = 0;
	    			for(Candidate c : winners) {
	    				if(!c.getName().equals(winner.getName())) {
	    					otherWinners++;
	    				}
	    			}
	    			
	    			String otherWinnersPresent = otherWinners > 0 ? "There were no other winners." : "There were " + otherWinners + " votes for other winners!";
	    			System.out.println(otherWinnersPresent);
	    			
	    			
	    			
    			} else {
    				System.out.println("No-one to vote for!");
    			}
    			
    			
    		}
    		else if (selection == 'd' || selection == 'D') {
    			if(list.size() > 0) {
    				list.forEach(c -> {
    					if(c.getSlogan()!=null) System.out.println(c.getName() + "'s slogan is: " + c.getSlogan());
    				});
	    		} else {
					System.out.println("No-one to listen to!");
				}
    		}
    		
    		
    		else if(selection != 'e' && selection != 'E') {
    			System.out.println("Not a valid option.");
    		}
    		
    		selection = selectOption();
    	}
    }    
}
