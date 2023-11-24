package OOP.ec22697.MP;// File Candidate_ec22479.java
//
// Machine generated stub for Assignment 2

import java.util.HashSet;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class Candidate_ec22479 extends Candidate {
    
    //Main method
    public static void main(String[] args) {
        ArrayList<Candidate> candidates = new ArrayList<>(); // specific candidates who can be voted for
        Candidate[] allCandidates = A3.getCandidateArray(); // all candidates who will be voting/ all candidates who can count the votes
        getCandidates(candidates);
        boolean readyToRunElection = false;

        char userOption = getChar("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        while (!(userOption == 'c' && readyToRunElection)) {
            if (userOption == 'a') {
                addCandidate(candidates);
                getCandidates(candidates);
                userOption = getChar("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
                }
            else if (userOption == 'b') {
                Random random = new Random();
                int upperBound = allCandidates.length;
                int randomNum = random.nextInt(upperBound);
                candidates.add(0, allCandidates[randomNum]);
                getCandidates(candidates);
                userOption = getChar("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
                }
                else if (userOption == 'c') {
                    if (candidates.size() == 0) {
                        System.out.println("There are no candidates to run the election. Try adding a candidate first."); 
                        userOption = getChar("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
                    }
                    else
                        readyToRunElection = true; }
            else {
                System.out.println("That's not a valid option. Try again.");
                userOption = getChar("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
                }
        }
        runElection(allCandidates, candidates);
    }


    //Runs the election x many times with x number of candidates
    public static void runElection(Candidate[] allCandidates, ArrayList<Candidate> candidates) {
        ArrayList<Candidate> winningCandidates = new ArrayList<>();
        HashSet<String> invalidCandidates = new HashSet<>();
        //Gets selectWinner method from user that has been input
        String specificName = getString("Who should count the votes?");
        Candidate voteCounter = A3.getByUsername(specificName.toLowerCase(), allCandidates);
        while (voteCounter == null) {
            specificName = getString("User not found. Try again");
            voteCounter = A3.getByUsername(specificName, allCandidates);
        }
        Candidate[] voteCandidates = candidates.toArray(new Candidate[0]);
        int electionRunTime = 1;
        // while (electionRunTime < 1) {
        do {
            try {
                if (electionRunTime < 1) {System.out.println("Please give a positive number (greater than 0)");}
                electionRunTime = getInt("How many times shall we run the election?");
            }
            catch (Exception e) {
                System.out.println("Error (" + e + ") : that does not look like a number to me.");
            }
        } while (electionRunTime < 1);
        for (int i = 0; i < electionRunTime; i++) {
            for (int j = 0; j < 546; j++)
                try {
                allCandidates[j].vote(voteCandidates);
                } catch (Exception e) { invalidCandidates.add(allCandidates[j].getName());}
            Candidate winner = voteCounter.selectWinner(voteCandidates);
            winningCandidates.add(winner);
        }
        mostCommonWinner(winningCandidates.toArray(new Candidate[0]));
        System.out.println("Invalid voters: " + invalidCandidates);
    }


    //Recycled currentWinner method but instead prints the most common winning candidate.
    public static void mostCommonWinner(Candidate[] commonCandidates) {
        // Default to first vote, but this will be over-written.
        Candidate currentWinner = commonCandidates[0]; //candidates[0]
        ArrayList<Candidate> allWinners = new ArrayList<>();
        if (commonCandidates.length == 1)
            allWinners.add(currentWinner);
        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : commonCandidates) { // : votes
            
            int count = 0;
            for (Candidate x : commonCandidates) // : votes
                if (x == c)
                    count++;
            
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
            else if (count == highestCount && !(allWinners.contains(currentWinner))) {
                allWinners.add(currentWinner);
                currentWinner = c;
            }
        }

        for (Candidate c : allWinners) {
            System.out.println("Most common winner is " + c.getName() + " with " + highestCount + " votes.");
        }
        System.out.println("There were no other winners.");
        
    }

    //Makes inputting chars easier. Used in the menu option thingy
    public static char getChar(String input) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(input);
            String output = scanner.next().toLowerCase()+ scanner.nextLine().toLowerCase();
            return output.charAt(0);
    }

    //Gets the candidates who may be voted for
    public static void getCandidates(ArrayList<Candidate> candidates) {
        System.out.println("Candidates are ");
        if (candidates.size() == 0) 
            System.out.println("None");
        else {
            for (int i=0; i < candidates.size(); i++) {
                System.out.println((i+1) + ". " + (candidates.get(i)).getName());
            }
        }
    }

    //Makes inputting strings easier. 
    public static String getString(String input) {
        System.out.println(input);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


        //Makes inputting strings easier. 
        public static int getInt(String input) {
            System.out.println(input);
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        }

    //Adds a candidate to be voted for. Does not allow for duplicate entries. 
    public static void addCandidate(ArrayList<Candidate> candidates) {
        Candidate[] allContributions = A3.getCandidateArray();
        String specificName = getString("Which specific user would you like to include?");
        Candidate specificCandidate = A3.getByUsername(specificName.toLowerCase(), allContributions);
        boolean duplicateEntry = false;
        if (specificCandidate != null) {
            for (Candidate candidate : candidates) {
                if (candidate.getName().equals(specificCandidate.getName())) {
                    duplicateEntry = true;
                    break;
                }
            }
            
            if (duplicateEntry)
            System.out.println("User already included.");
            else candidates.add(0, specificCandidate); 
        }
        else System.out.println("User not found.");
    }

    //Makes King Julian a candidate
    public String getName() {
        return "King Julian";
    }
    
    //King Julian has suffered life-changing injuries
    public String getSlogan() {
        return "Mauricio, I can't move it move it anymore..";
    }
    
    //Vote for a candidate
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22479();

        // cheekily voting for myself
        for (Candidate candidate : candidates) {
            if (candidate.getName().equals("King Julian"))
                r = candidate;
            }

        // if candidates is empty, it'll just return the newly created Candidate_ec22479
        return r;
    }
    
    //Guess who the candidate is
    public Candidate selectWinner(Candidate[] votes) {

        // If array is empty, return instance of this class.
        if (votes.length == 0)
            return new Candidate_ec22479();

        // Default to first vote, but this will be over-written.
        Candidate currentWinner = null;

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

