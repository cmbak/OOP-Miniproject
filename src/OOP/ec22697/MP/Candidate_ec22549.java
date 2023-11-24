package OOP.ec22697.MP;// File Candidate_ec22549.java

import java.util.Scanner;

class Candidate_ec22549 extends Candidate {
    
    public static void main(String[] args) {        
        final Candidate[] FULL_CANDIDATES_LIST = A3.getCandidateArray(); // Get all potential candidates.
        final int NUMBER_OF_CANDIDATES_REQUIRED = inputPositiveInt("How many candidates would you like to add to the election?");
        
        System.out.println();
        
        
        // Ask the user the enter all the usernames of the candidates they want.
        Candidate[] chosen_candidates_list = new Candidate[NUMBER_OF_CANDIDATES_REQUIRED];
        for (int x = 0; x < chosen_candidates_list.length; x++) { 
            chosen_candidates_list[x] = getCandidate("Please enter the username of candidate number " + (x+1) + " e.g. (ec22549)", FULL_CANDIDATES_LIST);
            System.out.println();
        }
        
        
        
        
        // Ask the user who should be counting votes and find the index of that Candidate object.        
        final int INDEX_OF_VOTE_COLLECTOR = getVoteCollectorIndex("Please enter the username of the candidate who should conduct votes counting? e.g. (" + chosen_candidates_list[0].un + ")", chosen_candidates_list);
        
        System.out.println();
        
        
        
        // Ask the user how many times the election should be repeated.
        final int REPEAT_NUMBER_OF_TIMES = inputPositiveInt("How many times should the election be repeated?");
        Candidate[] winners = processWinnersList(REPEAT_NUMBER_OF_TIMES, chosen_candidates_list, INDEX_OF_VOTE_COLLECTOR);
        
        
        // Find the most common winner(s).
        displayMostCommonWinners(winners);
                
        return;
    }
    
    public static void displayMostCommonWinners(Candidate[] winners) {
        
        int[] winners_count = new int[winners.length];
        int highest_count = 1;
        
        for (int x = 0; x < winners_count.length; x++) {
            int count = 1;
            
            for (int y = 0; y <= x ; y++) {
                if (x != y && winners[x].un == winners[y].un) {
                    count++;
                }
            }
            
            if (count > highest_count) {
                highest_count = count;
            }
            
            winners_count[x] = count;
        }
        
        System.out.println("The most common winner(s) is/are :");
        
        for (int x = 0; x < winners_count.length; x++) {
            if (highest_count == winners_count[x]) {
                System.out.println(winners[x].un);
            }
        }
        
        return;
    }
    
    public static int getVoteCollectorIndex(String message, Candidate[] chosen_candidates_list) {
        boolean valid = false; // flag
        int index_of_vote_collector = -1;
        
        System.out.println(message);
        
        while (!valid) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNext()) {
                String entered_username = scanner.next();
                
                for (int x = 0; x < chosen_candidates_list.length; x++) {
                    if (chosen_candidates_list[x].un.equals(entered_username)) {
                        index_of_vote_collector = x;
                    }
                }
                
                if (index_of_vote_collector == -1) {
                    System.out.println("This person is not running as a candidate. Please enter another username.");
                }
                else {
                    valid = true;
                }
            }
        }
        
        return index_of_vote_collector;
    }
    
    public static Candidate[] processWinnersList(int REPEAT_NUMBER_OF_TIMES, Candidate[] chosen_candidates_list, int INDEX_OF_VOTE_COLLECTOR ) {
         Candidate[] winners = new Candidate[REPEAT_NUMBER_OF_TIMES];
        
        for (int x = 0; x < winners.length; x++) {
            
            // Get a list of each vote.
        
            Candidate[] votes = new Candidate[chosen_candidates_list.length];
        
            for (int y = 0; y < chosen_candidates_list.length; y++) {
                votes[y] = chosen_candidates_list[y].vote(chosen_candidates_list);
            }

            // Select winner and display.
            Candidate winner = chosen_candidates_list[INDEX_OF_VOTE_COLLECTOR].selectWinner(votes);
            System.out.println("The winner for election " + (x+1) + " is " + winner.getName() + " (" + winner.un + ") who's slogan is \"" + winner.getSlogan() + "\"");
            
            // Store winner in winners list.
            winners[x] = winner;
        }
        
        return winners;
    }
    
    public static Candidate getCandidate(String message, Candidate[] FULL_CANDIDATES_LIST) {
        boolean valid = false;
        Candidate c = null;
        String entered_username = "";
        
        System.out.println(message);
        
        while (!valid) {
            Scanner scanner  = new Scanner(System.in);
            if (scanner.hasNext()) {
                entered_username = scanner.next();
                if (A3.getByUsername(entered_username, FULL_CANDIDATES_LIST) == null || entered_username.length() == 0) {
                    System.out.println("This candidate does not exist. Please try again.");
                }
                else {
                    valid = true;
                }
            }
        }
        
        return A3.getByUsername(entered_username, FULL_CANDIDATES_LIST);
    }
    
    public static int inputPositiveInt(String message) {
        boolean valid = false; // flag
        int value = -1;
        
        System.out.println(message);
        
        while (!valid) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value < 1) {
                    System.out.println("This is not valid. Please try again.");
                }
                else {
                    valid = true;
                }
            }
            else {
                System.out.println("This is not an integer. Please try again.");
            }
        }
        
        return value;
    }
    
    public String getName() {
        return "Unknown Name";
    }
    
    public String getSlogan() {
        return "The trend is your friend!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        if (candidates.length == 0) {
            return new Candidate_ec22549();
        }
        
        return candidates[0];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        if (votes.length == 0) {
            return new Candidate_ec22549();
        }
        
        // Dictatorship style "voting" by selecting first person as the winner.
        return votes[0];
    }
    
}