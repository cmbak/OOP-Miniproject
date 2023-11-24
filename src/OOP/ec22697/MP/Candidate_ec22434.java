package OOP.ec22697.MP;// File Candidate_ec22434.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22434 extends Candidate {
    public static void main(){
    }

    public String getName() {
        return "Jack";
    }
    
    public String getSlogan() {
        return "Hello World!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22434();
        
        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Hello World!"))
                return c;
        
        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Random Person"))
                return c;
        
        // Otherwise, return the last on the list
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22434();
        
        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];
        
        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {
            
            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        return currentWinner;
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

    public static Candidate[] getCandidateArray() {
        Candidate[] CandidateList = new Candidate[5];

        for (int i = 0; i < 5; i++) {
            CandidateList[i] = new Candidate_ec22434();
        }

        return CandidateList;
    }

    public static void main(String[] args) {
        // Makes array of all candidates by calling A3 method
        Candidate[] allCandidates = getCandidateArray();
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
        // Gets the users input from the menu
            menu = getCharacter("add a specific candidate b) add a candidate at random c) run the election?");
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
                    Candidate chosenCandidate = A3.getByUsername(username, allCandidates);
                    // Asks the user how many times they would like to run the election
                    int repeat = getInteger("How many times should we run the election?");
                    // Calls the method to collect the votes
                    runningVote(election, counter, repeat, chosenCandidate);
                    // Once completed, exit
                    exit = true;
                    break;

                default:
                    // If an invalid option is chosen
                    System.out.println("Option '" + menu + "' not a valid option.");
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

    public static void runningVote(Candidate[] election, int counter, int repeat, Candidate chosenCandidate) {
        // Creates a new array to be identical to the election array, but no null
        // elements
        Candidate[] electionNew = new Candidate[counter];

        // Copies the elements in the election array that have candidates
        for (int i = 0; i < counter; i++) {
            electionNew[i] = election[counter];

        }

        // Creates a new array with length equal to the number of elections multiplied
        // by the number of candidates
        Candidate[] votes = new Candidate[repeat * (counter - 1)];

        // Repeat as many times as stated by the user
        for (int j = 0; j <= repeat; j++) {
            // Counts each vote for each candidate
            for (int i = 0; i < counter; i++) {
                try {
                    votes[(counter * j) + i] = electionNew[i].vote(electionNew);
                } catch (Exception e) {
                    // If vote doesn't work, vote for instance of my class
                    votes[i] = new Candidate_ec22434();
                }
            }
        }

        // Stores instance of the winning candidate
        Candidate winner = chosenCandidate.selectWinner(votes);
        // Outputs the winner to the user
        System.out.println("Most common winner is: " + winner.getName());
    }
}
