package OOP.ec22697.MP;//
	// Machine generated stub for Assignment 2

import java.util.Scanner;

	class Candidate_ec22897 extends Candidate {

	// print 
	static <T> void print(T message) {
	System.out.println(message);
	}

	// Returns string from user
	static <T> String inputString(T message) {
	Scanner sc = new Scanner(System.in);

	print(message);

	return sc.nextLine();
	}

	// Returns int from user
	static <T> int inputInt(T message) {
	Scanner sc = new Scanner(System.in);
	boolean valid = false;
	int i = 0;

	print(message);

	while (!valid) {
	try {
	    i = Integer.parseInt(sc.nextLine());
	    valid = true;
	}
	catch(Exception e) {
	    System.out.println("Invalid input.");
	}
	}
	return i;
	} 	 


	public String getName() {
	return "joker";
	}

	public String getSlogan() {
	return "Shuush";
	}

	public Candidate vote(Candidate[] candidates) {

	// If array is empty, return instance of friend's class.
	if (candidates.length == 0) 
	    return new Candidate_ec22897();

	// Search for a like minded candidate.
	for (Candidate c : candidates)
	    if (c.getSlogan().equals("Shuush"))
		return c;
	// EDITED THIS USING TERMINAL        
	// Otherwise, default to last candidate on list.
	return candidates[candidates.length-1];
	}

	public Candidate selectWinner(Candidate[] votes) {

	// If array is empty, return instance of friend's class.
	if (votes.length == 0) 
	    return new Candidate_ec22897();

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
	
	//adapted from Candidate_ah21407.java 
	public static void main(String[] args) {
	    final Candidate[] ListOfEveryone = A3.getCandidateArray();
	    Candidate[] votes = new Candidate[ListOfEveryone.length];

	    //requesting the number of candidates
	    int NumberOfCandidates = inputInt("How many candidates do you want to enter?");
	    Candidate[] userCandidates = new Candidate[NumberOfCandidates];
	    for (int i = 0; i < NumberOfCandidates; i++) {
		String username = inputString("Enter candidate username --> ");
		Candidate person = A3.getByUsername(username, ListOfEveryone);
		if (person == null) {
		    print("Not found. Try again.");
		    i--;
		}
		else {
		    userCandidates[i] = person;
		}
	    }

	    print("The list of Candidates you provided:");
		// for (Candidate person : userCandidates) {
		// print(person.getName());
		// }

	for(int i = 0; i < userCandidates.length; i++)
	{
	print((i+1)+"-->"+userCandidates[i].getName());
	}

	    //requesting who will count the votes

	    boolean valid = false;
		Candidate counter = new Candidate_ec22897();
		while (!valid) {
		    String counterUsername = inputString("Who do you want to count the votes? [enter username]");
		    counter = A3.getByUsername(counterUsername, ListOfEveryone);
		    if (counter == null){ 
			print("Not found. Try again.");
		    }
		    else {
			valid = true;
			 }
		}

	    //requesting the amount of runs

		boolean positive = false;
		int numOfRuns = 0;
		while (!positive) {
		    numOfRuns = inputInt("How many times shall we run the election?");
		    if (numOfRuns > 0) {
			positive = true;
		    }
		    else {
			print("Positive number! Try again.");
		    }
		}

		Candidate[] winners = new Candidate[numOfRuns];

		//running the election
		 for (int run = 0; run < numOfRuns; run++) {

		    for (int i = 0; i < ListOfEveryone.length; i++) {
	    		try{
				votes[i] = ListOfEveryone[i].vote(userCandidates);
	   		 }
	   		 catch(Exception e) {
				print("ERROR WITHIN CANDIDATE USERNAME: " + ListOfEveryone[i]);
				i++;
	   		 }
	    }

		    winners[run] = counter.selectWinner(votes);
		}

	    // output winne
		int highestFreq = 0;
		int freq = 0;
		Candidate bigWinner = winners[0];
		for (int i = 0; i < winners.length; i++) {
		    freq = 1;
		    for (int j = 0; j < winners.length; j++) {
			if (winners[i] == winners[j]) freq++;
		    }
		    if (freq > highestFreq) {
			highestFreq = freq;
			bigWinner = votes[i];
		    }
		}
		print("The most frequent winner was " + bigWinner.getName());
	}


	}
