package OOP.ec22697.MP;// File Candidate_ec22784.java
//
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22784 extends Candidate {
    
    public String getName() {
        return "Rohan Rahman";
    }
    
    public String getSlogan() {
        return "sus";
    }

    public static void main(String[] args) {
        Candidate[] all_candidates = A3.getCandidateArray();
        Candidate[] chosen_candidates = new Candidate[546];
        int index_chosen_candidates = 0;
        
        printSelectedCandidatesArray(chosen_candidates, index_chosen_candidates);
        System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
        String input = StrInput("");
        while (!(input.equals("a") || input.equals("b") || input.equals("c"))) {
            input = StrInput("Please enter a valid input: ");
        }

        while (!(input.equals("c"))) {
            
            if (input.equals("a")) {
                if (index_chosen_candidates == all_candidates.length) {
                    System.out.println("Full number of candidates!");
                }
                else {
                    System.out.println("Please enter a username");
                    String new_candidate_user_name = StrInput("");
                    Candidate new_candidate = A3.getByUsername(new_candidate_user_name, all_candidates);
                    if (new_candidate == null) {
                        System.out.println("This candidate doesn't exist");
                    }
                    else {
                        chosen_candidates[index_chosen_candidates] = new_candidate;
                        index_chosen_candidates++;
                    }
                }
            }
            else if (input.equals("b")) {
                if (index_chosen_candidates == all_candidates.length) {
                    System.out.println("Full number of candidates!");
                }
                else {
                    int random_int = -1;
                    while (random_int == -1) {
                        random_int = RandomCandidate(chosen_candidates, all_candidates, index_chosen_candidates);
                        chosen_candidates[index_chosen_candidates] = all_candidates[random_int];
                        index_chosen_candidates++;
                    }
                }
            }
            printSelectedCandidatesArray(chosen_candidates, index_chosen_candidates);
            System.out.println("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            input = StrInput("");
            while (!(input.equals("a") || input.equals("b") || input.equals("c"))) {
                input = StrInput("Please enter a valid input: ");
            }
        }
        if (input.equals("c")) {
            Candidate count_candidate = null;
            String choose_counter = StrInput("Who should count the votes?");
            while (count_candidate == null) {
                count_candidate = A3.getByUsername(choose_counter, all_candidates);
                if (count_candidate == null) {
                    System.out.println("Couldn't find " + choose_counter + ". Please choose someone else to count the votes.");
                    choose_counter = StrInput("");
                }
            }
            int elections_ran = IntInput("How many times shall we run the election?");
            while (elections_ran <= 0) {
                elections_ran = IntInput("Please enter a valid amount of times to run the election.");
            }

            Candidate[] winning_candidates = new Candidate[elections_ran];

            for (int j = 0; j < elections_ran; j++) {

                Candidate[] final_candidates = new Candidate[index_chosen_candidates + 1];
                for (int i = 0; i < index_chosen_candidates; i++) {
                    final_candidates[i] = chosen_candidates[i];
                }
                Candidate[] votes = new Candidate[all_candidates.length];

                for (int i = 0; i < all_candidates.length; i++) {
                    votes[i] = all_candidates[i].vote(final_candidates);
                }
                winning_candidates[j] = count_candidate.selectWinner(votes);
            }

        }
    }

    public static void CountMost(Candidate[] winning_candidate) {
        boolean multiple_winners = false;
        String winner_username = null;
        if (winning_candidate.length != 0) 
        {
            Candidate[] winner_count = new Candidate[winning_candidate.length];

            for (int j = 0; j < winning_candidate.length; j++) {
                winner_count[j] = winning_candidate[j];
            }
            int higest_count = 0;
            for (int i = 0; i < winner_count.length; i++) 
            {
                Candidate c = winner_count[i];
                if (c != null)
                {
                    int count = 0;
                    for (int j = 0; j < winner_count.length; j++) 
                    {
                        Candidate x = winner_count[j];
                        if (x != null && x.getName() == c.getName()) 
                        {
                            winner_count[j] = null;
                            count++;
                        }
                    }
                    if (count == higest_count) 
                    {
                        winner_username = winner_username + " and " + c.getName();
                        multiple_winners = true;
                    }
                    if (count > higest_count) 
                    { 
                        higest_count = count; 
                        winner_username = c.getName();
                        multiple_winners = false;
                    } 
                }
            }
        }
        if (winner_username != null)
        {
            if (multiple_winners == true)
            {
                System.out.println("Most common winners are " + winner_username);
            }
            else
            {
                System.out.println("Most common winner is " + winner_username);
            }
        }
        System.out.println("There were no other winners.");
    }
    

    public static int RandomCandidate(Candidate[] chosen_candidates, Candidate[] all_candidates, int index_chosen_candidates) {
        int random_int = randomInt(all_candidates.length);
        Candidate random_candidate = all_candidates[random_int];
        for (int i = 0; i < index_chosen_candidates; i++) {
            if (chosen_candidates[i] == random_candidate) {
                return -1;
            }
        }

        return random_int;

    }
 
    public static String StrInput(String prompt) {
        System.out.println(prompt);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        return input;
    }

    public static int IntInput(String prompt) {
        System.out.println(prompt);

        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        return input;
    }

    public static void printCandidatesArray(Candidate[] candidates) {

        System.out.println("Candidates are");

        if (candidates.length == 0){
            System.out.println("None");
        }
        else {
            for (int i = 0; i < candidates.length; i++) {
                int pos = i + 1;
                System.out.println(pos + ". " + candidates[i].getName());
            }
        }
    }

    public static void printSelectedCandidatesArray(Candidate[] candidates, int index) {
        System.out.println("Candidates are");

        if (index == 0){
            System.out.println("None");
        }
        else {
            for (int i = 0; i < index ; i++) {
                int pos = i + 1;
                System.out.println(pos + ". " + candidates[i].getName());
            }
        }
    }

    public static int randomInt(int bound) {
        Random r = new Random();
        return r.nextInt(bound);
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22784();
        
        if (candidates.length != 0) r = candidates[0];
 
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22784();
        
        if (votes.length != 0) r = votes[0];
 
        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
        
        return r;
    }
    
}
