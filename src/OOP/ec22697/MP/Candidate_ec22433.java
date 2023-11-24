package OOP.ec22697.MP;// File Candidate_ec22433.java
//
// Machine generated stub for Assignment 2

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

class Candidate_ec22433 extends Candidate {

    public String getName() {
        return "Jonathan";
    }

    public String getSlogan() {
        return "Arghhh!";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22433();

        if (candidates.length != 0) {
            r = candidates[0];
        }

        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.

        if (candidates.length == 0) {
            return new Candidate_ec22433();
        }

        for (Candidate c : candidates) {
            if (c.getSlogan().equals("Arghhh!")) {
                return c;
            }
        }

        for (Candidate c : candidates) {
            if (c.getName().equals("Utfur")) {
                return c;
            }
        }



        return candidates[candidates.length - 1];

    }

    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22433();

        if (votes.length != 0) {
            r = votes[0];
        }

        // Add code that either counts the votes
        // or uses some other way to return an element
        // of the array vote as the winner of an election.

        if (votes.length == 0) {
            return new Candidate_ec22433();
        }

        Candidate Winner = votes[0];
        int maxCount = 0;
        for (Candidate c : votes) {
            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count >= maxCount) {
                maxCount = count;
                Winner = c;
            }
        }

        return Winner;
    }

    // add candidate
    public static int addCandidate(Candidate[] eCandidates, int electionCount, Candidate[] candidates) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a username.");
        String username = input.nextLine();

        Candidate c = A3.getByUsername(username, candidates);
        if (c == null) {
            System.out.println("Can't find candidate -> " + username);
            return electionCount;
        }
        else {
            eCandidates[electionCount] = c;
            return electionCount + 1;
        }
    }

    // add random candidate
    public static int addRandomCandidate(Candidate[] eCandidates, int electionCount, Candidate[] candidates) {
        if (electionCount == candidates.length) {
            System.out.println("Can't add more candidates.");
            return electionCount;
        }

        Random rnd = new Random();
        int rndPos;
        Candidate c;
        do {
            rndPos = rnd.nextInt(candidates.length);
            c = candidates[rndPos];
        } while (Arrays.asList(eCandidates).contains(c));

        eCandidates[electionCount] = c;
        return electionCount + 1;
    }

    // get the common winner/s
    public static void getCommonWinner(Candidate[] winners) {
        if (winners.length == 0) {
            System.out.println("No winners.");
            return;
        }

        String mostCommonWinner = null;
        int maxCount = 0;
        boolean isTie = false;

        for (int i = 0; i < winners.length; i++) {
            Candidate c = winners[i];
            if (c == null) {
                continue;
            }

            int count = 1;
            for (int j = i + 1; j < winners.length; j++) {
                if (winners[j] == null) {
                    continue;
                }

                if (c.getName().equals(winners[j].getName())) {
                    count++;
                    winners[j] = null;
                }
            }

            if (count == maxCount) {
                isTie = true;
                mostCommonWinner += " and " + c.getName();
            } else if (count > maxCount) {
                isTie = false;
                maxCount = count;
                mostCommonWinner = c.getName();
            }
        }

        if (isTie) {
            System.out.println("Most common winners are " + mostCommonWinner);
        }
        else {
            System.out.println("Most common winner is " + mostCommonWinner);
        }
        System.out.println("No other winners.");
    }

    // run election
    public static void runElection(Candidate[] eCandidates, int electionCount, Candidate[] candidates) {
        Scanner input2 = new Scanner(System.in);
        System.out.println("Who should count the votes?");
        String name = input2.nextLine();

        Candidate c = A3.getByUsername(name, candidates);

        if (c != null) {
            System.out.println("How many times shall we run the election?");
            int count = input2.nextInt();
            Candidate[] winners = new Candidate[count];
            for (int j = 0; j < count; j++) {
                Candidate[] people = new Candidate[electionCount];
                System.arraycopy(eCandidates, 0, people, 0, eCandidates.length);
                Candidate[] votes = new Candidate[candidates.length];
                for (int k = 0; k < candidates.length; k++) {
                    votes[k] = candidates[k].vote(people);
                }
                winners[j] = c.selectWinner(votes);
            }
            getCommonWinner(winners);
        }
        else {
            System.out.println("Can't find candidate -> " + name);
        }
    }

    // list candidates
    public static void listCandidates(Candidate[] eCandidates, int electionCount) {
        System.out.println("The Candidates are");
        if (electionCount == 0) {
            System.out.println("None");
        }
        else {
            for (int i = 0; i < electionCount; i++) {
                System.out.println((i + 1) + ". " + eCandidates[i].getName());
            }
        }
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            Candidate[] candidates = A3.getCandidateArray();
            Candidate[] eCandidates = new Candidate[candidates.length];
            int eCandidateCount = 0;
            String choice;

            System.out.println("= Running Repeated Elections =");

            while (true)
            {
                listCandidates(eCandidates, eCandidateCount);

                System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit");

                choice = scanner.nextLine();

                if (choice.equals("a")) {
                    eCandidateCount = addCandidate(eCandidates, eCandidateCount, candidates);
                }
                else if (choice.equals("b")) {
                    eCandidateCount = addRandomCandidate(eCandidates, eCandidateCount, candidates);
                }
                else if (choice.equals("c")) {
                    runElection(eCandidates, eCandidateCount, candidates);
                }
                else if (choice.equals("d")) {
                    break;
                }
                else {
                    System.out.println("Please choose option a, b, c or d!");
                }
            }
        }
        catch (Exception exception) {
            System.out.println("There was an error within one of the candidates");
        }
    }

}
