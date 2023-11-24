package OOP.ec22697.MP;// File Candidate_ec21416.java
//
// Machine generated stub for Assignment 2

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;  // Import the Scanner class
import java.lang.*;

public class Candidate_ec21416 extends Candidate {


    public String getName(){
        return "Ray";

    }

    public String getSlogan(){
        return "A new way";


    }

    public Candidate vote(Candidate[] candidates){

        // First search for self on the list of candidates.
        for (Candidate c : candidates)
            if (c.getName().equals("Ray"))
                return c;

        // Otherwise, default to random candidate on list.
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];


    }

    public Candidate selectWinner(Candidate[] votes) {

        // If array is empty, return instance of this class.
        if (votes.length == 0)
            return new Candidate_ec21416();

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

    public static void print(String printed){
        System.out.print(printed);

    }

    public static String getString(String multiChoice){
        print(multiChoice);
        Scanner scanner = new Scanner(System.in);
        return scanner.next();

    }

    public static int getInt(String question){
        print(question);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static String getChoice(){
        String choice = getString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election? \n");
        String[] acceptableChoices = new String[]{"a","b","c"};
        boolean isIn = false;
        for (String acceptableChoice : acceptableChoices) {
            if (choice.equals(acceptableChoice)) {
                isIn = true;
            }

        }

        if (isIn){
            return choice;
        }
        else {
            print("Please enter a valid choice \n");
            System.out.println(choice);
            getChoice();
        }
        return choice;
    }

    public static boolean getBoolean(String question, String trigger){
        boolean ret = false;
        String answer = getString(question);
        if (answer == trigger){
            ret = !ret;
        }
        return ret;
    }

    /*
    *Calls A3 method, getByUsername. The method is
    * */
    public static boolean verify(String candidateName){
        Candidate[] existingCandidates = A3.getCandidateArray();
        return ((A3.getByUsername(candidateName, existingCandidates) != null));
    }
    public static void addCandidate(ArrayList<Candidate> candidatesForUser){

        String candidateName = getString("Please enter a username. \n");
        if((verify(candidateName))){
                candidatesForUser.add(A3.getByUsername(candidateName, A3.getCandidateArray()));
        }
    }

    public static void addRandomCandidate(ArrayList<Candidate> candidatesForUser){
        Candidate[] a3Candidates = A3.getCandidateArray();
        Random randy = new Random();
        int randomCandidateIndex = randy.nextInt(a3Candidates.length);
        candidatesForUser.add(a3Candidates[randomCandidateIndex]);

    }

    public static void printCandidates(ArrayList<Candidate> candidates){
        int currentCount = 1;
        System.out.println("Candidates are ");
        for(Candidate x : candidates) {
            System.out.println(currentCount + ". " + x.getName());
            currentCount++;
        }
    }

    public static Candidate[] getA3Candidates(){
        return A3.getCandidateArray();
    }

    public static Candidate[] arrayListToArrayCandidate(ArrayList<Candidate> activeCandidates){
        Candidate[] returnList = new Candidate[activeCandidates.size()];
        int i = 0;
        for (Candidate x: activeCandidates) {
            returnList[i] = activeCandidates.get(i);
            i++;
        }
        return returnList;
    }
    public static void runElection(Candidate[] qmCandidates, ArrayList<Candidate> activeCandidates){
        String voteCounter = getString("Who should count the votes");
        Candidate[] currentCandidates = arrayListToArrayCandidate(activeCandidates);
        Candidate[] currentElection = new Candidate[qmCandidates.length];
        ArrayList <Candidate> currentWinners = new ArrayList<>();
        if (verify(voteCounter)){
            int numberOfElections = getInt("How many times shall we run the election");
            for (int i=0; i<numberOfElections; i++){
                for (int j=0; j< currentElection.length; j++) {
                    try {
                        currentElection[j] = qmCandidates[j].vote(currentCandidates);
                    }catch (Exception e){currentElection[j] = new Candidate_ec21416();}
                }
                currentWinners.add(i, mostWinningest(currentElection));
            }
        }
        /*for (int x=0; x < currentWinners.size(); x++){
            System.out.println(currentWinners.get(x));
        }*/
        printCandidates(currentWinners);

    }

    public static Candidate mostWinningest(Candidate[] arr)
    {
        /*This code was adapted from geeksforgeeks.org/frequent-element-array/*/
        int maxcount = 0;
        Candidate element_having_max_freq = arr[0];
        int n = getA3Candidates().length;
        int mostFrequentWinner = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                }
            }

            if (count > maxcount) {
                maxcount = count;
                element_having_max_freq = arr[i];
            }
        }

        return element_having_max_freq;
    }

    public static void start(){
        Candidate[] a3Candidates = getA3Candidates();
        ArrayList<Candidate> currentCandidates = new ArrayList<>();
        System.out.println("= Running Repeated Elections =");
        String choice = getChoice();
        boolean stillRunning = true;
        while (stillRunning) {
            switch (choice) {
                case "a":
                    addCandidate(currentCandidates);
                    printCandidates(currentCandidates);


                case "b":
                    addRandomCandidate(currentCandidates);
                    printCandidates(currentCandidates);


                case "c":
                    runElection(a3Candidates, currentCandidates);





                    stillRunning = getBoolean(("Would you like to \n" + "a) exit "),"a");
            }
        }

    }

    public static void main(){
        //runElection(getA3Candidates(),);
        start();

    }



}

