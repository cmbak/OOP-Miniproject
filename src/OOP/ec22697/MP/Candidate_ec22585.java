package OOP.ec22697.MP;// File Candidate_ec22585.java
//

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22585 extends Candidate {
    public static String getSInput(String message) {
        Scanner scanner = new Scanner(System.in);
        String inputS;

        print(message);
        inputS = scanner.nextLine();

        return inputS;
    }

    public static int addSpecificCand(Candidate[] all_candidates, Candidate[] election_candidates, int num_added) {
        String candidate_username = getSInput("Please enter a username.");
        Candidate new_candidate = A3.getByUsername(candidate_username, all_candidates);

        if (new_candidate != null) {
            election_candidates[num_added] = new_candidate;
            num_added += 1;
        }
        else {
            print("Cannot find candidate: " + candidate_username);
        }

        showAllCandidates(election_candidates, num_added);

        return num_added;
    }

    public static int addRandomCandidate(Candidate[] all_candidates, Candidate[] election_candidates, int num_added) {
        Random rand = new Random();
        int random_number = rand.nextInt(all_candidates.length);

        if (all_candidates[random_number] != null) {
            election_candidates[num_added] = all_candidates[random_number];
            num_added += 1;
        }

        return num_added;
    }

    public static void showAllCandidates(Candidate[] election_candidates, int num_added) {
        print("Candidates are: ");
        if (num_added == 0) {
            print("None");
        }
        else {
            for (int i=0; i<num_added; i++) {
                System.out.println((i+1) + ". " + (election_candidates[i].getName()));
            }
        }

        return;
    }

    
    public static Candidate[] runElection(Candidate[] all_candidates,
                                          Candidate[] extra_election_candidates, int num_added) {
        Candidate[] election_candidates = new Candidate[num_added];
        
        for (int t=0; t<num_added; t++) {
            election_candidates[t] = extra_election_candidates[t];
        }
        
        String candidate_username = getSInput("Who should count the votes? ");
        Candidate chosen_cand = A3.getByUsername(candidate_username, all_candidates);
        Candidate[] final_winners = null;

        if (chosen_cand != null) {
            int num_runs = getIntInput("How many times shall we run the election? ");
            Candidate[] election_winners = new Candidate[num_runs];
            for (int x = 0; x < num_runs; x++) {
                Candidate[] election_votes = new Candidate[election_candidates.length];
                for (int i = 0; i <election_candidates.length; i++) {
                    try {
                        election_votes[i] = all_candidates[i].vote(election_candidates);
                    }
                    catch (Exception e) {
                        print("This is the problem: " + e);
                        election_votes[i] = new Candidate_ec22585();
                    }
                }
                election_winners[x] = chosen_cand.selectWinner(election_votes);
            }
            final_winners = getCommonWinner(election_winners);
        }
        else {
            print("Cannot find candidate: " + candidate_username);
        }
        
        

        return final_winners;
    }
    
    
    public static boolean nameInArray(Candidate[] array1, String candidate_name) {
        boolean in_array = false;
        
        for(int i=0; i<array1.length; i++) {
            Candidate test_cand = array1[i];
            if (test_cand != null) {
                try {
                    if (array1[i].getName().equals(candidate_name)) {
                        in_array = true;
                    }
                }
                catch (Exception e) {
                    print("Something went wrong");
                }
            }
        }

        return in_array;
    }
    

    public static Candidate[] getCommonWinner(Candidate[] election_winners) {
        Candidate[] common_winners_temp = new Candidate[election_winners.length];
        int num_winner = 0;
        int index_of_common = -1;
        int highest_occurence = 0;


        if (election_winners.length != 0) {
            for (int i=0; i<election_winners.length; i++) {
                Candidate can1 = election_winners[i];
                int can1_count = 0;
                for (int x=0; x<(election_winners.length-1); x++) {
                    Candidate can2 = election_winners[x];
                    if (can2.getName().equals(can1.getName())) {
                        can1_count += 1;
                    }
                }

                if (can1_count > highest_occurence) {
                    highest_occurence = can1_count;
                    common_winners_temp[0] = can1;
                    num_winner = 1;
                }
                else if (can1_count == highest_occurence && 
                         (nameInArray(common_winners_temp, can1.getName()) != true)) {
                    common_winners_temp[num_winner] = can1;
                    num_winner += 1;
                }
            }
        }
        
        // Duplicate the array and so it does not have multiple nulls.
        Candidate[] common_winners = new Candidate[num_winner];
        for (int t=0; t<num_winner; t++) {
            common_winners[t] = common_winners_temp[t];
        }

        return common_winners;
    }
    
    
    public static void print(String message) {
        System.out.println(message);
    }
    
    
    // A general method that will print a message to the user and take their input,
    // convert it to an integer and return it.
    // Includes exception handling to ensure an integer is input
    //
    public static int getIntInput(String message) {
        Scanner scanner = new Scanner(System.in);
        int InputI = 0;
        boolean got_int = false;
        String user_input;

        print(message);

        while (got_int == false) {
            // hasNextInt will take an input (rather than check a previously entered one)
            if (scanner.hasNextInt()) {
                InputI = scanner.nextInt();
                got_int = true;
            } else {
                scanner.nextLine();
                print("Please enter an integer");
            }
        }

        return InputI;
    }

    public String getName() {
        return "Heisenberg";
    }

    public String getSlogan() {
        return "Make games great again!";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22409();

        // If array is empty, return instance of friend's class.
        if (candidates.length == 0)
            return new Candidate_ec22409();

        // Search for a like minded candidate.
        for (int i=0; i<candidates.length; i++) {
            Candidate c = candidates[i];
            if (c.getSlogan().equals("Make games great again!")) {
                return c;
            }
        }

        // Otherwise search for friend
        for (int i=0; i<candidates.length; i++) {
            Candidate c = candidates[i];
            if (c.getName().equals("Gamer")) {
                return c;
            }
        }

        // Otherwise default to the first candidate on the list
        if (candidates.length != 0) {
            r = candidates[0];
        }

        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.

        return r;
    }

    public Candidate selectWinner(Candidate[] votes) {
        Candidate t = new Candidate_ec22409();

        if (votes.length != 0) {
            t = votes[0];
        }


        Random rand = new Random();
        int winner = rand.nextInt(votes.length);

        t = votes[winner];

        // Add code that either counts the votes
        // or uses some other way to return an element
        // of the array vote as the winner of an election.

        return t;
    }


    public static void main(String[] args) {
        Candidate[] candidates = A3.getCandidateArray();
        Candidate[] election_candidates = new Candidate[candidates.length];
        boolean keep_asking = true;
        int num_added = 0;

        while (keep_asking) {
            String option = getSInput("Would you like to a) add a specific candidate b) add a candidate at random" +
                    " c) run the election?");
            if (option.equals("a")) {
                num_added = addSpecificCand(candidates, election_candidates, num_added);
            }
            else if (option.equals("b")) {
                num_added = addRandomCandidate(candidates, election_candidates, num_added);
            }
            else if (option.equals("c")) {
                Candidate[] winners = runElection(candidates, election_candidates, num_added);
                keep_asking = false;
                print("The most common winners were: ");
                for (int i=0; i<winners.length; i++) {
                    System.out.println(winners[i].getName());
                }
            }
        }

    }
}
