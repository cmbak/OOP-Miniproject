package OOP.ec22697.MP;// File Candidate_ec22903.java
//
// Machine generated stub for Assignment 2

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22903 extends Candidate {
    public String getName() {
        return "Shane";
    }

    public String getSlogan() {
        return "Hey!";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22903();

        for (Candidate x: candidates){
            if (x.getName().equals("Queen")) {
            return x;}
            }

        return r;
    }

    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22903();

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
        char ans;

        System.out.println(question);
        ans = ask.next().charAt(0);

        return ans;
    }

    public static String inputString(String question) {
        Scanner ask = new Scanner(System.in);
        String ans;

        System.out.println(question);
        ans = ask.nextLine();

        return ans;
    }

    public static void main(String[] args) {
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] election = new Candidate[allCandidates.length];
        int counter = 0;
        String username = "";
        boolean exit = false;
        char menu = 'x';

        while (!exit) {
            menu = inputChar("a.)add a candidate to the election \n b.) add a random candidate to the election \n c.) run the election");
            switch (menu) {
                case 'a':
                    username = inputString("Please enter the username of the candidate:");
                    Candidate newCandidate = A3.getByUsername(username, allCandidates);
                    election[counter] = newCandidate;
                    counter++;
                    break;

                case 'b':
                    Random r = new Random();
                    int randomInt = r.nextInt(allCandidates.length);
                    election[counter] = allCandidates[randomInt];
                    counter++;
                    break;

                case 'c':
                    username = inputString("Enter username of the person who should count the votes:");
                    Candidate chosenCandidate = A3.getByUsername(username, allCandidates);
                    int loop = Integer.parseInt(inputString("How many times would you wnat to run the election?"));
                    runElection(election, counter, loop, chosenCandidate);
                    exit = true;
                    break;

                default:
                    System.out.println("Please enter a, b or c");
            }
            displayCandidates(election, counter);
        }
    }

    public static void displayCandidates(Candidate[] candidates, int counter) {
        System.out.println("Candidates in the election: ");
        for (int i = 0; i < counter; i++) {
            System.out.println((i+1) + ". " + candidates[i].getName() + ": " + candidates[i].getSlogan());
        }
        return;
    }

    public static void runElection(Candidate[] election, int counter, int loop, Candidate chosenCandidate) {        
        Candidate[] votes = new Candidate[loop * (counter - 1)];

        for (int i = 0; i <= loop; i++) {
            for (int j = 0; j < counter; j++) {
                try {
                    votes[(counter * i) + j] = election[j].vote(election);
                } 
                catch (Exception e) {
                    votes[i] = new Candidate_ec22913();
                }
            }
        }

        Candidate winner = chosenCandidate.selectWinner(votes);
        System.out.println("WINNER: " + winner.getName());
    }
}
