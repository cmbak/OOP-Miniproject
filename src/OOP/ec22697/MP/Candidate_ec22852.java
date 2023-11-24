package OOP.ec22697.MP;// File Candidate_ec22852.java
//
// Machine generated stub for Assignment 2

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


class Candidate_ec22852 extends Candidate {

    public static void main(String[] args) {

        Candidate[] all_candidates = A3.getCandidateArray();
        // No seed set, so candidates should be different each time
        Random random_gen = new Random();

        Candidate trusted_counter = new Candidate_ec22852();

        ArrayList<Candidate> candidates = new ArrayList<Candidate>();

        // End Program if there are no candidates
        if (all_candidates.length == 0) return;

        char option = getValidOption("Would you like to:\n    a) Run a random election\n    b) Add  a candidate\n    c) View candidates in election\n    d) View all candidates\n    e) Run election\n    x) Exit the program");

        while (option != 'x'){
            if (option == 'a') {
                System.out.println("There are " + all_candidates.length + " candidates");

                int number_of_candidates_to_include = inputIntegerInRange("How many candidates do you want to include?", 1, all_candidates.length);
                Candidate[] available_candidates = new Candidate[number_of_candidates_to_include];

                for (int i = 0; i < number_of_candidates_to_include; i++) {
                    // Adds a random candidate from all_candidates to available_candidates
                    // **********FUTURE IMPROVEMENT: Random choice so can add a candidate that already is in the array, make it so if a candidate is already in, they cannot be added again **********//
                    // **********Above problem will often be seen if number_of_candidates_to_include is a large number *************//
                    Candidate random_candidate = all_candidates[random_gen.nextInt(all_candidates.length)];
                    while (!proper(random_candidate)) {
                        random_candidate = all_candidates[random_gen.nextInt(all_candidates.length)];
                    }
                    available_candidates[i] = random_candidate;
                }

                Candidate[] votes = getVotes(available_candidates);

                Candidate real_winner = trusted_counter.selectWinner(votes);
                // Variable to store how many people actually got the correct winner
                int number_of_correct_winners = 0;
                for (Candidate candidate : available_candidates) {
                    try {
                        Candidate stated_winner = candidate.selectWinner(votes);

                        if (stated_winner == real_winner) {
                            number_of_correct_winners++;
                        }

                        System.out.println(candidate.getName() + " (" + candidate.un + "): " + " says the winner is " + stated_winner.getName() + " (" + stated_winner.un + "): " + stated_winner.getSlogan());
                        System.out.println();

                    }
                    // If the candidates select winner produces an error, ignore it and move on
                    catch (Exception ignored) {
                    }
                }

                System.out.println();
                System.out.println("THE REAL WINNER IS:\n");
                System.out.println("\"" + real_winner.getName() + "\"" + " (" + real_winner.un + "): " + real_winner.getSlogan());
                System.out.println("\n");
                double percentage_of_correct_winner = ((double) number_of_correct_winners / number_of_candidates_to_include) * 100;
                String formatted_percentage = String.format("%.2f", percentage_of_correct_winner);
                System.out.println(number_of_correct_winners + " / " + available_candidates.length + " (" + formatted_percentage + "%) of the candidates told the truth about who the winner is\n");

            }

            else if (option == 'b'){
                String username = input("Enter a username");
                addCandidate(username, all_candidates, candidates);
                System.out.println();
            }

            else if (option == 'c'){
                printCandidates(candidates);
                System.out.println();
            }

            else if (option == 'd'){
                printAllCandidates(all_candidates);
                System.out.println("\n");
            }

            else{
                if (candidates.size() == 0) System.out.println("EMPTY");
                else {
                    Candidate most_common_winner = runElection(trusted_counter, candidates);
                    System.out.println("The most common winner is " + most_common_winner.getName());
                    // Can clear the candidates list to restart with no candidates
                    //candidates.clear();
                }
                System.out.println();
            }

            option = option = getValidOption("Would you like to:\n    a) Run a random election\n    b) Add  a candidate\n    c) View candidates in election\n    d) View all candidates\n    e) Run election\n    x) Exit the program");
        }
    }


    //------------------My Candidate Methods------------------------//
    // name
    // slogan
    // vote
    // select winner
    public String getName() {
        return "AAAAAAAAAAAAAAA";
    }

    public String getSlogan() {
        return "No slogan";
    }

