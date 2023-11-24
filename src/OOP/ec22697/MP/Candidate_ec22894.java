package OOP.ec22697.MP;// File Candidate_ec22894.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22894 extends Candidate {

    public static void main(String[] args) {
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] newCandidateList = new Candidate[allCandidates.length];
        int count = 0;
        boolean exit = false;
        while (!exit) {
            print("= Running Repeated Elections =");
            String choice = getString("Would you like to a) add a specific candidate b) run the election c) exit ?");
            if (choice.equals("a")) {
                count = addCandidate(allCandidates, newCandidateList, count);
            } else if (choice.equals("b")) {
                runElection(allCandidates, newCandidateList, count);
            } else if (choice.equals("c")) {
                break;
            } else {
                print("Please either select a, b or c.");
            }
        }
    }

    public static void runElection(Candidate[] allCandidates, Candidate[] newCandidateList, int count) {
        String voteCount = getString("Please enter who you would like to count the votes: ");
        Candidate voteCounter = A3.getByUsername(voteCount, newCandidateList);
        if (voteCounter != null) {
            int numOfElections = getInt("How many rounds of elections would you like to do: ");
            Candidate[] currentWinner = new Candidate[numOfElections];
            for (int i = 0; i < numOfElections; i++) {
                Candidate[] newList = newCandidateArray(allCandidates, count);
                Candidate[] votes = new Candidate[newCandidateList.length];
                for (int x = 0; x < newCandidateList.length; x++) {
                    try {
                        votes[i] = allCandidates[i].vote(newList);
                    } catch (Exception e) {
                        votes[i] = new Candidate_ec22894();
                    }
                }
                currentWinner[i] = voteCounter.selectWinner(votes);
            }
            Candidate winner = voteCounter.selectWinner(currentWinner);
            printWinner(winner);
        } else {
            print("Candidate could not be found.");
        }
    }

    private static Candidate[] newCandidateArray(Candidate[] newCandidateList, int count) {
        Candidate[] newArray = new Candidate[count];
        for (int i = 0; i < count; i++) {
            newArray[i] = newCandidateList[i];
        }
        return newArray;
    }

    public static int addCandidate(Candidate[] allCandidates, Candidate[] newCandidateList, int count) {
        String candidateInput = getString("Please enter the candidate you wish to add: ");
        Candidate candidateName = A3.getByUsername(candidateInput, allCandidates);
        if (candidateName != null) {
            newCandidateList[count] = candidateName;
            count++;
        } else {
            print("Candidate was not found.");
        }
        return count;
    }

    public static void printWinner(Candidate winner) {
        if (winner == null) {
            System.out.println("no winner");
        } else {
            System.out.println("Most common winner is " + winner.getName());
        }
        return;
    }

    public static void print(String s) {
        System.out.print(s);
    }

    public static String getString(String message) {
        Scanner s = new Scanner(System.in);
        System.out.println(message);
        return s.nextLine();
    }

    public static int getInt(String message) {
        Scanner s = new Scanner(System.in);
        System.out.println(message);
        return s.nextInt();
    }

    public String getName() {
        return "Luffy";
    }

    public String getSlogan() {
        return "Give more stones";
    }

    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0)
            return new Candidate_ec22894();
        for (Candidate c : candidates) {
            if (c.getSlogan().equals("Give more stones")) {
                return c;
            }
        }
        for (Candidate c : candidates) {
            if (c.getName().equals("Luffy")) {
                return c;
            }
        }
        Random random = new Random();
        int candidate = random.nextInt(candidates.length);
        return candidates[candidate];
    }

    public Candidate selectWinner(Candidate[] votes) {

        int highest = 0;
        Candidate winner = votes[0];
        for (Candidate c : votes) {
            int count = 0;
            for (Candidate a : votes) {
                if (a == c) {
                    count++;
                }
            }
            if (count >= highest) {
                highest = count;
                winner = c;
            }
        }
        return winner;
    }
}
