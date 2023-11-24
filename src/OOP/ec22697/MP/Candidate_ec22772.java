package OOP.ec22697.MP;// File Candidate_ec22772.java
//
// Updated version for Assignment 3

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22772 extends Candidate {

    // Take in a String message and returns an integer inputted by the user
    public static int inputInt (String message)
    {
        int answer;
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);
        answer = scanner.nextInt();

        return answer;
    }

    // Take in a String message and returns a String inputted by the user
    public static String inputStr (String message)
    {
        String answer;
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);
        answer = scanner.nextLine();

        return answer;
    }

    public String getName() {
        return "Shrek";
    }

    public String getSlogan() {
        return "Onion is love, onion is life";
    }

    public static void main(String[] args) {
        String userChoice;
        int count = 0;
        Candidate[] AllCandidates = A3.getCandidateArray();
        Candidate[] CandidatesList = new Candidate[AllCandidates.length];
        String username;
        boolean Exit = false;
        System.out.println("= Running Repeated election =");
        while (!Exit)
        {
            System.out.println("Candidates are");
            if (count == 0) {
                System.out.println("None");
            }
            else {
                for(int i = 0; i < count;i++) {
                    System.out.println(CandidatesList[i].getName());
                }
            }

            userChoice = inputStr("Would you like to a) add a specific candidate b) add a candidate at random c) run the election d) exit?");

            switch(userChoice) {
                case ("a"):
                    username = inputStr("Enter username");
                    for (Candidate candidate : AllCandidates) {
                        if (candidate.un.equals(username)) {
                            CandidatesList[count] = candidate;
                        }
                    }
                    count += 1;
                    break;
                case ("b"):
                    Random rand = new Random();
                    int randomCand = rand.nextInt(AllCandidates.length);
                    CandidatesList[count] = AllCandidates[randomCand];
                    count += 1;
                    break;
                case ("c"):
                    String userCounter = inputStr("Who should count the votes");
                    int timesRan = inputInt("How many times shall we run the election");
                    for (Candidate allCandidate : AllCandidates) {
                        if (allCandidate.un.equals(userCounter)) {
                            Candidate[] FinalCandidates = new Candidate[count];
                            for(int j = 0; j < timesRan; j++) {
                                for (int z = 0; z < count; z++) {
                                    FinalCandidates[z] = CandidatesList[z];
                                }
                            }
                            Candidate winner = allCandidate.selectWinner(FinalCandidates);
                            System.out.println("The most common winner is " + winner.getName());
                            Exit = true;
                        }
                    }
                    break;
                case ("d"):
                    System.exit(0);
                    break;
            }
        }
    }

    public Candidate vote(Candidate[] candidates) {

        // If array is empty, return instance of friend's class.
        if (candidates.length == 0)
            return new Candidate_jpp319457();

        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("More trees!"))
                return c;

        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Kim"))
                return c;

        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }

    public Candidate selectWinner(Candidate[] votes) {

        // If array is empty, return instance of friend's class.
        if (votes.length == 0)
            return new Candidate_jpp319457();

        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];

        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {

            int count = 0;
            for (Candidate x : votes)
                if (x.equals(c))
                    count++;
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }

        return currentWinner;
    }

}
