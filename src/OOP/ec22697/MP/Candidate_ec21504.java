package OOP.ec22697.MP;// File Candidate_ec21504.java
//
// Machine generated stub for Assignment 2

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

class Candidate_ec21504 extends Candidate {

    public String getName() {
        return "Piotr";
    }

    public String getSlogan() {
        return "BTW, I use Arch!";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate toVoteFor = new Candidate_ec21504();

        if (candidates.length == 0) {
            return toVoteFor;
        }

        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.

        for (Candidate c : candidates) {
            if (c.getName().equals("Piotr")) {
                return c;
            }
        }
        return toVoteFor;
    }

    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec21504();
        int largest = 0;

        if (votes.length == 0) {
            return r;
        }

        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.

        for (Candidate c : votes) {
            int currentCount = 0;

            for (Candidate d : votes) {
                if (c == d) {
                    currentCount++;
                }
            }

            if (currentCount > largest) {
                largest = currentCount;
                r = c;
            }
        }

        return r;
    }

    //This functions checks if the input string is an integer.
    public static boolean isNumeric(String input) {
        if (input == null)
            return false;
        if (input.length() == 0)
            return false;
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i)))
                return false;
        }
        return true;
    }

    //This function takes a message as a parameter and takes a string input from a user
    public static String inputString(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    //This function takes a message as a parameter and takes an int input from a user
    public static int inputInt(String message) {
        boolean correctInput = false;
        String input = "";

        while (!correctInput) {
            input = inputString(message);
            if (isNumeric(input)) {
                correctInput = true;
            } else {
                System.out.println("The input is incorrect, please enter a valid number");
            }
        }
        return Integer.parseInt(input);
    }

    public static int inputIntRange(String message, int min, int max) {
        boolean correctInput = false;
        String input = "";

        while (!correctInput) {
            input = inputString(message);
            if (isNumeric(input)) {
                int number = Integer.parseInt(input);
                if (number >= min && number <= max) {
                    correctInput = true;
                }
            } else {
                System.out.println("The input is incorrect, please enter a valid number");
            }
        }
        return Integer.parseInt(input);
    }

    public static char selectOption(char[] legalSelections) {
        while (true) {
            System.out.println("Would you like to a) add a specific candidate b) run the election? c) exit");
            String rawSelection = inputString("");

            for (char c :
                    legalSelections) {
                if (c == rawSelection.charAt(0)) {
                    return c;
                }
            }

            System.out.println("INVALID SELECTION! Please enter a valid selection.\n");
        }
    }

    public static boolean booleanReRunSelectOption() {
        char[] legalSelections = {'y', 'n', 'Y', 'N'};

        while (true) {
            System.out.println("Would you like to rerun the election y/N: ");
            String rawSelection = inputString("");

            for (char c :
                    legalSelections) {
                if (c == rawSelection.charAt(0)) {
                    if (c == 'y' || c == 'Y'){
                        return true;
                    }
                    if (c == 'n' || c == 'N'){
                        return false;
                    }
                }
            }

            System.out.println("INVALID SELECTION! Please enter a valid selection.\n");
        }
    }

    public static void main(String[] args) {
        List<Candidate> votes = new ArrayList<>();

        char[] legalOptions = {'a', 'b', 'c'};

        Candidate[] possibleCandidates = A3.getCandidateArray();
        int maxRange = possibleCandidates.length-1;

        while (true) {
            char selected = selectOption(legalOptions);
            if (selected == 'a'){
                int opt = inputIntRange("Select a candidate from 0 - " + maxRange + ": ", 0, maxRange);
                votes.add(possibleCandidates[opt]);
                continue;
            }
            if (selected == 'b'){
                List<Candidate> winners = new ArrayList<>();
                boolean rerun = false;
                int reruns = 1;

                int opt = inputIntRange("Select a candidate from 0 - " + maxRange + " whose election function should be used: ", 0, maxRange);
                Candidate[] temp = votes.toArray(new Candidate[0]);

                System.out.print("Would you like to rerun the election y/N: ");
                rerun = booleanReRunSelectOption();

                System.out.println();

                if (rerun)
                    reruns = inputIntRange("How many times would you like to rerun the election? 2 - 10000: ", 2, 10000);

                for (int i = 0; i < reruns; i++) {
                    Candidate winner = possibleCandidates[opt].selectWinner(temp);
                    winners.add(winner);
                }

                if (rerun){

                    int highest = 0;
                    for(int i = 0; i < winners.size(); i++) {
                        int count = 0;
                        Candidate holder = winners.get(i);

                        for(Candidate candidate : winners) {
                            if(candidate == holder) {
                                count++;
                            }
                        }

                        if(count > highest)
                            highest = count;
                    }

                    String ending = " vote";

                    if (highest > 1){
                        ending = " votes";
                    }

                    System.out.println("The most common winner is: " + winners.get(highest).getName() + " with a whopping " + highest + ending + "!");
                }

                if (!rerun){
                    String name = winners.get(0).getName();
                    System.out.println("The winner is: " + name + ". Congratulations!");
                    continue;
                }


            }
            if (selected == 'c') {
                System.out.println("Thank you for using my Election Program. Goodbye");
                return;
            }
        }
    }
}
