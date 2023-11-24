package OOP.ec22697.MP;// File Candidate_ec22542.java
//
/// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;
import java.util.LinkedList;

class Candidate_ec22542 extends Candidate {


    public static void main(String[] args) {
        System.out.println("==Running repeated elections==");
        Candidate[] candidates = A3.getCandidateArray();
        LinkedList<Candidate> A3candidates = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            for (Candidate ca : A3candidates) {
                System.out.println(ca.getName());
            }

            System.out.println("Here are the current candidates");
            System.out.println(" You have 3 options. a) Add candidate b) Add random candidate c) Run ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("b")) {
                Random randomCandidate = new Random();
                A3candidates.add(candidates[randomCandidate.nextInt(candidates.length - 1)]);
            } else if (userInput.equalsIgnoreCase("a")) {
                System.out.println("Please enter the username of the candidate you want to add");
                String userCandidate = scanner.nextLine();
                Candidate username = A3.getByUsername(userCandidate, candidates);
                A3candidates.add(username);

            } else if (userInput.equalsIgnoreCase("c")) {
                System.out.println("Who should Count the votes");
                String userCounter = scanner.nextLine();
                Candidate candidateCounter = A3.getByUsername(userCounter, candidates);
                if (candidateCounter != null) {
                    System.out.println("How many times shall we run this election");
                    if (scanner.hasNextInt()) {
                        int answer = scanner.nextInt();
                        String electionWinner = "default";

                        for (int i = 0; i < answer; i++) {
                            int winningCount = 0;
                            for (Candidate ca : A3candidates) {
                                int count = 0;
                                for (Candidate candidatesA3 : A3candidates) {
                                    if (candidatesA3 == ca) {
                                        winningCount++;
                                    }
                                    if (count > winningCount) {
                                        winningCount = count;
                                        electionWinner = ca.getName();
                                    }
                                }
                            }
                        }
                        System.out.println("Most common winner is " + electionWinner);
                    } else {
                        System.out.println("Username invalid");
                    }

                }
            }
        }
    }



    

    public String getName() {
        return "BM";
    }

    public String getSlogan() {
        return "Just do it";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22542();

        if (candidates.length != 0) r = candidates[0];

        for (Candidate a: candidates){
		if(a.getName().equals("Mike")){
			r=a;
		}
	}



        return r;
    }

    public Candidate selectWinner(Candidate[] votes) {

      // If array is empty, return instance of this class.
      if (votes.length == 0)
          return new Candidate_ec22542();

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

}
