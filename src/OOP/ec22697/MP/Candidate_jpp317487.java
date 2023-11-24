package OOP.ec22697.MP;// File Candidate_jpp317487.java
//
// Assignment 3

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Candidate_jpp317487 extends Candidate {

    public static String inputString(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }

    //returns true/false for an yes or no input.
    public static boolean yesOrNoInput(String message) {
        String userInput = inputString(message);
        while (!(userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("no"))) {
            userInput = inputString(message);
        }
        if (userInput.equalsIgnoreCase("yes")) {
            return true;
        } else {
            return false;
        }
    }

    //returns true if passed char is int, else returns false.
    public static boolean isCharInt(char charInput) {
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (int i = 0; i < digits.length; i++) {
            if (charInput == digits[i]) {
                return true;
            }
        }
        return false;
    }

    //returns true if a string is an integer, else returns false.
    public static boolean isStringInt(String stringInput) {
        for (int i = 0; i < stringInput.length(); i++) {
            if (!isCharInt(stringInput.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    //prints a passed string and returns the user input string.
    public static char inputChar(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.next().charAt(0);
    }

    //prints a passed string and returns the user input integer.
    public static int input_Int(String message) {
        String input = inputString(message);
        while (!isStringInt(input)) {
            input = inputString(message);
        }
        return Integer.parseInt(input);
    }

    public static int voteCounter(Candidate c, Candidate[] totalVotes) {
        int count = 0;

        for (int i = 0; i < totalVotes.length; i++) {
            if (totalVotes[i] == c) {
                count++;
            }
        }
        return count;
    }

    public String getName() {
        return "Finngolfinn";
    }

    public String getSlogan() {
        return "24 pack!";
    }

    public Candidate vote(Candidate[] candidates) {
        // If array is empty, return instance of this class.
        if (candidates.length == 0) {
            return new Candidate_jpp317487();
        }

        //searching for a friend
        for (Candidate c : candidates) {
            if (c.getName().equals("Finn")) {
                return c;
            }
        }
        // Otherwise, search for a like minded people.
        for (Candidate c : candidates) {
            if (c.getName().equals("Moby")) {
                return c;
            }
        }
        //otherwise return the last item in the candidate array
        return candidates[candidates.length - 1];
    }

    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_jpp317487();

        if (votes.length == 0) {
            return new Candidate_jpp317487();
        }
        Candidate currentWinner = votes[0];

        int highestCount = 0;

        for (int i = 0; i < votes.length; i++) {
            if ((voteCounter(votes[i], votes)) > highestCount) {
                highestCount = (voteCounter(votes[i], votes));
                currentWinner = votes[i];
            }
        }
        return currentWinner;
    }

    //prints a list of ongoing conidates for the election.
    public static void printCandidates(ArrayList<Candidate> activeCandidates) {
        //Prints all the candidates in the current election
        System.out.println("Candidates are: ");
        for (int i = 0; i < activeCandidates.size(); i++) {
            System.out.println(activeCandidates.get(i).getName() + " with their slogan: " + activeCandidates.get(i).getSlogan());
        }
        return;
    }

    //Picks a random canidate and adds it to the list.
    public static void randomCandidate(Candidate[] allCandidates, ArrayList<Candidate> activeCandidates) {
        Random random = new Random();
        int randomPosition = random.nextInt(allCandidates.length);
        activeCandidates.add(allCandidates[randomPosition]);
        printCandidates(activeCandidates);
    }

    //takes a username as a string input and adds it as a candidate to the list.
    public static void addCandidate(Candidate[] allCandidates, ArrayList<Candidate> activeCandidates) {
        String username = inputString("Please enter candidate username:");
        if ((A3.getByUsername(username, allCandidates)) == null) {
            System.out.println("This candidate does not exist.");
        } else {
            activeCandidates.add(A3.getByUsername(username, A3.getCandidateArray()));
            printCandidates(activeCandidates);
        }
    }


    //picks from the list of active candiadates and follows the election system of a selected candidate.
    public static Candidate runElection(Candidate[] allCandidates, ArrayList<Candidate> activeCandidates) {
        String voteCounterUsername = inputString("Who should count the votes?");
        boolean validVoteCounter = false;

        //checks that the user entered for voting exists
        while (!validVoteCounter) {
            if ((A3.getByUsername(voteCounterUsername, allCandidates)) == null) {
                System.out.println("This candidate does not exist.");
                voteCounterUsername = inputString("Who should count the votes?");
            } else {
                validVoteCounter = true;
            }
        }

        Candidate voteCounter = (A3.getByUsername(voteCounterUsername, allCandidates));

        int numOfElections = input_Int("How many times shall we run the votes?");

        Candidate[] activeCandidatesArray = new Candidate[activeCandidates.size()];
        for (int i = 0; i < activeCandidates.size(); i++) {
            activeCandidatesArray[i] = activeCandidates.get(i);
        }

        Candidate[] electionWinners = new Candidate[numOfElections];


        //loops for the given number of elections and for each election loops through all candidates taking in their votes
        for (int i = 0; i < numOfElections; i++) {
            Candidate[] votes = new Candidate[allCandidates.length];
            for (int j = 0; j < allCandidates.length; j++) {
                try {
                    votes[j] = (allCandidates[j].vote(activeCandidatesArray));
                } catch (Exception ignored) {
                }
            }
            electionWinners[i] = (voteCounter.selectWinner(votes));
        }

        Candidate winner = findWinner(electionWinners);
        System.out.println("Winner is " + winner.getName());
        return winner;
    }

    //given an array of Candidates, returns the most frequent.
    public static Candidate findWinner(Candidate[] electionWinners) {
        int maxCount = 0;
        Candidate mostFrequentCandidate = null;

        for (int i = 0; i < electionWinners.length; i++) {
            int count = 0;
            for (int j = 0; j < electionWinners.length; j++) {
                if (electionWinners[i] == electionWinners[j]) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mostFrequentCandidate = electionWinners[i];
            }
        }
        return mostFrequentCandidate;
    }

    public static void main(String[] args) {

        Candidate[] allCandidates = A3.getCandidateArray();
        ArrayList<Candidate> activeCandidates = new ArrayList<Candidate>();
        boolean end = false;

        System.out.println("= Running Repeated Elections =");

        while (!end) {
            char userChoice = inputChar("Would you like to a) add a specific candidate b) candidate at random c) run the election?");
            if (userChoice == 'a' || userChoice == 'A') {
                addCandidate(allCandidates, activeCandidates);
            } else if (userChoice == 'b' || userChoice == 'B') {
                randomCandidate(allCandidates, activeCandidates);
            } else if (userChoice == 'c' || userChoice == 'C') {
                runElection(allCandidates, activeCandidates);
                end = yesOrNoInput("Would you like to exit the program? (yes/no)");
            }
        }

    }
}
