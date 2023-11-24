package OOP.ec22697.MP;// File Candidate_ec22419.java

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22419 extends Candidate {

    public static void main(String[] args) {

        // based on the users choice, the given method will be executed
        Scanner scanner = new Scanner(System.in);
        boolean val = true;
        while (val) {
            System.out.print(
                    "Would you like to a) add a specific candidate b) add a candidate at random c) run the election? d)exit ");
            String ans = scanner.nextLine();
            // option d is to quit the program
            if (ans.equals("d")) {
                val = false;
            } else {
                requiredTask(ans);
            }
        }
    }

    // based on the choice the user selected, the given method will run.
    public static void requiredTask(String option) {
        Candidate[] allContributions = A3.getCandidateArray();
        Candidate[] addCandidates = new Candidate[allContributions.length];
        int x = 0;
        if (option.equals("a")) {
            addCandidates = addCandidate(allContributions, addCandidates, x);
            x++;
        } else if (option.equals("b")) {
            addCandidates = addRandomCandidate(allContributions, addCandidates, x);
            x++;
        } else if (option.equals("c")) {
            runElection(allContributions, x, addCandidates);
        } else {
            System.out.println("Invalid input.");
        }
    }

    // will add an candidate into the array based on the user input.
    public static Candidate[] addCandidate(Candidate[] allCandidates, Candidate[] addCandidates, int x) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter the candidate's username: ");
            String inputReceived = scanner.nextLine();
            System.out.println("Input Received is " + inputReceived);
            Candidate CandidateName = A3.getByUsername(inputReceived, allCandidates);

            System.out.println("Added candidate " + CandidateName.getName());
            addCandidates[x] = CandidateName;
        } catch (NullPointerException e) {
            System.out.println("Invalid Input.");
        }
        return addCandidates;
    }

    // will randomly add an candidate into the array.
    public static Candidate[] addRandomCandidate(Candidate[] allCandidates, Candidate[] addCandidates, int x) {
        Random random = new Random();
        int ranNum = random.nextInt(allCandidates.length);
        addCandidates[x] = allCandidates[ranNum];
        System.out.println("Added candidate " + addCandidates[x].getName());
        return addCandidates;
    }

    // will run the election based on the number of candidates currently stored in
    // the array.
    public static void runElection(Candidate[] allCandidates, int x, Candidate[] addCandidates) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username who should count votes: ");
        String inputReceived = scanner.nextLine();

        try {
            Candidate voteCounter = A3.getByUsername(inputReceived, allCandidates);

            System.out.print("How many times should we run the election? ");
            int noTimes = scanner.nextInt();

            Candidate[] listOfVotes = new Candidate[noTimes * addCandidates.length];

            for (int electionNum = 0; electionNum < noTimes; electionNum++) {

                for (int i = 0; i < addCandidates.length; i++) {
                    try {

                        listOfVotes[(electionNum * allCandidates.length) + i] = addCandidates[i].vote(addCandidates);
                    } catch (Exception e) {
                        listOfVotes[(electionNum * allCandidates.length) + i] = new Candidate_ec22419();
                    }
                }
            }
            Candidate winner = voteCounter.selectWinner(listOfVotes);
            System.out.println("The winner is: " + winner.getName());
        } catch (NullPointerException e) {
            System.out.println("Invalid Username");
        } catch (NumberFormatException nf) {
            System.out.println("Invalid Number Input");
        }

    }

    // Stub from A2 ->
    public String getName() {
        return "Naomi";
    }

    public String getSlogan() {
        return "End Global Warming!";
    }

    public Candidate vote(Candidate[] candidates) {

        if (candidates.length == 0) {
            return new Candidate_ec22419();
        }

        for (Candidate c : candidates) {
            if (c.getSlogan().equals("End Global Warming!")) {
                return c;
            }
        }

        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("David")) {
                return c;
            }

        // Otherwise, default to last candidate on list.
        return candidates[candidates.length - 1];
    }

    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0) {
            return new Candidate_ec22419();
        }

        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];

        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {

            int count = 0;
            for (Candidate x : votes) {
                if (x == c) {
                    count++;
                }
            }
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }

        return currentWinner;
    }

}
