package OOP.ec22697.MP;// File Candidate_ec22517.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

class Candidate_ec22517 extends Candidate {
    private static Scanner scanner() {
        return new Scanner(System.in);
    }

    public static void main(String[] a) {
        Candidate[] allContributions = A3.getCandidateArray();
        ArrayList<Candidate> candidates = new ArrayList<Candidate>();
        String response = "";

        while (!response.equals("c")) { // repeat until user decides to run the election
            System.out.println("Candidates are");
            for (int i=0; i<candidates.size(); i++) {
                System.out.println(candidates.get(i).getName());
            }
            response = inputString("a) add a specific candidate\nb) add a random candidate\nc) run the election\nSelect an option: ");

            if (response.equals("a")) {
                response = inputString("Candidate username: ");
                Candidate candidate = A3.getByUsername(response, allContributions);

                if (candidate == null) { // if candidate is not found
                    System.out.println("Invalid candidate.");
                } else {
                    candidates.add(candidate);
                }

            } else if (response.equals("b")) {
                int index = randInt(0, allContributions.length); // get the index of a random position in the array
                candidates.add(allContributions[index]);
            }
        }

        response = inputString("Who should count the votes? ");
        Candidate candidateCount = A3.getByUsername(response, allContributions);
        if (candidateCount == null) {
            System.out.println("Invalid candidate.");

        } else {
            response = inputString("How many times should we run the election? ");
            int numberOfTimes = Integer.parseInt(response);

            Candidate[] candidateArray = candidates.toArray(new Candidate[candidates.size()]);
            Candidate[] winners = new Candidate[numberOfTimes];

            for (int i=0; i<winners.length; i++) { // run election number of times specified and store results in array
                winners[i] = runElection(candidateArray, allContributions, candidateCount);
            }

            Candidate r = new Candidate_ec22517();
            ArrayList<Candidate> uniqueWinners = new ArrayList<Candidate>();
            int largestCountSoFar = 0;
            
            for (Candidate c : winners) {          
                int currentCount = 0;

                boolean found = false;
                for (int i=0; i<uniqueWinners.size(); i++) { // check if candidate is already in the unique winners list
                    if (uniqueWinners.get(i) == c) {
                        found = true;
                    }
                }

                if (!found) {
                    uniqueWinners.add(c); // add to list if not already in it
                }
                
                for (Candidate d : winners) {
                    if (c == d) {
                        currentCount = currentCount + 1;
                    }
                }
    
                if (currentCount > largestCountSoFar) {
                    largestCountSoFar = currentCount;
                    r = c; // keep track of the most common winner
                }
            }

            System.out.println("Most common winner is " + r.getName() + "\nThere were " + (uniqueWinners.size() - 1) + " other winners");
        }
    }

    public static Candidate runElection(Candidate[] candidates, Citizen[] citizens, Candidate candidateCount) {
        Candidate[] votes = new Candidate[citizens.length];

        for (int i=0; i<citizens.length; i++) { // run the vote method of every citizen and store results in an array
            try {
                votes[i] = citizens[i].vote(candidates);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }        

        return candidateCount.selectWinner(votes);
    }

    public static String inputString(String message) {
        String response;
        
        System.out.print(message);
        response = scanner().nextLine();

        return response;        
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
    
    public String getName() {
        return "Joe";
    }
    
    public String getSlogan() {
        return ":P";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22517();
        
        if (candidates.length != 0) r = candidates[0];
        
        for (Candidate c : candidates)
            if (c.getName().equals("Mr. Bean"))
                return c;
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22517();
        int largestCountSoFar = 0;
        
        if (votes.length != 0) r = votes[0];
        
        for (Candidate c : votes) {          
            int currentCount = 0;
            
            for (Candidate d : votes) {
                if (c == d) {
                    currentCount = currentCount + 1;
                }
            }

            if (currentCount > largestCountSoFar) {
                largestCountSoFar = currentCount;
                r = c;
            }
        }
        
        return r;
    }
    
}
