package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22707 extends Candidate {
    
    public String getName() {
        return "Boris";
    }
    
    public String getSlogan() {
        return "It's Party Time!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0) 
            return new Candidate_ec22707();
        
        for (Candidate c : candidates)
            if (c.getName().equals("Boris"))
                return c;
        
        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }
    
    public Candidate selectWinner(Candidate[] votesCast) {
        if (votesCast.length == 0) 
            return new Candidate_ec22707();
        
        Random r = new Random();
        int c = r.nextInt(votesCast.length);
        return votesCast[c];
    }

    public static String inputString(String message){
        Scanner in = new Scanner(System.in);
        System.out.println(message);
        return in.next();
    }

    public static int inputInt(String message){
        try{
            String tempUserInput = inputString(message);
            int userInput = Integer.parseInt(tempUserInput);
            return userInput;
        }
        catch (NumberFormatException e){
            System.out.println("Please enter a valid input!");
            return inputInt(message);
        }
    }

    public static void main(String[] args) {
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] allVotes = new Candidate[allCandidates.length];
        int noCandidates = inputInt("How many candidates would you like to run in this election? ");
        Candidate[] runningCandidates = new Candidate[noCandidates];
        for (int i = 0; i < noCandidates; i++) {
            String candidateName = inputString("Enter the name of the candidate: ");
            Candidate check = A3.getByUsername(candidateName, allCandidates);
            if (check == null) {
                System.out.println("This candidate does not exist, please try again.");
                i--;
            } else {
                runningCandidates[i] = check;
            }
        }
        System.out.println("Here are the candidates running in this election: ");
        int x = 0;
        while (x < runningCandidates.length) {
                System.out.println((x + 1) + ". " + runningCandidates[x].getName());
                x++;
        }
        String voteCounter = null;
        Candidate check = null;
        while (check==null) {
            voteCounter = inputString("Which candidate would you like to count the votes? ");
            check = A3.getByUsername(voteCounter, runningCandidates);
            if (check == null) {
                System.out.println("This candidate does not exist, please try again");
            }
        }
        int noElections = 0;
        while (noElections <= 0) {
            noElections = inputInt("How many times would you like to run the election? ");
            if (noElections <= 0) {
                System.out.println("Please enter a positive integer");
            }
        }
        Candidate[] winners = new Candidate[noElections];
        for (int i = 0; i < noElections; i++) {
            for (int j = 0; j < allCandidates.length; j++) {
                try {
                    allVotes[j] = allCandidates[j].vote(runningCandidates);
                } catch (Exception e) {
                    System.out.println("The following candidate has an error: " + allCandidates[j]);
                    j++;
                }
            }
            winners[i] = check.selectWinner(allVotes);
        }
        Candidate mostCommonWinner = null;
        int mostVotes = 0;
        for (Candidate candidate : winners) {
            int count = 0;
            for (Candidate other : winners) {
                if (candidate == other) {
                    count++;
                }
            }
            if (count > mostVotes) {
                mostVotes = count;
                mostCommonWinner = candidate;
            }
        }
        System.out.println("The most common winner was " + mostCommonWinner.getName());
    }
}

//comment for lab 4
//another comment
