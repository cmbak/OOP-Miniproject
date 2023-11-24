package OOP.ec22697.MP;// File Candidate_ec22718.java
//
// Machine generated stub for Assignment 2

// import random

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22718 extends Candidate {

    public String getName() {
        return "Anya";
    }

    public String getSlogan() {
        return "More fields!";
    }

    public Candidate vote(Candidate[] candidates) {

        // If array is empty, return instance of this class.
        if (candidates.length == 0)
            return new Candidate_ec22718();

        // First search for Utfur on the list of candidates.
        for (Candidate c : candidates)
            if (c.getName().equals("Utfur"))
                return c;

        // Otherwise, choose at random from list.
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }

    public Candidate selectWinner(Candidate[] votes) {

        // If array is empty, return instance of this class.
        if (votes.length == 0)
            return new Candidate_ec22718();

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
            if (count >= highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }

        return currentWinner;
    }

    public static void menuChoice(String option, Candidate[] totalCandidates, Candidate[] candidateVotes, int count){

        if (option.equals("a")){
            // add specific candidate
            addSpecificCandidate(totalCandidates, candidateVotes,count);

        }else if (option.equals("b")){
            // add random candidate
            addRandomCandidate(totalCandidates, candidateVotes,count);
        }else if (option.equals("c")){
            // run election
            newElection(totalCandidates, candidateVotes,count);
        }else{
            menuChoice(option,totalCandidates, candidateVotes,count);
        }
    }
    public static Candidate[] addSpecificCandidate (Candidate[] totalCandidates, Candidate[] candidateVotes, int count) {
        String username = input("Please enter the username. ");
        Candidate newCandidate = A3.getByUsername(username, totalCandidates);

        candidateVotes[count] = newCandidate;
        count++;

        return candidateVotes;
    }


    public static Candidate[] addRandomCandidate (Candidate[] totalCandidates, Candidate[] candidateVotes, int count) {
        Random rand = new Random();
        int random = rand.nextInt(totalCandidates.length);

        candidateVotes[count] = totalCandidates[random];
        count++;

        return candidateVotes;
    }

    public static void println (String text){
        System.out.println(text);
    }

    // collects string input from user
    //
    public static String input(String text){
        Scanner sc = new Scanner(System.in);
        println(text);
        return sc.nextLine();
    }

    // turns string input to an int value
    //
    public static int inputInt(String text){
        int number =0;
        try{
            number = Integer.parseInt(input(text));
        }catch (NumberFormatException e){
            println("invalid");
            inputInt(text);
        }
        return number;
    }

    public static void newElection(Candidate[] totalCandidates, Candidate[] candidateVotes, int count){
        String userToCount = input("Who should count the votes?");
        int howMany = inputInt("How many times should we run the electon?");

        Candidate userCounting = A3.getByUsername(userToCount, totalCandidates);

        Candidate[] electionCandidates = new Candidate[count];

        for (int i = 0; i < count; i++) {
            electionCandidates[i] = candidateVotes[count];
        }

        Candidate[] candidatesWithVotes = new Candidate[howMany * (count -1)];

        for (int i = 0; i <= howMany; i++) {
            for (int j = 0; j < count; j++) {
                try {
                    candidatesWithVotes[(count * i) + j] = electionCandidates[j].vote(electionCandidates);
                }
                catch (Exception e) {
                    candidatesWithVotes[j] = new Candidate_ec22718();
                }
            }
        }

        Candidate winner = userCounting.selectWinner(candidatesWithVotes);
        System.out.println("Most common winner is: " + winner.getName());
        return;

    }

    public static boolean menu2(int choice, boolean end){
        if (choice ==1){
            println("Leaving");
            end = true;
        } else if (choice==2){
            println("...");
        }else{
            menu2(choice,end);
        }
        return end;
    }

    public static void main(String[] args) {
        boolean end = false;
        Candidate[] totalCandidates = A3.getCandidateArray();
        Candidate[] candidateVotes = new Candidate[totalCandidates.length];
        int count =0;

        while (!end){
            String choice =input("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            menuChoice(choice,totalCandidates, candidateVotes,count);
            int choice2= inputInt("Would you like to 1) EXIT or 2) Choose option from Menu?");
            end = menu2(choice2, end);

        }

    }

}
