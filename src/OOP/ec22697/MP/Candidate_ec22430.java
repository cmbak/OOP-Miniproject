package OOP.ec22697.MP;//

import java.util.Scanner;
import java.util.Random;
//Adapted from pull requests

class Candidate_ec22430 extends Candidate {

    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;
        Candidate[] listOfCandidates = A3.getCandidateArray();
        Candidate[] votedCandidates = new Candidate[listOfCandidates.length];

        System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit");
        choice = scanner.nextLine();
        int candidateCounter = 0;

        if (choice.equals("a"))  //Gets username to add specific candidate
        {
            String username;
            System.out.println("Please enter a username.");
            username = scanner.nextLine();
            Candidate newCandidate = A3.getByUsername(username, listOfCandidates);  //Retrieves username from list of candidates

            if (newCandidate == null) {  //If username is not found
                System.out.println("Cannot find " + username + " in list of candidates.");
            }
            votedCandidates[candidateCounter] = newCandidate;    //Adds entered username to array
            candidateCounter = candidateCounter + 1;   //Increments counter by 1

            printElectionCandidates(votedCandidates, candidateCounter);

        }
        else if (choice.equals("b"))
        {
            Random r = new Random();
            int listLength = listOfCandidates.length;
            int randomNum = r.nextInt(listLength); //Creates a random number with upper bound being length of array
            votedCandidates[candidateCounter] = listOfCandidates[randomNum];

            candidateCounter = candidateCounter + 1;  //Increments counter by 1

            printElectionCandidates(votedCandidates, candidateCounter);
        }
        else if (choice.equals("c"))
        {
            String voteCounter;
            System.out.println("Who should count the votes?");
            voteCounter = scanner.nextLine();
            Candidate chosenVoteCounter = A3.getByUsername(voteCounter, listOfCandidates);
            if (chosenVoteCounter == null) {
                System.out.println("Cannot find " + voteCounter + " in list of candidates.");
            }

            runElection(listOfCandidates, votedCandidates, chosenVoteCounter, candidateCounter);
        }
        else
        {
            System.out.println("Invalid input.");
        }


    }

    public static void runElection(Candidate[] listOfCandidates, Candidate[] votedCandidates, Candidate chosenVoteCounter, int candidateCounter)
    {
        Scanner scanner = new Scanner(System.in);
        int numOfTimes = 0;
        System.out.println("How many times shall we run the election?");
        numOfTimes = Integer.parseInt(scanner.nextLine());

        Candidate[] election = new Candidate[candidateCounter];
        Candidate[] winners = new Candidate[candidateCounter * numOfTimes];
        for (int i=0;i<candidateCounter;i++)
        {
            election[i] = votedCandidates[i]; 
        }

        for (int i=0;i<numOfTimes;i++)
        {
            for (int x=0;x<candidateCounter;x++) //Adds result of each election into array
            {
                try {
                    winners[(candidateCounter * i) + x] = election[x].vote(election);
                } catch (Exception exception) {
                    System.out.println("There was an error in the election.");
                }
            }
        }

        Candidate winner = chosenVoteCounter.selectWinner(winners); //Winner is stored and printed
        System.out.println("Most common winner is " + winner.getName());
    }

    public static void printElectionCandidates(Candidate[] votedCandidates, int candidateCounter) {
        System.out.println("Candidates are:");
        for (int i=0; i<candidateCounter;i++)  //Prints name of each candidate in array
        {
            System.out.println(votedCandidates[i].getName());
        }
    }

    public String getName() {
        return "Jesse Pinkman";
    }

    public String getSlogan() {
        return "I will fix the economy";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22430();

        if (candidates.length == 0) {
            return r;
        }
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.

        for (Candidate c : candidates) {
            if (c.getSlogan().equals("let me cook")) {
                return c;
            }
        }

        for (Candidate c : candidates) {
            if (c.getName().equals("Walter Hartwell White")) {
                return c;
            }
        }

        return r;
    }

    // Comment added remotely
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22430();

        if (votes.length == 0) {
            return r;
        }

        // Add code that either counts the votes
        // or uses some other way to return an element
        // of the array vote as the winner of an election.

        Candidate currentWinner = votes[0];
        int highestCount = 0;
        for (Candidate c : votes) {
            int count = 0;
            for (Candidate i : votes) {
                if (c == i) {
                    count = count + 1;
                }
                if (count > highestCount) {
                    highestCount = count;
                    currentWinner = c;
                }
            }

        }

        return currentWinner;
    }

}
