package OOP.ec22697.MP;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// File Candidate_ec22962.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22962 extends Candidate {

    public static void main(String[] args) {
        // Create an array for all candidates
        Candidate[] candidates = A3.getCandidateArray();
        List<Candidate> electionCandidates = new ArrayList<>();

        System.out.println("= Running Repeated Elections =");

        boolean loop = true;
        while(loop) {
            System.out.println("\nCandidates are");
            if(electionCandidates.isEmpty()) { System.out.println("None"); }
            for(int i = 0; i < electionCandidates.size(); i++) {
                System.out.println(electionCandidates.get(i).getName());
            }

            System.out.println("\nWould you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            Scanner scanner = new Scanner(System.in);
            String userChoice = scanner.nextLine();
            
            switch(userChoice) {
                case "a":
                    System.out.println("Please enter a username");
                    String username = input();
                    Candidate specificCandidate = A3.getByUsername(username, candidates);
                    try {
                        if (specificCandidate != null) {
                            electionCandidates.add(specificCandidate);
                        }
                    } catch(Exception e) {
                        System.out.println("Couldn't get candidate.");
                    }
                    break;
                case "b":
                    int randIndex = new Random().nextInt(candidates.length);
                    electionCandidates.add(candidates[randIndex]);
                    break;
                case "c":
                    System.out.println("Who should count the votes?");
                    String voteCounter = input();
                    
                    System.out.println("How many times shall we run the election?");
                    Integer electionRepetitions = inputInt();

                    Candidate theOneWhoCounts = A3.getByUsername(voteCounter, candidates);
                    if(theOneWhoCounts != null) {
                        runElection(candidates, electionCandidates, electionRepetitions, theOneWhoCounts);
                        String postElectionAction = getElectionAction();
                        if(postElectionAction.equals("a")){ loop=false;break; }
                    }
                    break;
                case "d":
                    loop = false;
                    break;
                default:
                    System.out.println("");
                    loop = false;
                    break;
            }
        }
    }

    public static void runElection(Candidate[] candidates, List<Candidate> electionCandidates, int electionRepetitions, Candidate theOneWhoCounts) {
        Candidate[] electionCandidatesArr = electionCandidates.toArray(new Candidate[electionCandidates.size()]);
        Candidate[] votes = new Candidate[electionCandidatesArr.length];
        Candidate[] winners = new Candidate[electionRepetitions];
        
        System.out.println(electionCandidatesArr.length);

        for (int i=0; i<electionRepetitions; i++) {
            try {
                for (int j=0; j<candidates.length; j++) {
                    votes[j] = candidates[j].vote(electionCandidatesArr);
                }
            } catch(Exception e) {
                System.out.println("\nCouldn't run election " + (i+1));
                System.out.print(e);
            }
            winners[i] = theOneWhoCounts.selectWinner(votes);
        }
        
        try {
            Candidate overallWinner = theOneWhoCounts.selectWinner(winners);
            System.out.println("\nMost common winner is " + overallWinner.getName());
            System.out.println("Their slogan is " + overallWinner.getSlogan());
        } catch(Exception e) {
            System.out.println("\nCounldn't get winner.");
        }
    }

    public static String getElectionAction() {
        System.out.println("\nWould you like to \na) exit\nb) run same election many times\nc) check who counts honestly\nd) run all possible two-candidate elections\ne) run a higher-order election");
        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.nextLine();
        return userChoice;
    }

    public static String input() {
        Scanner scanner = new Scanner(System.in);
        String inp = scanner.nextLine();
        return inp;
    }

    public static Integer inputInt() {
        Scanner scanner = new Scanner(System.in);
        Integer inp = scanner.nextInt();
        return inp;
    }

    public String getName() {
        return "Egg";
    }
    
    public String getSlogan() {
        return "Hi, kids!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0) 
            return new Candidate_ec22948();
        
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Hi, kids!"))
                return c;
        
        for (Candidate c : candidates)
            if (c.getName().equals("SashaMasha"))
                return c;
        
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0) 
            return new Candidate_ec22948();
        
        Candidate currentWinner = votes[0];
        
        int highestCount = 0;
        for (Candidate c : votes) {
            
            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if(count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        
        return currentWinner;
    }
    
}
