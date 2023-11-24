package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;

// Subclass of Candidate that represents a specific candidates
class Candidate_ec22406 extends Candidate {

    // Returns the candidate's name
    public String getName() {
        return "Big Smoke";
    }

    // Returns the candidate's slogan
    public String getSlogan() {
        return "A number 9 large";
    }

    // Determines the candidate that matches the candidate's slogan
    public Candidate vote(Candidate[] candidates) {
        for (Candidate c : candidates) {
            if (c.getSlogan().equals("A number 9 large")) {
                return c;
            }
        }
        for (Candidate c : candidates) {
            if (c.getSlogan().equals("Here we go again")) {
                return c;
            }
        }
        return candidates[candidates.length-1];
    }

    // Determines the winner of the election based on votes cast
    public Candidate selectWinner(Candidate[] votes) {
        Candidate currentWinner = null;
        int highestCount = 0;
        for (Candidate c : votes) {
            int count = 0;
            for (Candidate x : votes) {
                if (x == c) {
                    count++;
                }
            }
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        return currentWinner == null ? new Candidate_ec22406 () : currentWinner;
    }

    // Gets an integer from the user, with validation for positive integers
    public static int inputInt(String message) {
        Scanner sc = new Scanner(System.in);
        int number;
        while (true) {
            System.out.println(message);
            if (sc.hasNextInt() && (number = sc.nextInt()) >= 0) {
                return number;
            } else {
                System.out.println("That integer isn't positive.");
                sc.next();
            }
        }
    }

    // Gets a string from the user
    public static String inputString(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextLine();
    }

    // Prints a message to the console
    public static void print(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        final Candidate[] allCandidates = A3.getCandidateArray();

        // Prompt user for number of candidates in the election
        int numberOfCandidates = inputInt("How many candidates will there be in this election?");

        // Create an array to hold the candidates
        Candidate[] candidateArray = new Candidate[numberOfCandidates];

        // Keep track of how many candidates have been added to the array
        int candidatesAdded = 0;

        // Flag to indicate if the program should continue running
        boolean running = true;

        // Main loop for adding candidates and running the election
        while (running) {
            String input = inputString("What would you like to do?\n a) add a specific candidate\n b) add a candidate at random\n c) run the election");

            // Check if the user wants to add a candidate
            if (candidatesAdded < numberOfCandidates) {
                if (input.equalsIgnoreCase("a")) {
                    candidateArray[candidatesAdded++] = getSpecificCandidate();
                } else if (input.equalsIgnoreCase("b")) {
                    candidateArray[candidatesAdded++] = getRandomCandidate();
                }

                // Check if the user wants to run the election
            } else if (input.equalsIgnoreCase("c")) {
                printCandidates(candidateArray);

                // Prompt user for number of times to run the election
                int runCount = inputInt("How many times shall we run the election?");

                // Prompt user for candidate who should count the votes
                String counterInput = inputString("Who should count the votes?");
                Candidate counter = A3.getByUsername(counterInput, allCandidates);

                // Create an array to hold the winners of each election
                Candidate[] winners = new Candidate[runCount];

                // Run the election the specified number of times
                for (int i = 0; i < runCount; i++) {
                    winners[i] = getWinner(candidateArray, counter);
                    System.out.println("The winner of election " + (i + 1) + " is " + winners[i].getName());
                }

                // Determine the most popular winner
                Candidate winner = popularWinner(winners);
                System.out.println("The most popular winner is " + winner.getName() + "!");

                // Exit the main loop
                running = false;

                // Check if the election is full
            } else if (candidatesAdded == numberOfCandidates) {
                System.out.println("Sorry. The election is now full. You can't add anymore candidates.");

                // User input is invalid
            } else {
                System.out.println("That isn't a valid choice.");
            }
        }
    }

    // Gets the winner of the election based on the votes cast
    public static Candidate getWinner(Candidate[] candidates, Candidate counter) {
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] voteArray = new Candidate[allCandidates.length];
        for (int i = 0; i < allCandidates.length; i++) {
            try {
                voteArray[i] = allCandidates[i].vote(candidates);
            } catch (Exception ignored) {}
        }
        return counter.selectWinner(voteArray);
    }

    // Prints the list of candidates in the election
    public static void printCandidates(Candidate[] candidates) {
        System.out.println("The candidates in the election are as follows:");
        for (Candidate candidate : candidates) {
            System.out.println(candidate.getName());
        }
    }

    // Gets a specific candidate from the user
    public static Candidate getSpecificCandidate() {
        String candidateName = inputString("Please enter the candidate username:");
        Candidate newCandidate = A3.getByUsername(candidateName, A3.getCandidateArray());
        if (newCandidate == null) {
            System.out.println("The candidate could not be found.");
        } else {
            System.out.println("The candidate was found.");
        }
        return newCandidate;
    }

    // Gets a random candidate
    public static Candidate getRandomCandidate() {
        Random rand = new Random();
        Candidate[] allCandidates = A3.getCandidateArray();
        return allCandidates[rand.nextInt(allCandidates.length)];
    }

    // Determines the candidate with the most votes
    public static Candidate popularWinner(Candidate[] votes) {
        Candidate currentWinner = null;
        int highestCount = 0;
        for (Candidate c : votes) {
            int count = 0;
            for (Candidate x : votes) {
                if (x == c) {
                    count++;
                }
            }
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        return currentWinner == null ? new Candidate_ec22406 () : currentWinner;
    }
}
