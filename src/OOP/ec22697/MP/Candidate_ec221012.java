package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

// File Candidate_ec22974.java
// Machine generated stub for Assignment 2

class Candidate_ec221012 extends Candidate {

    public String getName() {
        return "Jia Jing Teh";
    }

    public String getSlogan() {
        return "More Money";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec221012();

        for (Candidate c: candidates){
            if (c.getName().equals("Mr. Bean") && c.getSlogan().equals("Teddy!")) {
            return c;}
            }

        return r;
    }

    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec221012();

        if (votes.length != 0) {
            int highestCount = 0;
            for (Candidate a: votes) {
                int count = 0;
                for (Candidate b: votes) {
                    if (b == a) count++;
                }
                if (count > highestCount) { 
                    highestCount = count; 
                    r = a;
                } 
            }
        }
        return r;
    }


    public static char inputChar(String question) {
        Scanner ask = new Scanner(System.in);
        char answer;

        System.out.println(question);
        answer = ask.next().charAt(0);

        return answer;
    }

    public static String inputString(String question) {
        Scanner ask = new Scanner(System.in);
        String answer;

        System.out.println(question);
        answer = ask.nextLine();

        return answer;
    }

    public static void main(String[] args) {
        // Makes array of all candidates by calling A3 method
        Candidate[] allCandidates = A3.getCandidateArray();
        // New empty array with length equal to all the candidates
        Candidate[] election = new Candidate[allCandidates.length];
        // This will count the next available index in election array
        int counter = 0;
        // Empty string for username
        String username = "";

        // exit is false by default and menu is 0 by default
        boolean end = false;
        char menu = 'x';

        while (!end) {
            // Gets the users input from the menu
            menu = inputChar("Do you wish to:\na) add a candidate to the election\nb) add a random candidate to the election\nc) run the election");
            switch (menu) {
                case 'a':
                    username = inputString("Please enter the candidate's username:");
                    Candidate newCandidate = A3.getByUsername(username, allCandidates);
                    election[counter] = newCandidate;
                    counter++;
                    break;
                case 'b':
                    // Generate a random number between 0 and the length of the candidates array
                    Random r = new Random();
                    int randomInt = r.nextInt(allCandidates.length);
                    // Add that candidate to the election array
                    election[counter] = allCandidates[randomInt];
                    // Increase index by 1
                    counter++;
                    break;
                case 'c':
                    // Gets the instance of Candidate whose selectWinner method will be called
                    username = inputString("Enter username of the person who should count the votes:");
                    Candidate chosenCandidate = A3.getByUsername(username, allCandidates);
                    // Asks the user how many times they would like to run the election
                    int loop = Integer.parseInt(inputString("How many times should we run the election?"));
                    // Calls the method to collect the votes
                    runElection(election, counter, loop, chosenCandidate);
                    // Once completed, exit
                    end = true;
                    break;

                default:
                    // If an invalid option is chosen
                    System.out.println("Please enter a valid option.");
            }
            // Display all current candidates in election
            displayCandidates(election, counter);
        }
    }

    public static void displayCandidates(Candidate[] candidates, int counter) {
        // Displays all the candidates in the current election
        System.out.println("Candidates in the election: ");
        for (int i = 0; i < counter; i++) {
            System.out.println((i+1) + ". " + candidates[i].getName() + ": " + candidates[i].getSlogan());
        }
        return;
    }

    public static void runElection(Candidate[] election, int counter, int loop, Candidate chosenCandidate) {        
        // Creates a new array with length equal to the number of elections multiplied
        // by the number of candidates
        Candidate[] votes = new Candidate[loop * (counter - 1)];

        // Repeat as many times as stated by the user
        for (int i = 0; i <= loop; i++) {
            // Counts each vote for each candidate
            for (int j = 0; j < counter; j++) {
                try {
                    votes[(counter * i) + j] = election[j].vote(election);
                } 
                catch (Exception e) {
                    // If vote doesn't work, vote for instance of my class
                    votes[i] = new Candidate_ec22974();
                }
            }
        }

        // Stores instance of the winning candidate
        Candidate winner = chosenCandidate.selectWinner(votes);
        // Outputs the winner to the user
        System.out.println("WINNER: " + winner.getName());
    }
}
