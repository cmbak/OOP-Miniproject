package OOP.ec22697.MP;// File Candidate_ec22478.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

public class Candidate_ec22478 extends Candidate {

    public String getName() {
        return "Artur Aliu";
    }

    public String getSlogan() {
        return "This is my slogan";
    }

    public Candidate vote(Candidate[] candidates) {

        if (candidates.length == 0) {                   //checks if the array is empty
            return new Candidate_ec22478();
        }

        for (int i = 0; i < candidates.length; i++) {
            Candidate ca = candidates[i];
            if (ca.getName().equals("Artur Aliu")) {         //checks if each candidate in the array has the name "Artur Aliu"
                return ca;
            }
        }

        for (int j = 0; j < candidates.length; j++) {
            Candidate ca = candidates[j];
            if (ca.getSlogan().equals("This is my slogan")){      //checks if each candidate in the array has the slogan "This is my slogan"
                return ca;
            }
        }

        Random rand = new Random();
        int ca = rand.nextInt(candidates.length);                 //else a random candidate is generated
        return candidates[ca];

    }

    public Candidate selectWinner(Candidate[] votes) {

        if (votes.length == 0) {                                //checks if the array is empty
            return new Candidate_ec22478();
        }

        Candidate mostVotes = votes[0];
        int highestNum = 0;

        for (int i = 0; i < votes.length; i++) {
            Candidate ca1 = votes[i];
            int numVotes = 0;
            for (int j = 0; j < votes.length; j++) {            //checks if two candidates are the same and increment its number of votes if they are
                Candidate ca2 = votes[j];
                if (ca2 == ca1) {
                    numVotes++;
                }
            }

            if (numVotes >= highestNum) {                       //if the number of votes for the current candidate is higher than the highest number of votes any candidate holds at this moment, then the current candidate now holds the most votes.
                highestNum = numVotes;
                mostVotes = ca1;
            }
        }
        return mostVotes;
    }

    public static String inputString(String str) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(str);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Candidate[] everyCandidate = A3.getCandidateArray();

        Candidate[] everyVote = new Candidate[everyCandidate.length];

        int numCandidates = 0;
        String userAnswer = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");

        while (!userAnswer.equals("a") && !userAnswer.equals("b") && !userAnswer.equals("c")) {
            System.out.println("Invalid input, please try again");
            userAnswer = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        }

        if (userAnswer.equals("a")){
            String user = inputString("Please enter a username.");
            Candidate newCandidate = A3.getByUsername(user, everyCandidate);
            everyVote[numCandidates] = newCandidate;
            numCandidates++;

            candidateList(everyVote, numCandidates);
        }
        else if (userAnswer.equals("b")){
            Random rand = new Random();
            int randNum = rand.nextInt(everyCandidate.length);
            everyVote[numCandidates] = everyCandidate[randNum];
            numCandidates++;

            candidateList(everyVote, numCandidates);
        }

        else if (userAnswer.equals("c")){
            String whoCount = inputString("Who should count the votes?");
            Candidate c = A3.getByUsername(whoCount, everyCandidate);
            while (c == null)
            {
                System.out.println("This candidate doesn't exist, try another candidate");
                whoCount = inputString("Who should count the votes?");
                c = A3.getByUsername(whoCount, everyCandidate);
            }
            System.out.println("How many times shall we run the election?");
            int numtimes = Integer.parseInt(scanner.nextLine());
            runElection(everyVote, numCandidates, numtimes, c, everyCandidate);
        }
    }

    public static void candidateList(Candidate[] candidates, int count) {
        System.out.println("Candidates are: ");
        for (Candidate ca : candidates) {
            System.out.println(ca.getName() + " and their slogan is: " + ca.getSlogan());
        }
    }

    public static void runElection(Candidate[] everyVote, int numCandidates, int numtimes, Candidate c, Candidate[] everyCandidate){
        Candidate[] election = new Candidate[numCandidates];
        for (int i = 0; i < numCandidates; i++) {
            election[i] = everyVote[numCandidates];
        }

        Candidate[] winners = new Candidate[numtimes * (numCandidates - 1)];

        for (int i = 0; i < numtimes; i++) {
            for (int j = 0; j < numCandidates; j++) {
                try {
                    winners[(numCandidates * i) + j] = election[j].vote(election);
                }
                catch (Exception e) {
                    winners[j] = new Candidate_ec22478();
                }
            }
        }
        Candidate mostCommon = c.selectWinner(winners);
        System.out.println("Most common winner is: " + mostCommon.getName());
    }
}
