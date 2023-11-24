package OOP.ec22697.MP;// File Candidate_ec22522.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22522 extends Candidate {
    
    public String getName() {
        return "Sami";
    }
    
    public String getSlogan() {
        return "Criminalise becoming a Candidate once I'm in charge";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22510();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("FRANCIUM FOR SENATE!!!"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Ehimen"))
                return c;
        
        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22522();
        
        if (votes.length != 0) 
            r = votes[0];
 
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
        
        return r;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Candidate[] nameArr = A3.getCandidateArray();
        Candidate[] voteArr = new Candidate[nameArr.length];
        int count = 0;
        String response;

        System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        String ans = scanner.nextLine();

        if (ans .equals("a")){
            System.out.println("Please enter a username.");
            response = scanner.nextLine();
            Candidate newCandidate = A3.getByUsername(response, nameArr);
            voteArr[count] = newCandidate;
            count++;

            namesList(voteArr, count);
        } 
        else if (ans .equals("b")){
            Random rand = new Random();
            int rng = rand.nextInt(nameArr.length);
            voteArr[count] = nameArr[rng];
            count++;
            namesList(voteArr, count); 
        }

        else if (ans .equals("c")){
            System.out.println("Who should count the votes?");
            response = scanner.nextLine();
            Candidate candidateName = A3.getByUsername(response, nameArr);
            System.out.println("How many times shall we run the election?");
            int electionRuns = Integer.parseInt(scanner.nextLine());
            runElection(voteArr, count, electionRuns, candidateName);
        }
    }

    public static void namesList(Candidate[] candidates, int count) {
        System.out.println("Candidates are: ");
        for (int i=0; i<count; i++) {
            System.out.println(candidates[i].getName());
        }
        return;

    }

    public static void runElection(Candidate[] voteArr, int count, int electionRuns, Candidate candidateName) {
        
        Candidate[] newElection = new Candidate[count];

        for (int i=0; i<count; i++) {
            newElection[i] = voteArr[count];
        }

        Candidate[] winners = new Candidate[(count - 1) * electionRuns];

        for (int i=0; i<=electionRuns; i++) {
            for (int j=0; j<count; j++) {
                winners[(count * i) + j] = newElection[j].vote(newElection);
            }
        }

        Candidate winner = candidateName.selectWinner(winners);
        System.out.println("Most common winner is: " + winner.getName());
    }

}
