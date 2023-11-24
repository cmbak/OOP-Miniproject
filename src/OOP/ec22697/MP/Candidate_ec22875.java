package OOP.ec22697.MP;// File Candidate_ec22875.java
//
// Machine generated stub for Assignment 2

import java.util.*;

class Candidate_ec22875 extends Candidate {
    
    public String getName() {
        return "Sawalha";
    }
    
    public String getSlogan() {
        return "Save me";
    }
    
    public Candidate vote(Candidate[] Candidates) {
       
        // If array is empty, return instance of friend's class.
        if (Candidates.length == 0)
            return new Candidate_ec22875();
       
        // Search for a like minded candidate.
        for (Candidate c : Candidates)
            if (c.getSlogan().equals("Save me"))
                return c;
       
        // Otherwise, search for a friend.
        for (Candidate c : Candidates)
            if (c.getName().equals("Sawalha"))
                return c;
       
        // Otherwise, default to last candidate on list.
        return Candidates[Candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22875();
        
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

    public static String input(String String) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println(String);
            return sc.nextLine().toLowerCase();
        }
	}

    public static void listCandidates(Candidate[] Candidates) {
        System.out.println("The Candidates are");

        if(Candidates.length == 0) {
            System.out.println("None");
		}

		else {
            for (int i = 0; i < Candidates.length; i++) {
                System.out.println(Candidates[i].getName());
            }
		}
    }


    public static void Election(Candidate[] candidates, int numCandidates, int numRuns, Candidate[] Candidates) {
		Random random = new Random();
		Candidate[] currentElection = new Candidate[numCandidates];
		int[] voteCounts = new int[numCandidates];
                Candidate[] winners = new Candidate[numRuns*(numCandidates-1)];
		
		for (int i = 0; i < numCandidates; i++) {
			currentElection[i] = candidates[i];
		}
		
		for (int i = 0; i < numRuns; i++) {
			// Reset vote counts for each round of voting
			Arrays.fill(voteCounts, 0);
			
			// Vote for each candidate in the current election
			for (int j = 0; j < numCandidates; j++) {
				Candidate votedFor = currentElection[j].vote(currentElection);
				voteCounts[Arrays.asList(candidates).indexOf(votedFor)]++;
			}
			
			// Determine the winner of the current round of voting
			Candidate winner = currentElection[0];
			for (int j = 1; j < numCandidates; j++) {
				if (voteCounts[j] > voteCounts[Arrays.asList(candidates).indexOf(winner)]) {
					winner = currentElection[j];
				}
			}
			
			// Add the winner to the list of winners
			winners[i] = winner;
		}
		
		// Determine the overall winner of the election
		int[] overallVoteCounts = new int[numCandidates];
		for (int i = 0; i < numRuns; i++) {
			overallVoteCounts[Arrays.asList(candidates).indexOf(winners[i])]++;
		}
		
		// Determine the candidate with the highest vote count
		int maxVotes = 0;
		Candidate overallWinner = candidates[0];
		for (int i = 0; i < numCandidates; i++) {
			if (overallVoteCounts[i] > maxVotes) {
				maxVotes = overallVoteCounts[i];
				overallWinner = candidates[i];
			}
		}
		Candidate Me = A3.getByUsername("ec22875", Candidates);
		System.out.println("The winner of the election with the most votes is: " + Me.selectWinner(winners).getName());
	}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Candidate[] Candidates = A3.getCandidateArray();
        Candidate[] CandidatesN = new Candidate[Candidates.length];

        String option = "";
        int Index = 0;
		boolean ExitCode = true;

        while (ExitCode) {
            option = input("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");

            if(option.equals("a")) {

                String Name = input("Enter a username:");
                Candidate Candidate = A3.getByUsername(Name, Candidates);

                if(Candidate.equals(null)){
                    CandidatesN[Index] = Candidate;
                    Index = Index+1;
                    listCandidates(CandidatesN);
                }

				else {
                    System.out.println("Candidate "+ Name +" does not exist.");
                    listCandidates(CandidatesN);
                }

            }
			else if(option.equals("b")){
                Random rand = new Random();
                CandidatesN[Index] = Candidates[rand.nextInt(Candidates.length)];
                listCandidates(CandidatesN);

            }
			else if(option.equals("c")){
                int RunNo;

                while (true) {
                    try {
                        RunNo = Integer.parseInt(input("Enter Number of election runs required:"));
                        break;
                    } 
					catch (Exception e) {
						System.out.println("Invalid Input");
				}
                }
				ExitCode = false;
                Election(CandidatesN, Index, RunNo, Candidates);
            }
        }
    }
}

