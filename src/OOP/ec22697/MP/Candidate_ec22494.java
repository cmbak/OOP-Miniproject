package OOP.ec22697.MP;// File Candidate_ec22494.java
//
// Machine generated stub for Assignment 3

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22494 extends Candidate {
    
    // Main method
    //
    public static void main(String[] args) {
        
        Candidate[] allCandidates = A3.getCandidateArray(); // gets all the possible candidates
        Candidate[] electedCandidates = new Candidate[0]; // empty array of elected Candidates
        System.out.println(" = Running Repeated Elections = ");
        printArray(electedCandidates, "Candidates are: ");
        
        String choice = inputChoice("Would you like to\n  a) Add a specific candidate\n  b) Add a random candidate\n  c) Run the election\nPlease choose an option: ");
        
        while (!choice.equals("c")) {
            // Choice "a"
            //
            if (choice.equals("a")) {
                Candidate x = null;
                while (x == null) {
                    String candidateUsername = inputString("Please enter a valid username: ");
                    x = A3.getByUsername(candidateUsername, allCandidates);
                }
                electedCandidates = append(electedCandidates, x);
                printArray(electedCandidates, "Candidates are: ");
                
                choice = inputChoice("Would you like to\n  a) Add a specific candidate\n  b) Add a random candidate\n  c) Run the election\nPlease choose an option: ");
            }
            // Choice "b"
            //
            if (choice.equals("b")) {
                Random rand = new Random();
                int random = rand.nextInt(allCandidates.length);
                Candidate r = allCandidates[random];
                electedCandidates = append(electedCandidates, r);
                printArray(electedCandidates, "Candidates are: ");
                
                choice = inputChoice("Would you like to\n  a) Add a specific candidate\n  b) Add a random candidate\n  c) Run the election\nPlease choose an option: ");
            }
        }
        // Choice "c"
        //
        Candidate voteCounter = null;
        while (voteCounter == null) {
            String username = inputString("Who should count the votes: ");
            voteCounter = A3.getByUsername(username, allCandidates);
        }
        int howMany = inputPositiveInt("How many times shall we run the election: ");
        
        runElections(allCandidates, electedCandidates, voteCounter, howMany);
    }
    
    //
    public static void runElections(Candidate[] allCandidates, Candidate[] electedCandidates, Candidate voteCounter, int howMany) {
        
        Candidate[] votes = new Candidate[allCandidates.length];
        for (int x=0; x<allCandidates.length; x++) {
            votes[x] = allCandidates[x].vote(electedCandidates);
        }
        votes = removeNullItems(votes);
        
        Candidate[] winners = new Candidate[0];
        for (int y=0; y<howMany; y++) {
            Candidate currentWinner = voteCounter.selectWinner(votes);
            winners = append(winners, currentWinner);
        }
        
        Candidate[] commonWinners = winners;
        commonWinners = nullifyDuplicates(commonWinners);
        commonWinners = removeNullItems(commonWinners);
        
        printArray(commonWinners, "The most common winners are:");
    }
    
    // Removes null items from the array
    //
    public static String[] removeNullItems(String[] array) {
        int count = 0;
        int skip = 0;
        for (int n = 0; n < array.length; n++) {
            if (array[n] == null) {
                count++;
            }
        }
        String[] tempArray = new String[array.length - count];
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                skip++;
            } else {
                tempArray[i - skip] = array[i];
            }
        }
        return tempArray;
    }
    
    // nullifies dupes
    //
    public static Candidate[] nullifyDuplicates(Candidate[] a) {
    
        // Loop that goes through a.
        for (int i = 1; i < a.length; i++) {
            // Loop that checks earlier elements.
            for (int j = i-1; j >= 0; j--) {
                if (a[i] == (a[j])) {
                    a[i] = null;
                }
            }
        }
        return a;   
    }
    
    // Removes null items from the array
    //
    public static Candidate[] removeNullItems(Candidate[] array) {
        int count = 0;
        int skip = 0;
        for (int n = 0; n < array.length; n++) {
            if (array[n] == null) {
                count++;
            }
        }
        Candidate[] tempArray = new Candidate[array.length - count];
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                skip++;
            } else {
                tempArray[i - skip] = array[i];
            }
        }
        return tempArray;
    }
    
    // Extends array size by 1 and adds item to the array
    //
    public static Candidate[] append(Candidate[] array, Candidate appendedItem) {
        Candidate tempArray[] = new Candidate[array.length + 1];
        for (int n = 0; n < array.length; n++) {
            tempArray[n] = array[n];
        }
        tempArray[array.length] = appendedItem;
        return tempArray;
    }
    
    // Ensures that user inputs either "a" "b" or "c"
    //
    public static String inputChoice(String message) {
        System.out.print(message);
        boolean validAnswer = false;
        String choice = "";
        while (!validAnswer) {
            Scanner input = new Scanner(System.in);
            choice = input.nextLine();
            if (choice.equals("a") || choice.equals("b") || choice.equals("c")) {
                validAnswer = true;
            } else {
                System.out.print("Invalid input try again: ");
            }
        }
        return choice;
    }
    
    // Method prints all the Candidate items in a list
    //
    public static void printArray(Candidate[] array, String message) {
        System.out.println(message);
        if (array.length == 0) {
            System.out.println("None");
        } else {
            for (int n=0; n<array.length; n++) {
                System.out.println((n+1)+") "+array[n].getName()+" with their slogan: "+array[n].getSlogan());
            }
        }
    }
    
    // Method prints a message then ensures the user enters a positive integer
    //
    public static int inputPositiveInt(String message) {
        System.out.print(message);
        int number = 0;
        boolean check = false;
        while (!check) {
            Scanner input = new Scanner(System.in);
            if (input.hasNextInt()) {
                number = input.nextInt();
                input.nextLine();
                if (number > 0) {
                    check = true;
                } else {
                    System.out.print("Not a valid integer, try again: ");
                }
            } else {
                System.out.print("Not an integer, try again: ");
            }
        }
        return number;
    }

    // Method prints a message then returns the users input as a string
    //
    public static String inputString(String message) {
        System.out.print(message);
        Scanner input = new Scanner(System.in);
        String string = input.nextLine();
        return string;
    }
    
    
    
    // Code from A2
    //
    //
    public String getName() {
        return "Wreck-it";
    }
    
    public String getSlogan() {
        return "Ralph";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        Candidate r = new Candidate_ec22494();
        
        for  (Candidate c : candidates) {
            if (c.getName().equals("Mr. Bean") && c.getSlogan().equals("Teddy!")) {
                return c;
            }
        }
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        Candidate r = new Candidate_ec22494();
        
        if (votes.length != 0) {
            int highestCount = 0;
            for (Candidate i : votes) {
                int count = 0;
                for (Candidate j : votes) {
                    if (i == j) {
                        count++;
                    }
                }
                if (count > highestCount) { 
                    highestCount = count; 
                    r = i;
                } 
            }
        }
        return r;
    }
}
