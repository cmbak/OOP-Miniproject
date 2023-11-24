package OOP.ec22697.MP;// File Candidate_ec22435.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22435 extends Candidate {
    
    public String getName() {
        return "Rohan";
    }
    
    public String getSlogan() {
        return "Thats what she said";
    }
    
    //Takes input from the user and returns it as a string
    public static String inputText() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    //Prints string
    public static void printString(String message){
        System.out.println(message);
        return;
    }

    //Prints all current candidates
    public static void printCandidates(Candidate[] allCandidates){
        printString("Candidates are: ");
        
        if (allCandidates.length == 0){
            printString("None");
            return;
        }

        for(int i = 0; i < allCandidates.length; i++){
            printString(i + ". " + allCandidates[i]);
        }

        return;
    }


    public static void optionA(Candidate[] allCandidates, Candidate[] candidateArray, int candidateCount){
        printString("Please enter a username: ");
        String username = inputText();

        Candidate newCandidate = A3.getByUsername(username, allCandidates);
        
        candidateArray[candidateCount] = newCandidate;
        candidateCount += 1;
    }

    public static void optionB(Candidate[] allCandidates, Candidate[] candidateArray, int candidateCount) {
        Random random = new Random();

        boolean found = false;

        while (!found){
            found = false;

            int randomInt = random.nextInt(allCandidates.length);
            Candidate c = allCandidates[randomInt];
            
            for (int i = 0; i < candidateCount; i++){
                if (c == candidateArray[i]){
                    found = true;
                }
            }

            if (found == false){
                candidateArray[candidateCount] = c;
                candidateCount += 1;
            }
        }
    }

    public static void runElection(Candidate[] allCandidates, Candidate[] candidateArray, int candidateCount) {
        printString("Who should count the votes? ");
        String chosen = inputText();

        Candidate candidateCounter = A3.getByUsername(chosen, allCandidates);
        
        printString("How many times should we run the election? ");
        int howMany = Integer.parseInt(inputText());

        Candidate[] votes = new Candidate[candidateArray.length]; 

        for (int i = 0; i < candidateCount; i++){
            votes[i] = candidateArray[i];
        }

        Candidate[] winner = new Candidate[howMany];
        Candidate[] allVotes = new Candidate[votes.length];

        for (int i = 0; i < howMany; i++){
            for (int j = 0; j < allCandidates.length; j++){
                allVotes[j] = allCandidates[j].vote(votes);
            }
            winner[i] = candidateCounter.selectWinner(allVotes);
        }

        Candidate finalWinner = candidateCounter.selectWinner(winner);
        printString("The winner is " + finalWinner.getName() + " with the slogan " + finalWinner.getSlogan());
         
    }
  

    public static void  main(String[] args) {
        //Create array of all candidates
        Candidate[] allCandidates = A3.getCandidateArray();

        //New array that is same size as candidates array
        Candidate[] candidateArray = new Candidate[allCandidates.length];

        int candidateCount = 0;
        String input = "";
        
        printString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        input = inputText();
        
        if((input.toLowerCase()).equals("a")){
            optionA(allCandidates, candidateArray, candidateCount);
            printCandidates(candidateArray);
        }

        else if((input.toLowerCase()).equals("b")){
            optionB(allCandidates, candidateArray, candidateCount);
            printCandidates(candidateArray);
        }
        
        else if((input.toLowerCase()).equals("c")){
            runElection(allCandidates, candidateArray, candidateCount);
        }
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22435();    
        if (candidates.length == 0){
            return new Candidate_ec22922();
         }         
         else {
            for (Candidate c : candidates){
                if ((c.getSlogan()).equals((r.getSlogan())));{
                    return c;
                }
            }
        }
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
        return candidates[candidates.length];
    }

    
    public Candidate selectWinner(Candidate[] votes) {

        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.

        if (votes.length == 0){
            return new Candidate_ec22922();
        }

        Candidate currentWinner = votes [0];

        int highestCount = 0;
        for (Candidate c : votes){
            int count = 0;
            for (Candidate x : votes){
                if (x == c) {
                    highestCount = count;
                    currentWinner = c;
                }
            }
        }
        
        return currentWinner;
    }
    
}
