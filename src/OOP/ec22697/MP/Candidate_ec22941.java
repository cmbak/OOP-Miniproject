package OOP.ec22697.MP;
// File Candidate_ec22941.java
//
// Edited stub

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22941 extends Candidate {

    public String getName() {
        return "Shrek";
    }

    public String getSlogan() {
        return "What ARE you doing in my swamp!?";
    }

    public Candidate vote(Candidate[] candidates) {
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.

        // if array is empty vote for new instance of friend's friend
        Candidate r = new Candidate_ec221003();

        // if not empty vote for self or vote for friend's slogan, whichever it finds
        // first but self is the priority
        if (candidates.length != 0) {
            for (int i = 0; i < candidates.length; i++) {
                if (candidates[i] != null) {
                    if (candidates[i].getName().equals("Shrek"))
                        return candidates[i];
                    else if (candidates[i].getSlogan().equals("Let's work on the Future together! 😘✌"))
                        return candidates[i];
                }
            }
        }
        // only if it is empty
        return r;
    }

    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22941();

        // Add code that either counts the votes
        // or uses some other way to return an element
        // of the array vote as the winner of an election.

        // If array is empty, return new instance of own class.
        if (votes.length == 0)
            return r;

        int count = 0;
        int max_count = 0;
        // count votes and return highest count person
        for (int i = 0; i < votes.length; i++) {
            count = 0;
            for (int j = 0; j < votes.length; j++) {
                if (votes[i] == votes[j])
                    count++;
            }
            if (max_count < count) {
                max_count = count;
                r = votes[i];
            }
        }
        return r;
    }

    // string input
    public static String InputString(String message) {
        System.out.println(message);
        Scanner Input = new Scanner(System.in);
        String s = Input.nextLine();
        return s;
    }

    private static void DisplayStartMessage() {
        System.out.println("Would you like to ");
        System.out.println("a) exit ");
        System.out.println("b) run same election many times");

        return;
    }

    private static Candidate GetRandomCandidate(Candidate c_arr[]) {
        Random r = new Random();
        return c_arr[r.nextInt(c_arr.length)];
    }

    // display the current candidates
    private static void DisplayCurrentCandidates(Candidate[] c_arr, int counter) {
        System.out.println("Candidates are:");
        for (int i = 0; i < counter; i++)
            System.out.println((i + 1) + ". " + c_arr[i].getName());
        return;
    }

    // people vote
    private static Candidate[] All_Vote(Candidate[] cands, Citizen[] voters) {
        Candidate[] temp = new Candidate[voters.length];
        int count = 0;

        for (int i = 0; i < voters.length; i++) {
            try {
                temp[count] = voters[i].vote(cands);
                count++;
            } catch (Exception e) { // exception
            }
        }
        Candidate[] votes = new Candidate[count];
        for (int i = 0; i < count; i++)
            votes[i] = temp[i];
        return votes;
    }

    // run the election once
    private static void RunRepeatedElection(Candidate[] cands, Citizen[] voters, Candidate[] winners, int times,
                                            Candidate the_one_who_counts) {
        for (int i = 0; i < times; i++) {
            winners[i] = the_one_who_counts.selectWinner(All_Vote(cands, voters));
        }
        return;
    }

    // count the most frequent winner
    private static void MostFrequentCandidates(Candidate[] winners) {
        Candidate w = null;
        Candidate winners_freq[] = new Candidate[winners.length];
        int w_counter = 0;
        // If array is empty stop going further
        if (winners.length == 0)
            return;

        int count = 0;
        int max_count = 0;
        // count votes and return highest count person
        for (int i = 0; i < winners.length; i++) {
            count = 0;
            for (int j = 0; j < winners.length; j++) {
                if (winners[i] == winners[j] && winners[i] != null)
                    count++;
            }
            if (max_count < count) {
                max_count = count;
                w = winners[i];
            }
        }

        // count other winners as well and add them to the array
        winners_freq[w_counter++] = w;
        for (int i = 0; i < winners.length; i++) {
            count = 0;
            for (int j = 0; j < winners.length; j++) {
                if (winners[i] == winners[j])
                    count++;
            }
            if (max_count == count && winners[i] != null && isNotInArray(winners[i], winners_freq)) {
                winners_freq[w_counter++] = winners[i];
            }
        }
        System.out.println("Most common winner is" + w.getName());
        System.out.println("Other common winners are:");
        // display message about other winners
        for (int i = 1; i < w_counter; i++) {
            System.out.println(winners_freq[i].getName());
        }
        return;
    }

    // checks if the first digit is in the range 1-9 inclusive
    public static boolean isValidFirstDigit(String num) {
        char digit = num.charAt(0);
        if ((digit == '1') || (digit == '2') || (digit == '3') || (digit == '4') || (digit == '5') || (digit == '6')
                || (digit == '7') || (digit == '8') || (digit == '9')) {
            return true;
        } else
            return false;
    }

    // check other digits of the string are in the range 0-9 inclusive
    public static boolean isValidDigit(char digit) {
        if ((digit == '0') || (digit == '1') || (digit == '2') || (digit == '3') || (digit == '4') || (digit == '5')
                || (digit == '6') || (digit == '7') || (digit == '8') || (digit == '9')) {
            return true;
        } else
            return false;
    }

    // check if ALL the other digits of the string are in the range 0-9 inclusive
    public static boolean isValidInteger(String num) {
        // go through all character and if any character is not a digit return false
        for (int i = 1; i < num.length(); i++) {
            if (!isValidDigit(num.charAt(i))) {
                return false;
            }
        }
        // check if first digit is correct as well
        if (isValidFirstDigit(num)) {
            return true;
        } else {
            return false;
        }
    }

    // ask the user to input an integer, includes input validation, will ask the
    // user a question passed to it as an argument
    public static int InputInteger(String question) {
        String input_string = InputString(question);
        while (!isValidInteger(input_string)) {
            System.out.println("ERROR: Incorrect input, please input a positive integer: ");
            input_string = InputString(question);
        }

        return Integer.parseInt(input_string);
    }

    static <T> boolean isNotInArray(T obj, T[] arr) {
        boolean r_val = true;
        for (int i = 0; i < arr.length; i++) {
            if (obj == arr[i])
                return false;
        }
        return r_val;
    }

    // when the player choses to do a repeated election this will execute
    public static void RepeatedElection(Candidate[] allCandidates) {
        Candidate[] election_Candidates = new Candidate[546];
        int candidate_counter = 0;

        String choice_repeated_election = "";
        System.out.println("Running repeated election:");
        String input_candidate = "";
        while (!choice_repeated_election.equals("c")) {
            choice_repeated_election = InputString(
                    "Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
            if (choice_repeated_election.equals("a")) {
                input_candidate = InputString("Please enter a username.");

                if (A3.getByUsername(input_candidate, allCandidates) != null) {
                    election_Candidates[candidate_counter] = A3.getByUsername(input_candidate, allCandidates);
                    candidate_counter++;
                } else
                    System.out.println("Invalid username");
                // for (int i = 0; i < allCandidates.length; i++) {
                // if (allCandidates[i].un == input_candidate) {
                // election_Candidates[candidate_counter] = allCandidates[i];
                // candidate_counter++;
                // }
                // }
                DisplayCurrentCandidates(election_Candidates, candidate_counter);
            } else if (choice_repeated_election.equals("b")) {
                election_Candidates[candidate_counter] = GetRandomCandidate(allCandidates);
                candidate_counter++;
                DisplayCurrentCandidates(election_Candidates, candidate_counter);
            } else if (choice_repeated_election.equals("c")) {
                int times = InputInteger("How many times shall we run the election?");
                input_candidate = InputString("Who should count the votes?");
                Candidate counter_voter = null;
                if (A3.getByUsername(input_candidate, allCandidates) != null) {
                    counter_voter = A3.getByUsername(input_candidate, allCandidates);
                } else {
                    System.out.println("Invalid input, this voter will count");
                    counter_voter = new Candidate_ec22941();
                    System.out.println(counter_voter.getName() + " decides the winner: ");
                }
                Candidate[] winners = new Candidate[times];
                Candidate[] ready_Candidates = new Candidate[candidate_counter]; // for election candidates
                for (int i = 0; i < candidate_counter; i++) {
                    ready_Candidates[i] = election_Candidates[i];
                }
                RunRepeatedElection(election_Candidates, ready_Candidates, winners, times, counter_voter);
                MostFrequentCandidates(winners);

            }
        }
    }

    // A3 main method
    public static void main(String[] args) {

        Candidate[] allCandidates = A3.getCandidateArray();

        String choice_election = "";
        while (!choice_election.equals("a")) {
            DisplayStartMessage();
            choice_election = InputString("");

            if (choice_election.equals("b")) {
                RepeatedElection(allCandidates);
            } else if (choice_election.equals("a")) {
                System.out.println("Exiting");
            }
        }
        return;
    }
}
