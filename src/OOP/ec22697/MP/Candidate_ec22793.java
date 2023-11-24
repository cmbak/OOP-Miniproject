package OOP.ec22697.MP;// File Candidate_ec22793.java
//

// Machine generated stub for Assignment 3

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22793 extends Candidate {

	public String getName() {
		return "Brody Foxx";
    	}

    	public String getSlogan() {
        	return "Yo mama!";
    	}

	public Candidate vote(Candidate[] candidates) {
        	// If array is empty, return instance of this class.
		if (candidates.length == 0) 
		    	return new Candidate_ec22793();

		// vote for mohammed
		for (Candidate c : candidates)
			if (c.getName().equals("Mohammed"))
				return c;

		for (Candidate c : candidates)
			if (c.getName().equals("Saho"))
				return c;

		// return candidate 3
		return new Candidate_ec22793();
	}
    
    public Candidate selectWinner(Candidate[] votes) {
        // If array is empty, return instance of this class.
        if (votes.length == 0) 
            return new Candidate_ec22793();
        
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


	public static void main(String[] args){
		Candidate[] allCandidates = A3.getCandidateArray();
		Candidate[] currentCandidates = new Candidate[allCandidates.length];
		int currentCandidateCount = 0;

		// variable declarations for a
		String specificCandidateName = "jonathan"; 
		Candidate newCandidate = new Candidate_ec22793();

		// for b
		int randomInt = 0;

		// for c
		Candidate electionRunner = new Candidate_ec22793();
		Candidate electionWinner = new Candidate_ec22793();
		int electionRuns = 0;
		String electionRunnerUsername = "jonathan";
		int[] winCount = new int[currentCandidateCount]; // to store the amount of times someone has won.
		for(int i = 0; i < currentCandidateCount; i++){
			winCount[i] = 0;
		}
		int mostWinsCandidateIndex = 0;
		int firstPlace = 0;
		int secondPlace = 0;
		int thirdPlace = 0;

		// menu //

		String choice = "none";
		while (!choice.equals("d")) {
			displayCandidates(currentCandidates, currentCandidateCount);
			choice = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit?");

			// add specific candidate.

			if (choice.equals("a")) { 	
				if(allCandidates.length == currentCandidateCount){
					System.out.println("No more candidates allowed!");
				} else {
					specificCandidateName = inputString("Please enter the new candidate's username:");
					newCandidate = A3.getByUsername(specificCandidateName, allCandidates);
					currentCandidates = addCandidate(newCandidate, currentCandidates, currentCandidateCount);
					currentCandidateCount ++;
				}
			}

			// add a random candidate.

			else if (choice.equals("b")) {
				if(allCandidates.length == currentCandidateCount){
					System.out.println("No more candidates allowed!");
				} else {
					Random random = new Random();
					randomInt = random.nextInt(allCandidates.length);
					currentCandidates = addCandidate(allCandidates[randomInt], currentCandidates, currentCandidateCount);
					currentCandidateCount ++;
				}
			}

			// run the election x amount of times.

			else if (choice.equals("c")) {
				electionRunnerUsername = inputString("Who shall run the election? ");
				electionRunner = A3.getByUsername(electionRunnerUsername, allCandidates);
				electionRuns = inputInt("How many times shall the election be ran?");

				for(int i = 0; i < electionRuns; i++){
					// run the election.

					electionWinner = electionRunner.vote(currentCandidates);

					// find the winner in the array, and add one to their win count in the wins array.

					for(int j = 0; j < currentCandidateCount; j++){
						if (currentCandidates[j].getName().equals(electionWinner.getName())){
							winCount[j] += 1;
						}
					}
				}

				// afterwards, determine the candidate with the most winners.

				mostWinsCandidateIndex = winCount[0];
				for(int i = 0; i < currentCandidateCount; i++){
					if (winCount[i] > winCount[mostWinsCandidateIndex]){
						mostWinsCandidateIndex = i;
					}
				}
				firstPlace = mostWinsCandidateIndex;

				// afterwards, determine the candidate with the 2nd most winners.

				mostWinsCandidateIndex = winCount[0];
				for(int i = 0; i < currentCandidateCount; i++){
					if (winCount[i] > winCount[mostWinsCandidateIndex] && mostWinsCandidateIndex != firstPlace){
						mostWinsCandidateIndex = i;
					}
				}
				secondPlace = mostWinsCandidateIndex;

				// afterwards, determine the candidate with the 3rd most winners.

				mostWinsCandidateIndex = winCount[0];
				for(int i = 0; i < currentCandidateCount; i++){
					if (winCount[i] > winCount[mostWinsCandidateIndex] && mostWinsCandidateIndex != firstPlace && mostWinsCandidateIndex != secondPlace){
						mostWinsCandidateIndex = i;
					}
				}
				thirdPlace = mostWinsCandidateIndex;
                
                System.out.println("First place: " + currentCandidates[firstPlace].getName());
                System.out.println("Second place: " + currentCandidates[secondPlace].getName());
                System.out.println("Third place: " + currentCandidates[thirdPlace].getName());
			}
			
            else {
                System.out.println("Please choose a valid option!");
            }
		}
	}

	public static Candidate[] addCandidate(Candidate newCandidate, Candidate[] currentCandidates, int currentCandidateCount){
		currentCandidates[currentCandidateCount] = newCandidate;
		return currentCandidates;
	}

	public static void displayCandidates(Candidate[] currentCandidates, int currentCandidateCount) {
		System.out.println("Candidates are:");
		if (currentCandidateCount == 0) {
			System.out.println("None");
		} else {
			for (int i = 0; i < currentCandidateCount; i++) {
			System.out.println((i+1) + ". " + currentCandidates[i].getName() + " Slogan: " + currentCandidates[i].getSlogan());
			}
		}
	}

	public static int inputInt(String prompt){ // This function is called when the user is to input an integer into the console.
		Scanner keyboard = new Scanner(System.in);
		int userInput = 0;
		boolean allowed = false;
		while (allowed == false){
			try {
				System.out.println(prompt);
				allowed = true;
				userInput = Integer.valueOf(keyboard.nextLine());
			} catch (Exception e) { // Validation, if the user doesn't enter a number.
				allowed = false;
				System.out.println("Please enter a valid integer!");
			}
		}
		return userInput;
	}

	public static String inputString(String prompt){ // This function is called when the user is to input text into the console.
		Scanner keyboard = new Scanner(System.in);
		System.out.println(prompt);
		String userInput = keyboard.nextLine();

		return userInput;
	}

}
