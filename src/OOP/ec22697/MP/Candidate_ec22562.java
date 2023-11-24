package OOP.ec22697.MP;// File Candidate_ec22562.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22562 extends Candidate {

    public String getName() {
        return "John Snow";
    }

    public String getSlogan() {
        return "You know nothing John Snow";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22562();

        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i].getName().equals("Homer Simpson")) {
                return candidates[i];
            } else if (candidates[i].getSlogan().equals("duh")) {
                return candidates[i];
            }
        }

        return r;
    }

    public Candidate selectWinner(Candidate[] votes) {

        if (votes.length == 0) {
            return new Candidate_ec22562();
        }

        // Defaults
        Candidate winner = votes[0];
        int largest = 0;
        int count = 0;

        for (int i = 0; i < votes.length; i++) {
            // counts how many votes there are for this candidate
            for (int j = 0; j < votes.length; j++) {
                if (votes[i] == votes[j]) {
                    count++;
                }
            }
            // Checks if this candidate has the most votes
            if (count > largest) {
                winner = votes[i];
                largest = count;
            }
            // Resets the count
            count = 0;
        }
        // Returns the winning candidate
        return winner;
    }

    public static String getString(String message) {
        // returns string entered by the user
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();

    }

    public static char getCharacter(String message) {
        // returns string entered by the user
        Scanner scanner3 = new Scanner(System.in);
        System.out.println(message);
        return scanner3.nextLine().charAt(0);

    }

    public static int getInteger(String message) {
        Scanner scanner2 = new Scanner(System.in);
        System.out.println(message);
        return Integer.parseInt(scanner2.nextLine());
    }

    public static void main(String[] args) {
        Candidate_ec22562 cand = new Candidate_ec22562();
        // Makes array of all candidates by calling A3 method
        Candidate[] allCandidates = A3.getCandidateArray();
        // New empty array with length equal to all the candidates
        Candidate[] election = new Candidate[allCandidates.length];
        // This will count the next available index in election array
        int counter = 0;
        // Empty string will be used later
        String username = "";

        // exit is false by default and menu is 0 by default
        boolean exit = false;
        char menu = '0';

        // Runs while exit is false
        while (!exit) {
            Candidate chosenCandidate;
            // Gets the users input from the menu
            menu = getCharacter(
                    "Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit?");
            switch (menu) {
                case 'a':
                    // If a selected, get the candidate defined by that username's class and add to
                    // the election array
                    username = getString("Please enter a username:");
                    Candidate newCandidate = A3.getByUsername(username, allCandidates);
                    election[counter] = newCandidate;
                    // Increase index by 1
                    counter++;
                    // Display all current candidates in election
                    displayCandidates(election, counter);
                    break;
                case 'b':
                    // Generate a random number between 0 and the length of the candidates array
                    Random randInt = new Random();
                    int randomInt = randInt.nextInt(allCandidates.length);
                    // Add that candidate to the election array
                    election[counter] = allCandidates[randomInt];
                    // Increase index by 1
                    counter++;
                    // Display all current candidates in election
                    displayCandidates(election, counter);
                    break;
                case 'c':
                    // Gets the instance of Candidate whose selectWinner method will be called
                    username = getString("Who should count the votes?:");
                    try {
                        chosenCandidate = A3.getByUsername(username, allCandidates);
                    }
                    catch (Exception e){
                        chosenCandidate = A3.getByUsername("ec22562", allCandidates);
                    }
                    // Calls the method to collect the votes
                    cand.runningVote(allCandidates, election, counter,  chosenCandidate);
                    exit = true;
                    break;
                case 'd':
                    exit = true;
                    break;
                default:
                    // If an invalid option is chosen
                    System.out.println("Option '" + menu + "' not a valid option.");
                    break;
            }
        }
    }

    public static void displayCandidates(Candidate[] candidateList, int counter) {
        // Displays all the candidates in the current election
        System.out.println("The Candidates are: ");
        for (int i = 0; i < counter; i++) {
            System.out.println(candidateList[i].getName() + ": " + candidateList[i].getSlogan());
        }
        System.out.println("End of List");

        return;
    }
    
    public void repeatRunningVote(int repeat, Candidate[] allCandidates, Candidate[] election, int counter, String username) {
        
    }
    
    public void runningVote(Candidate[] allCandidates, Candidate[] election, int counter, Candidate chosenCandidate) {
        // Creates a new array to be identical to the election array, but no null
        // elements
        Candidate[] electionNew = new Candidate[counter];

        // Copies the elements in the election array that have candidates
        for (int i = 0; i < counter; i++) {
            electionNew[i] = election[i];
        }

        // Creates a new array with length equal to the number of elections multiplied
        // by the number of candidates
        Candidate[] votes = new Candidate[counter];

            // Counts each vote for each candidate
            for (int i = 0; i < counter; i++) {
                try {
                if (election[i] != null) {
                    votes[i] = allCandidates[i].vote(electionNew);
                }
                } catch (Exception e) {
                     //If vote doesn't work, vote for instance of my class
                   votes[i] = new Candidate_ec22562();
                }
            }
        // Stores instance of the winning candidate
        Candidate winner = chosenCandidate.selectWinner(votes);
        // Outputs the winner to the user
        System.out.println("Most common winner is: " + winner.getName());
    }
}

