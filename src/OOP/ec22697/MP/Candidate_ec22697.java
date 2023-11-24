package OOP.ec22697.MP;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Candidate_ec22697 extends Candidate {
    
    public static void main(String[] args) {
        askChoice();
    }

    // Asks the user a question and returns their answer
    public static String askQ(String question)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question);
        
        return scanner.nextLine();
    }

    // Prints the given string
    public static void print(String s) {
        System.out.println(s);
        return;
    }

    // Asks user for their choice
    public static void askChoice() {
        boolean valid = false;

        while (!valid) {
            print("Would you like to");
            print("a) exit");
            print("b) run same election many times"); //They should only be ablle to do a and b
            print("c) check who counts honestly");
            print("d) run all possible two-candidate elections");
            print("e) run a higher-order election");
            print("f) ...\n");

            String choice = askQ("");

            if (choice.equalsIgnoreCase("a")) {
                valid = true; // a doesn't need its own method
            } else if (choice.equalsIgnoreCase("b")) {
                choiceB();
                valid = true;
            } else if (choice.equalsIgnoreCase("c")) {
                return;
            } else if (choice.equalsIgnoreCase("d")) {
                return;
            } else if (choice.equalsIgnoreCase("e")) {
                return;
            }
            else if (choice.equalsIgnoreCase("f")) {
                return;
            }
            else {
                print("Sorry. Please enter a, b, c, d, e or f.");
            }
        }
        return;
    }

    // Prints all the candidates currently in an election
    public static void printElectionCandidates(List<Candidate> electionCandidates, int candidatesAdded) {
        if (electionCandidates.size() == 0) {
            print("None");
            return;
        }
        else {
            for (int i = 0; i < electionCandidates.size(); i++) {
                print((i+1) + ". " + electionCandidates.get(i).getName());
            }
        }
        print("");
        return;
    }

    // Allows the user to run an election as many times as they specifiy
    public static void choiceB() {
        Candidate[] allContributions = A3.getCandidateArray();
        final int MAX_NUM_CANDIDATES = allContributions.length;
        boolean valid = false;
        int candidatesAdded = 0;

//        Candidate[] electionCandidates = new Candidate[MAX_NUM_CANDIDATES];
        List<Candidate> electionCandidates = new ArrayList<>();

        print("= Running Repeated Elections =");

        while(!valid) {

            print("\nCandidates are");
            printElectionCandidates(electionCandidates, candidatesAdded);  // should print all the candidates currently in the election

            String choice = askQ("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?\n> ");

            if (choice.equalsIgnoreCase("a") & candidatesAdded < MAX_NUM_CANDIDATES) {
                candidatesAdded = addSpecificCandidate(allContributions, electionCandidates, candidatesAdded); // ADD THIS TO ARRAY
            } else if (choice.equalsIgnoreCase("b") & candidatesAdded < MAX_NUM_CANDIDATES) {
                candidatesAdded = pickRandomCandidate(allContributions, electionCandidates, MAX_NUM_CANDIDATES, candidatesAdded);
            } else if (choice.equalsIgnoreCase("c") & electionCandidates.size() > 0) {
                // c - only return/valid = true here

                int numOfElections = numberOfElections();
                Candidate candidateDoingElection = askCandidateRunElection(allContributions);

                runElection(numOfElections, candidatesAdded, candidateDoingElection, electionCandidates);

                return;
            } else {
                print("\nSorry, that's an invalid choice. Please enter a, b or c.\n");
            }
        }
        return;
    }

    // IT NO LONGER NEEDS INDEXTOADD!!!!!!!!!!! GET RIDD!!!
    // Allows the user to add a specified candidate to an election
    public static int addSpecificCandidate (Candidate[] allContributions, List<Candidate> electionCandidates, int indexToAdd) { // CURRENTLY DOESN'T RETURN A CANDIDATE
        boolean valid = false;
        Candidate userCandidate = allContributions[0]; // Just so java can be happy - name of this is John Cena

        while (!valid) {
            String candidateName = askQ("Please enter a username\n> ");
            userCandidate = A3.getByUsername(candidateName, allContributions);
            
            if (userCandidate == null) print("\nSorry, invalid username. Please try again.");
            else {
                valid = true;
            } 
        }

//        electionCandidates[indexToAdd] = userCandidate;
//
//        indexToAdd += 1;

        electionCandidates.add(userCandidate);

        return indexToAdd;
    }

    // Picks a random candidate for an election
    public static int pickRandomCandidate(Candidate[] allContributions, List<Candidate> electionCandidates, int TOTAL_NUM_CANDIDATES, int indexToAdd) { // SHOULD HAVE RETURN TYPE OF CANDIDATE
        Random random = new Random();

        int index = random.nextInt(TOTAL_NUM_CANDIDATES);

//        electionCandidates[indexToAdd] = allContributions[index];
//
//        indexToAdd += 1;
        electionCandidates.add(allContributions[index]);

        return indexToAdd;
    }

    // Checks if user's input is an int (and greater than 0) and prompts them until it is
    public static int validateIntInput(String question, String answer) {
        boolean validAns = false;
        int intAns = 0;

        while (!validAns) {
            try {
                intAns = Integer.parseInt(answer);
            } catch (Exception e) {
                print("Sorry, please enter an integer greater than 0.");
                answer = askQ(question + "\n> ");
            }

            if (intAns <= 0) {
                print("Sorry. You must enter an integer greater than 0");
                answer = askQ(question + "\n> ");
            } else {
                validAns = true;
            }
        }

        return intAns;
    }

    // Runs an election based on given candidates
    public static void runElection(int numOfTimes, int candidatesAdded, Candidate winnerSelector, List<Candidate> electionCandidates) {
        Candidate[] electionWinners = new Candidate[numOfTimes];

        int numOfWinners = 0;
        int numErrors = 0;
        int voterCount = 0; // used to see if things working
        Candidate[] candidates = A3.getCandidateArray();
        Candidate[] votes = new Candidate[candidates.length];

        for (int i = 0; i < numOfTimes; i++) {
            // call vote method on all the candidates
            for (int j = 0; j < votes.length; j++) {
                try {
                    voterCount += 1;
                    votes[j] = candidates[j].vote(listToArray(electionCandidates));
                    //System.out.println(candidates[j].un + " has votes for " + votes[j].getName());
                } catch (Exception e) {
                    numErrors += 1;
                    System.out.println("Error caused by " + candidates[j].un + ": " + numErrors + "/" + votes.length + "\n\t" + e + "\tVoter num: " + voterCount + "/" + votes.length);
                }
            }

            electionWinners[numOfWinners] = winnerSelector.selectWinner(votes);
            numOfWinners += 1;
            //System.out.println("Number of winners: " + numOfWinners);
        }

        // count most frequent winner + prints
        countMostFrequentW(electionWinners);

        return;
    }

    // Counts the most frequent candidate in an array and prints their name
    public static void countMostFrequentW(Candidate[] winners) {
        
        // if (votes.length == 0) { --- shouldn't be able to have an empty winners array right???
        //     return new Candidate();
        // }
        // else {

        int largestNumOfVotes = 0;
        Candidate mostPopularCandidate = winners[0];

        for (int i = 0; i < winners.length; i++) {
            int numberOfVotes = 0;

            for (int j = 0; j < winners.length; j++) {
                if (winners[j] == winners[i]) {
                    numberOfVotes += 1;
                }
            }
            //System.out.println("Currently the most popular candidate is " + mostPopularCandidate.getName());

            if (numberOfVotes > largestNumOfVotes) {
                largestNumOfVotes = numberOfVotes;
                mostPopularCandidate = winners[i];
            }
        }


        try {
            print("Most common winner is " + mostPopularCandidate.getName());
            print("There were no other winners.");

        }
        catch (Exception e) {
            print("Sorry something went wrong");
        }

        // what to do if more than 1 winner?
        //}
        
        return;
    }

    // Asks the user to name a candidate to run an election
    public static Candidate askCandidateRunElection(Candidate[] allContributions) {
        boolean valid = false;
        Candidate c = allContributions[0]; // yet again to make java happy

        while (!valid) {
            String candidateName = askQ("Who should count the votes?\n> ");
            c = A3.getByUsername(candidateName, allContributions);
            
            if (c == null) print("\nSorry, invalid username. Please try again.");
            else {
                valid = true;
            } 
        }

        return c;
    }



    // Asks the user how many times they want to run an election
    public static int numberOfElections() {
        print("How many times shall we run the election?");
        String choice = askQ("> "); // make new method to validate ints

        int intChoice = validateIntInput("How many times shall we run the election?", choice);

        return intChoice;
    }

    public String getName() {
        return "Palpatine";
    }
    
    public String getSlogan() {
        return "Vote for me - I AM the Senate after all";
    }
    
    // Votes for Palpatine - who said liberty is dead anyway?
    public Candidate vote(Candidate[] candidates) {
        Candidate emperor = new Candidate_ec22697(); 

        return emperor;
    }
    
    // Counts the number of candidates which begin with a C and selects the candidate found at that position in the votes array as the winner
    // Or it just returns Palpatine as the winner if the votes array is empty
    public Candidate selectWinner(Candidate[] votes) {
        Candidate winner = new Candidate_ec22697();
        if (votes.length == 0) return winner;

        int numberOfCs = 0;
        
        for (int i = 0; i < votes.length; i++) {
            if (votes[i].getName().charAt(0) == 'C' | votes[i].getName().charAt(0) == 'c') {
                numberOfCs += 1;
            }
        }

        Random random = new Random();
        int index = random.nextInt(votes.length);
//        winner = votes[numberOfCs];
        // just so it's easier to check that this method works
        winner = votes[index];
        
        return winner;
    }

    // Used to turn electionCandidates to an array for use in .vote()
    private static Candidate[] listToArray (List<Candidate> elecCandidates) {
        Candidate[] cArray = new Candidate[elecCandidates.size()];
        for (int i = 0; i < elecCandidates.size(); i++) {
//            System.out.println("i is " + i);
            cArray[i] = elecCandidates.get(i);
        }

        return cArray;
    }
    
}
