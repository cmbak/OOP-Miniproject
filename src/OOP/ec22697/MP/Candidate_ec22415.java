package OOP.ec22697.MP;// File Candidate_ec22415.java
//
// Machine generated stub for Assignment 2
// number of votes method, updated select winner method and yesOrNo methods inspired by ec19389
// code complete for self merging now maybe?

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

class Candidate_ec22415 extends Candidate {

    public static void main(String[] args) {
        Candidate[] allCandidates = A3.getCandidateArray();
        ArrayList<Candidate> forElection = new ArrayList<>();
        boolean end = false;

        print("########### Running elections ###########");

        while (!end) {

            char choice = charToLowerCase("Would you like to a) add a specific candidate b) add a candidate at random c) see candidates running for election d) run the election? e) exit the program");

            switch (choice) {
                case 'a':
                    addCandidate(allCandidates, forElection);
                    break;
                case 'b':
                    addRandomCandidate(allCandidates, forElection);
                    break;
                case 'c':
                    printForElection(forElection);
                    break;
                case 'd':
                    runElection(allCandidates, forElection);
                    end = yesOrNo("Do you want to exit the program? (yes/no)");
                    break;
                case 'e':
                    end = yesOrNo("Are you sure you want to exit the program? (yes/no)");
            }

        }

    }

    public static <T> void print(T out) {
        System.out.println(out);
    }

    public static String inputString(String message) {
        Scanner scanner = new Scanner(System.in);
        print(message);
        return scanner.nextLine();
    }

    public static String inputStringToLowerCase(String message) {
        return inputString(message).toLowerCase();
    }

    public static int inputInt(String message) {
        return Integer.parseInt(inputString(message));
    }

    public static char inputChar(String message) {
        return inputString(message).charAt(0);
    }

    public static char charToLowerCase(String message) {
        return inputStringToLowerCase(message).charAt(0);
    }

    public static boolean yesOrNo(String message) {
        String userInput = inputString(message);
        while (!(userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("no"))) {
            userInput = inputString(message);
        }
        return userInput.equalsIgnoreCase("yes");
    }

    public static void printForElection(ArrayList<Candidate> forElection) {
        print("The candidates that are running are: ");
        for (int i = 0; i < forElection.size(); i++) {
            print(i + 1 + ". " + forElection.get(i).getName());
        }
    }

    public static void addCandidate(Candidate[] allCandidates, ArrayList<Candidate> forElection) {
        String username = inputString("Please enter Candidate username: ");
        if (A3.getByUsername(username, allCandidates) == null) {
            print("This Candidate does not exist\n");
        } else {
            forElection.add(A3.getByUsername(username, allCandidates));
        }
    }

    public static void addRandomCandidate(Candidate[] allCandidates, ArrayList<Candidate> forElection) {
        Random r = new Random();
        forElection.add(allCandidates[r.nextInt(allCandidates.length)]);
    }

    public static void runElection(Candidate[] allCandidates, ArrayList<Candidate> forElection) {
        if(!(forElection.size() == 0)) {
            String voteCounterUsername = inputString("Who should count the votes?");
            boolean validCounter = false;

            while (!validCounter) {
                if ((A3.getByUsername(voteCounterUsername, allCandidates)) == null) {
                    System.out.println("This candidate does not exist.");
                    voteCounterUsername = inputString("Who should count the votes?");
                } else{
                    validCounter = true;
                }
            }

            Candidate voteCounter = A3.getByUsername(voteCounterUsername, allCandidates);

            int electionRuns = inputInt("How many times should we run the election?");

            Candidate[] forElectionCandidates = new Candidate[forElection.size()];
            for(int i = 0; i < forElection.size(); i++) {
                forElectionCandidates[i] = forElection.get(i);
            }

            Candidate[] winners = new Candidate[electionRuns];

            for(int i = 0; i < electionRuns; i++) {
                Candidate[] votes = new Candidate[allCandidates.length];
                for(int j = 0; j < allCandidates.length; j++) {
                    try {
                        votes[j] = allCandidates[j].vote(forElectionCandidates);
                    }
                    catch (Exception ignored) {}

                }
                winners[i] = voteCounter.selectWinner(votes);
            }
            Candidate winner =  findWinner(winners);
            print("Winner is " + winner.getName());
        }
        else {
            print("There are no candidates running for election at the moment, please add nominate candidates to continue");
        }
    }

    public static Candidate findWinner(Candidate[] winners) {
        int max = 0;
        Candidate winner = null;

        for(int i = 0; i < winners.length; i++) {
            int count = 0;
            for (int j = 0; j < winners.length; j++) {
                if (winners[i] == winners[j]) {
                    count++;
                }
            }
            if(count > max) {
                max = count;
                winner = winners[i];
            }
        }
        return winner;
    }

    public String getName() {
        return "Walter Hartwell White";
    }

    public String getSlogan() {
        return "let me cook 🥺👉👈";
    }

    public Candidate vote(Candidate[] candidates) {
        // search for self in the candidates list 
        for (Candidate c : candidates) {
            if (c.getName().equals("Walter Hartwell White")) {
                return c;
            }
        }

        // or default to random
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }

    public static int numberOfVotes(Candidate c, Candidate[] totalVotes) {
        int count = 0;

        for (Candidate totalVote : totalVotes) {
            if (totalVote == c) {
                count++;
            }
        }
        return count;
    }

    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0) {
            return new Candidate_ec22415();
        }

        Candidate winningCandidate = votes[0];
        int mostFrequent = 0;

        for (Candidate vote : votes) {
            if ((numberOfVotes(vote, votes)) > mostFrequent) {
                mostFrequent = (numberOfVotes(vote, votes));
                winningCandidate = vote;
            }
        }
        return winningCandidate; // Return a new Candidate object no-one voted for.
    }


}
