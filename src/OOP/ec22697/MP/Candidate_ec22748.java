package OOP.ec22697.MP;// File Candidate_ec22748.java
//
// Machine generated stub for Assignment 3

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22748 extends Candidate {

    public static void main(String[] args) {
        Candidate[] allContributions = A3.getCandidateArray();
        int voting = 0;
        Candidate[] allCandidates = new Candidate[allContributions.length];
        boolean exit = false;

        while (exit == false){
            ListOfCandidates(allCandidates, voting);

            String ans = inputString("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d)exit?");
            if (ans.equals("a")){
                String username = inputString("Please enter a username");
                Candidate specificCandidate = A3.getByUsername(username, allContributions);
                allCandidates[voting] = specificCandidate;
                voting ++;
            }

            else if (ans.equals("b")){
                Random rs = new Random();
                int randomNum = rs.nextInt(allContributions.length);
                allCandidates[voting] = allContributions[randomNum];
                voting ++;
            }
            else if (ans.equals("c")){
                String voteInput = inputString("Who should count the votes?");
                Candidate voteRunner = A3.getByUsername(voteInput, allCandidates);
                int times = inputInt("How many times shall we run the election?");
                runElection(allCandidates,voting,times,voteRunner);

            }


            else if(ans.equals("d")){
            exit = true;
        }
        else{
            pr("did not get it");
        }
    }

}

    public static void runElection(Candidate[] allCandidates, int voting, int times, Candidate voteRunner){
        //Creates an array filled with candidates
        Candidate[] newElection = new Candidate[voting];
        for (int i = 0; i < voting; i++) {
            newElection[i] = allCandidates[voting];
        }

        Candidate[] winningCandidates = new Candidate[times * (voting - 1)];
        //Repeat the action according to the number inputted by the user
        for (int i = 0; i <= times; i++) {
            for (int j = 0; j < voting; j++) {
                winningCandidates[(voting * i) + j] = newElection[j].vote(newElection);
            }
        }
        // winning candidate
        Candidate actualWinner = voteRunner.selectWinner(winningCandidates);
        //Prints the winner
        System.out.println("Most common winner is: " + actualWinner.getName());
    }

    public static void ListOfCandidates(Candidate[] list, int length){
        pr("Candidates are");
        if (length == 0){pr("None");}
        else{
            for (int i=0; i < length; i++){
                pr(list[i].getName()+ ": " + list[i].getSlogan());
            }
        }

        return;
    }

    public static String inputString (String message)
    {
        String answer;
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);
        answer = scanner.nextLine();

        return answer;
    }

    public static int inputInt (String message)
    {
        String answer = inputString(message);

        return Integer.parseInt(answer);
    }

    private static <T> void pr(T s) { System.out.println(s);}

    public String getName() {
        return "Michael Jordan";
    }

    public String getSlogan() {
        return "No pain no gain";
    }

    public Candidate vote(Candidate[] candidates) {
        // If array is empty, return a new candidate
        if (candidates.length == 0)
            return new Candidate_ec22748();

        // Search for a slogan.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("No pain no gain"))
                return c;

        // Search for the name.
        for (Candidate c : candidates)
            if (c.getName().equals("Michael Jordan"))
                return c;

        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];


    }

    public Candidate selectWinner(Candidate[] votes) {

        // if there is no votes return a new candidate who will be the winner
        if (votes.length == 0)
            return new Candidate_ec22748();

        // the winner right now is the first element of the array
        Candidate Winner = votes[0];

        // voting system. The winner is the one with the most votes

        int highest = 0;
        for (Candidate c : votes) {

            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count >= highest) {
                highest = count;
                Winner = c;
            }
        }

        return Winner;
    }

}
