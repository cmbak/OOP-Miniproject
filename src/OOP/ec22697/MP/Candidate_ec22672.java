package OOP.ec22697.MP;// File Candidate_ec22672.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;


class Candidate_ec22672 extends Candidate {
	
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("=Running Repeated Election=");
		Candidate[] candidatesA3 = A3.getCandidateArray();
		ArrayList<Candidate> currentCandidates = new ArrayList<Candidate>();
		boolean running = true;
		while(running){
			System.out.println("Candidates are");
			for(Candidate c : currentCandidates){System.out.println(c.getName());}
			System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
			String option = sc.nextLine();
			if(option.equals("a")){
				System.out.println("Please enter a username. ");
				String username = sc.nextLine();
				Candidate selection = A3.getByUsername(username, candidatesA3);
				if(selection!=null){
					currentCandidates.add(selection);
					
				}else{
					System.out.println("Username invalid");
				}
			}else if(option.equals("b")){
				Random random = new Random();
				int randomNumber = random.nextInt(candidatesA3.length-1);
				currentCandidates.add(candidatesA3[randomNumber]);
			}else if(option.equals("c")){
				System.out.println("Who should count the votes?");
				String inputUsername = sc.nextLine();
				if(A3.getByUsername(inputUsername, candidatesA3) != null){
					Candidate voteCounter = A3.getByUsername(inputUsername, candidatesA3);
					int electionRuns = getValidInt("How many times should we run the election?");
					String winner = "";
					for(int i=0; i<electionRuns;i++){
						int highestCount = 0;
						for (Candidate c : currentCandidates) {
						   int count = 0;
						   for (Candidate x : currentCandidates)
							   if (x == c)
								   count++;
						   if (count > highestCount) {
							   highestCount = count;
							   winner = c.getName();
						   }
						}
					}
					System.out.println("Most common winner is "+winner);
				}else{
					System.out.println("Username invalid");
				}
			}
		}
	}
	
	public static int getValidInt(String prompt){
		System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        if(sc.hasNextInt()){
            int x = sc.nextInt();
            if(x > 0){
                return x;
            }
        }
        System.out.println("Invalid Input.");
        return getValidInt(prompt);
    }
	

    public String getName() {
        return "Mike";
    }

    public String getSlogan() {
        return "Hawk";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22672();

        if (candidates.length != 0) r = candidates[0];

      	for (Candidate c : candidates) {
      		  if(c.getName().equals("BM")){
      			r = c;
      		}
        }

        return r;
    }

    public Candidate selectWinner(Candidate[] votes) {

        // If array is empty, return instance of friend's class.
        if (votes.length == 0)
           return new Candidate_ec22672();

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

}