    public Candidate vote(Candidate[] candidates) {

        // If candidates list empty, return a new "me" object
        if (candidates.length == 0) return new Candidate_ec22852();

        // If anyone in candidates has same name as me, vote for them
        for (Candidate candidate : candidates) {
            if (candidate.getName().equals("AAAAAAAAAAAAAAA")) {
                return candidate;
            }
        }

        // Otherwise vote for someone random
        Random rand_gen = new Random();
        int random_index = rand_gen.nextInt(candidates.length);
        return candidates[random_index];

    }

    public Candidate selectWinner(Candidate[] votes) {
        // Set a default winner, an instance of this class
        Candidate winner = new Candidate_ec22852();

        // Returns the default winner if list of votes is empty
        if (votes.length == 0) return winner;

        // Find candidate with the highest number of votes
        // *If multiple people have the same number of votes return the last person with that many votes
        int highestCount = 0;
        for (Candidate c : votes) {
            // Won't include the nulls which are added if someone's code has an error during voting
            if (c == null) continue;
            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count >= highestCount) {
                // Update the highest count and set winner to whoever has the new highest count
                highestCount = count;
                winner = c;
            }
        }

        return winner;
    }

    //------------------Other Methods------------------------//
    public static String input(String text){
        System.out.println(text);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static char getValidOption(String prompt) {
        String option_input = input(prompt).toLowerCase();

        while (!option_input.matches("[abcdex]")) {
            option_input = input("Enter a valid option!! \n" + prompt).toLowerCase();
        }

        return option_input.charAt(0);
    }

    public static int inputValidPositiveInteger(String text)
    {
        String input = input(text);
        while (!input.matches("\\d+"))
        {
            input = input("Enter a valid whole number, DON'T ENTER: NON NUMERIC CHARACTERS \n" + text);
        }

        return Integer.parseInt(input);
    }


    // Checks if integer is between bounds, both inclusive
    public static int inputIntegerInRange(String text, int lower_bound, int upper_bound){
        int user_input = inputValidPositiveInteger(text);
        while (user_input < lower_bound || user_input > upper_bound){
            user_input = inputValidPositiveInteger("Enter an integer in given range\n" + text);
        }
        return user_input;
    }

    // TAKEN FROM A3.JAVA
    private static boolean proper(Person p) {
        return !(p.getName().length() > 6 &&
                p.getName().startsWith("No name"));
    }

    public static Candidate[] getVotes(Candidate[] candidates){
        Candidate[] votes = new Candidate[candidates.length];
        for (int i = 0; i < candidates.length; i++){
            try {
                votes[i] = candidates[i].vote(candidates);
            }
            catch(Exception ignored){
                votes[i] = null;
            }
        }
        return votes;
    }

    public static void addCandidate(String username, Candidate[] all_candidates , ArrayList<Candidate> candidates){
        if(A3.getByUsername(username, all_candidates) == null) {
            System.out.println(("Username was invalid"));
        }else {
            candidates.add(A3.getByUsername(username, all_candidates));
        }
    }

    public static void printCandidates(ArrayList<Candidate> candidates) {
        if (candidates.size() == 0) System.out.println("EMPTY");
        else {
            for (Candidate c : candidates) {
                System.out.println(c.un);
            }
        }
    }

    public static void printAllCandidates(Candidate[] candidates) {
        if (candidates.length == 0) System.out.println("EMPTY");
        else {
            for (int i =0; i < candidates.length; i++) {
                if (i % 10 == 0) System.out.println();
                System.out.print(candidates[i].un + " ");
            }
        }
    }

    public static Candidate runElection(Candidate trusted_counter, ArrayList<Candidate> candidates){
        int electionRunAmount = inputValidPositiveInteger("How many times shall we run the election?");
        Candidate[] candidates_array = candidates.toArray(new Candidate[0]);
        Candidate[] winners = new Candidate[electionRunAmount];

        for (int run=0; run<electionRunAmount; run++){
            Candidate[] votes = getVotes(candidates_array);
            Candidate winner_of_current_run = trusted_counter.selectWinner(votes);
            winners[run] = winner_of_current_run;
        }

        Candidate currentWinner = winners[0];
        int highestCount = 0;

        for(int i=0; i < winners.length; i++) {
            int currentCount = 0;

            for(int j = 0; j < winners.length; j++) {
                if(winners[i] == winners[j]) {
                    currentCount++;
                }
            }

            if(currentCount > highestCount) {
                currentWinner = winners[i];
                highestCount = currentCount;
            }
        }

        return currentWinner;

    }

}
