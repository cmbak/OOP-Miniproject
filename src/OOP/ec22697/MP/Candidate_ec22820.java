package OOP.ec22697.MP;
// File Candidate_ec22820.java
// Machine generated stub for Assignment 2

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Random;

class Candidate_ec22820 extends Candidate {
    public String getName() {
        return "Roni Kazmirci";
    }

    public String getSlogan() {
        return "BaboonLover991";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22820();

        if (candidates.length != 0)
            r = candidates[0];

        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.

        return r;
    }

    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22820();

        if (votes.length != 0)
            r = votes[0];

        // Add code that either counts the votes
        // or uses some other way to return an element
        // of the array vote as the winner of an election.

        return r;
    }

    public static void printCandidates(Candidate[] candidates) {
        int counter = 1;
        for (Candidate c : candidates) {
            if (c != null) {
                System.out.println(counter + ". " + c.un);
                counter++;
            }
        }

        if (counter == 1) {
            System.out.println("None");
        }
    }

    public static String getInput(String msg) {
        Scanner s = new Scanner(System.in);
        System.out.println(msg);
        return s.nextLine();
    }

    private static Candidate[] randomCandidates(int number, Candidate[] ca) {

        int seed = getInt("Enter random seed or 0 for no seed. (The same seed will produce the same candidates.)");

        Candidate[] candidates = new Candidate[number];

        Random rs = (seed == 0 ? new Random() : new Random(seed));

        pr("The candidates are ");
        pr(number);
        for (int i = 0; i < number; i++) {
            do {
                candidates[i] = ca[rs.nextInt(ca.length)];
            } while (!proper(candidates[i]));

            pr(candidates[i].getName());
        }
        return candidates;
    }

    private static <T> void pr(T s) {
        System.out.println(s);
    }

    private static boolean proper(Person p) {
        return !(p.getName().length() > 6 &&
                p.getName().substring(0, 7).equals("No name"));
    }

    private static Scanner sc() {
        return new Scanner(System.in);
    }

    private static int getInt(String q) {
        pr(q);
        String t = sc().nextLine();
        return (!isNum.matcher(t).matches() ? 0 : Integer.parseInt(t));
        // return sc().nextInt();
    }

    private static char getChar(String q) {
        pr(q);
        String t = sc().nextLine();
        return (t.length() == 0 ? ' ' : t.charAt(0));
        // return sc().nextLine().charAt(0);
    }

    private static boolean showErrors = false;

    private static Candidate[] getVotes(Candidate[] candidates, Citizen[] va) {

        Candidate[] temp = new Candidate[va.length];
        int count = 0;
        for (int i = 0; i < va.length; i++) {
            if (proper(va[i])) {
                try {
                    temp[count] = va[i].vote(candidates);
                    // System.err.print(" "+temp[count].getName());
                    count++; // Won't happen if vote throws an exception.
                } catch (Exception e) { // 'Spoilt ballot'
                    if (showErrors)
                        e.printStackTrace();
                }
            }
        }

        Candidate[] votes = new Candidate[count];
        for (int i = 0; i < count; i++)
            votes[i] = temp[i];
        return votes;
    }

    private static Pattern isNum = Pattern.compile("\\d+");

    private static void randomElection(Candidate[] possibleCandidates, Citizen[] va) {

        int n = getInt("How many candidates?");
        Candidate[] candidates = randomCandidates(n, possibleCandidates);

        do {
            // 10 elections.
            for (int i = 0; i < 10; i++) {

                Candidate w = (new Candidate_eey577()).selectWinner(
                        getVotes(candidates, va));
                pr("The winner is " + w.getName() + " (" + w.un + "): " + w.getSlogan());
            }
        } while (getChar("More (y/n)?") == 'y');

    }

    public static void main(String[] args) {
        /*
         * Scanner s = new Scanner(System.in);
         * Candidate[] allCandidates = A3.getCandidateArray();
         * int candidateIndex = 0;
         * Candidate[] candidates = new Candidate[545];
         * String input = "";
         * String un;
         * 
         * while (!input.equals("a")) {
         * String whoCounts = "0";
         * input = getInput("\nWould you like to: \na) exit | b) run an election");
         * 
         * if (input.equals("a")) {
         * System.out.println("Exiting");
         * 
         * } else if (input.equals("b")) {
         * System.out.println("\n= Running Repeated Elections = \nCandidates are");
         * printCandidates(candidates);
         * 
         * while (!input.equals("c")) {
         * input = getInput(
         * "\nWould you like to a) add a specific candidate | b) add a candidate at random | c) run the election?"
         * );
         * 
         * if (input.equals("b")) {
         * candidates[candidateIndex] = allCandidates[(int) (Math.random() * 545)];
         * candidateIndex++;
         * 
         * printCandidates(candidates);
         * } else if (input.equals("a")) {
         * un = getInput("Please enter a username");
         * candidates[candidateIndex] = A3.getByUsername(un, allCandidates);
         * candidateIndex++;
         * 
         * } else if (input.equals("c")) {
         * randomElection(candidates, candidates);
         * }
         * }
         * }
         * }
         */
        randomElection(A3.getCandidateArray(), A3.getCandidateArray());
    }

}
