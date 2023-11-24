package OOP.ec22697.MP;// File Candidate_ec221088.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec221088 extends Candidate {

    public String getName() {
        return "Black Panther";
    }

    public String getSlogan() {
        return "Wakanda Forever!";
    }

    public Candidate vote(Candidate[] candidates) {
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0)
            return new Candidate_ec221088();

        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Wakanda Forever!"))
                return c;

        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Kim"))
                return c;

        // Otherwise, default to last candidate on list.
        return candidates[candidates.length - 1];

    }

    public Candidate selectWinner(Candidate[] votes) {

        // If array is empty, return instance of this class.
        if (votes.length == 0) {
            return new Candidate_ec221088();
        }

        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];

        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {
            int count = 0;
            for (Candidate x : votes) {
                if (x == c) {
                    count++;
                }
            }

            if (count >= highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }

        return currentWinner;

    }

    public static String inputString(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        String answer = scanner.nextLine();
        return answer;
    }


    public static int inputInt(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        int answer = Integer.parseInt(scanner.nextLine());
        return answer;
    }

    public static void main(String[] args) {

        Candidate[] all = A3.getCandidateArray();
        Candidate[] newCandidate = new Candidate[all.length];
        String option = "a";
        int length = 0;

        System.out.println("Repeating Elections: ");

        while (!option.equals("d")) {

            System.out.println("Candidates are");

            if (length == 0) {
                System.out.println("None");
            } else {
                for (int i = 0; i < length; i++) {
                    System.out.println("Candidates are");
                    System.out.println((i + 1) + ". " + newCandidate[i].getName());
                }
            }

            option = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) quit?");

            if (option.equals("a")) {
                String username = inputString("Enter the username: ");

                for (int j = 0; j < length; j++) {
                    if (all[j].equals(username)) {
                        newCandidate[length] = all[j];
                    }
                }
                length = length + 1;
            } else if (option.equals("b")) {
                Random random = new Random();
                int x = random.nextInt(all.length);
                newCandidate[length] = all[x];
                length = length + 1;
            } else if (option.equals("c")) {
                String vote_counter = inputString("Who should count the votes?");
                int repeats = inputInt("How many times do you wnat to run the election?");
                Candidate counter = A3.getByUsername(vote_counter, newCandidate);

                Candidate[] winners = new Candidate[repeats];

                for (int i = 0; i < repeats; i++) {
                    winners[i] = counter.selectWinner(newCandidate);
                }
            } else if (option.equals("d")) {
                break;
            } else {
                option = inputString("Options (a, b, c or d):");
            }
            return;
        }
    }
}
