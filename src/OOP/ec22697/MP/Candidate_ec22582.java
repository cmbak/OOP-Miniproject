package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22582 extends Candidate {

    private static <T> void pr(T s) {
        System.out.println(s);
    }

    private static Scanner sc() {
        return new Scanner(System.in);
    }

    public static void main(String[] args) {
        Candidate[] allCandidates = A3.getCandidateArray();
        Candidate[] chosenCandidates = new Candidate[allCandidates.length];
        pr("= Running Repeated Elections =");
        boolean exit = false;
        while (!exit) {
            pr("Candidates are");
            if (chosenCandidates[0] == null) {
                pr("None");
            } else {
                int count = 0;
                for (Candidate c : chosenCandidates) {
                    if (c != null) {
                        count++;
                        pr(count + ". " + c.getName());
                    }
                }
            }
            displayMenu();
            String option = getOption();

            switch (option) {

                case "a":
                    addSpecificCandidate(allCandidates, chosenCandidates);
                    break;

                case "b":
                    addRandomCandidate(allCandidates, chosenCandidates);
                    break;

                case "c":
                    runElection(allCandidates, chosenCandidates);
                    exit = true;
                    break;

                default:
                    pr("Option " + option + "' not available.");
            }
        }

    }

    public static void runElection(Candidate[] allCandidates, Candidate[] chosenCandidates) {
        Candidate[] votes = new Candidate[allCandidates.length];

        int count = 0;
        for (Candidate c : chosenCandidates) {
            if (c != null) {
                count++;
            }
        }

        Candidate[] temp = new Candidate[count];

        for (int i = 0; i < count; i++) {
            temp[i] = chosenCandidates[i];
        }

        pr("Who should count the votes?");
        String candidateName = sc().nextLine();
        Candidate judge = A3.getByUsername(candidateName, allCandidates);
        pr(judge.getName());

        pr("How many times shall we run the election?");
        int noOfElections = sc().nextInt();

        Candidate[] winners = new Candidate[noOfElections];

        for (int i = 0; i < noOfElections; i++) {
            int index = 0;
            for (Citizen voter : allCandidates) {
                try {
                    votes[index] = voter.vote(temp);
                    index++;
                } catch (Exception e) {
                    pr(e);
                }
            }
            winners[i] = judge.selectWinner(votes);
        }
        Candidate winner = mostCommonElement(winners);
        pr("Most common winner is " + winner.getName());

    }

    public static void addRandomCandidate(Candidate[] allCandidates, Candidate[] chosenCandidates) {

        int count = 0;
        for (int i = 0; i < chosenCandidates.length; i++) {
            if (chosenCandidates[i] != null) {
                count++;
            } else {
                break;
            }
        }
        Random random = new Random();
        int randInt = random.nextInt(allCandidates.length);
        Candidate randomCandidate = allCandidates[randInt];
        chosenCandidates[count] = randomCandidate;

    }

    public static void addSpecificCandidate(Candidate[] allCandidates, Candidate[] chosenCandidates) {
        pr("Please enter a username.");
        String candidateName = sc().nextLine();
        Candidate candidate = A3.getByUsername(candidateName, allCandidates);
        for (int i = 0; i < chosenCandidates.length; i++) {
            if (chosenCandidates[i] == null) {
                chosenCandidates[i] = candidate;
                break;
            }
        }
    }

    public static Candidate mostCommonElement(Candidate[] arr) {
        Candidate mostCommon = arr[0];
        int highestFrequency = 1;
        int currentFrequency;

        for (int i = 0; i < arr.length; i++) {
            currentFrequency = 1;
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    currentFrequency++;
                }
            }
            if (currentFrequency > highestFrequency) {
                mostCommon = arr[i];
                highestFrequency = currentFrequency;
            }
        }

        return mostCommon;
    }

    public static void displayMenu() {
        pr("Would you like to a) add a specific candidate b) add a candidate at random c) run the election?");
    }

    public static String getOption() {
        return sc().nextLine();
    }

    public String getName() {
        return "Asad Ali Khan";
    }

    public String getSlogan() {
        return "Hamood Habibi Hamood";
    }

    public Candidate vote(Candidate[] candidates) {

        if (candidates.length == 0)
            return new Candidate_ec22582();

        for (Candidate c : candidates)
            if (c.getSlogan().equals("Hamood Habibi Hamood"))
                return c;

        for (Candidate c : candidates)
            if (c.getName().equals("Asad"))
                return c;

        return candidates[0];
    }

    public Candidate selectWinner(Candidate[] votes) {

        if (votes.length == 0)
            return new Candidate_ec22582();

        Candidate currentWinner = votes[0];

        int highestCount = 0;
        for (Candidate c : votes) {

            int count = 0;
            for (Candidate v : votes)
                if (v == c)
                    count++;
            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }

        return currentWinner;
    }

}
