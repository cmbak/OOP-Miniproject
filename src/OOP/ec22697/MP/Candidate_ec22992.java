package OOP.ec22697.MP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

class Candidate_ec22992 extends Candidate {
    private final String[] progLanguages = {
        "c#",
        "csharp",
        "python",
        "python3",
        "c++",
        "cpp",
        "cplusplus",
        "typescript"
    };

    private static String getStringInput(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message + " ");
        return scanner.nextLine();
    }

    private static int getIntegerInput(String message) {
        try {
            return Integer.parseInt(getStringInput(message));
        } catch (Exception ex) {
            return -1;
        }
    }

    private static int getPosIntegerInput(String message) {
        int input = getIntegerInput(message);
        while (input < 0) {
            System.out.println("\nPlease provide a non-negative integer.");
            input = getIntegerInput(message);
        }
        return input;
    }

    private static void printCandidates(ArrayList<Candidate> electableCandidates) {
        if (electableCandidates.size() > 0) {
            System.out.println("\nThe candidates are:");
            for (int candidateNumber = 1; candidateNumber <= electableCandidates.size(); candidateNumber++) {
                System.out.printf("%d. %s\n", candidateNumber, electableCandidates.get(candidateNumber-1).getName());
            }
        }
    }

    private static ArrayList<Candidate> getCandidates() {
        ArrayList<Candidate> electableCandidates = new ArrayList<Candidate>();
        String option = getStringInput("Would you like to?\na) Add a specific candidate\nb) Add a candidate at random\nc) Run the election\n>");

        while (true) {
            switch (option.toLowerCase()) {
                case "a":
                    String candidateName = getStringInput("\nPlease enter the name of the candidate.\n>");
                    Candidate candidate = A3.getByUsername(candidateName, A3.getCandidateArray());
                    if (candidate != null) {
                        electableCandidates.add(candidate);
                        System.out.println("\nAdded " + candidateName + " as a voter and an electable candidate.");
                    }
                    printCandidates(electableCandidates);
                    break;
                case "b":
                    electableCandidates.add(A3.getCandidateArray()[new Random().nextInt(A3.getCandidateArray().length)]);
                    System.out.println("\nAdded a mysterious and cryptic candidate...");
                    printCandidates(electableCandidates);
                    break;
                case "c":
                    return electableCandidates;
                default:
                    System.out.println("\nAre you sure that is an option, you know the alphabet - right?");
                    break;
            }
            option = getStringInput("\nWould you like to?\na) Add a specific candidate\nb) Add a candidate at random\nc) Run the election\n>");
        }
    }

    private static Candidate handleSingleElection(Candidate countingCandidate, ArrayList<Candidate> electableCandidates) {
        Candidate[] votes = new Candidate[electableCandidates.size()];
        for (int voterIndex = 0; voterIndex < electableCandidates.size(); voterIndex++) {
            Candidate voter = electableCandidates.get(voterIndex);
            votes[voterIndex] = voter.vote(electableCandidates.toArray(new Candidate[0]));
        }
        return countingCandidate.selectWinner(votes);
    }

    private static Candidate getCountingCandidate(ArrayList<Candidate> electableCandidates) {
        Candidate countingCandidate = null;

        while (countingCandidate == null) {
            String countingCandidateName = getStringInput("\nWho should count the votes?\n>");
            countingCandidate = A3.getByUsername(countingCandidateName, electableCandidates.toArray(new Candidate[0]));
        }

        return countingCandidate;
    }

    private static HashMap<Candidate, Integer> getElectionResults(ArrayList<Candidate> electableCandidates) {
        Candidate countingCandidate = getCountingCandidate(electableCandidates);

        int electionNumber = getPosIntegerInput("\nHow many times should we run the election?\n>");
        HashMap<Candidate, Integer> electionResults = new HashMap<Candidate, Integer>();

        for (int i = 0; i < electionNumber; i++) {
            Candidate winner = handleSingleElection(countingCandidate, electableCandidates);
            electionResults.put(winner, electionResults.getOrDefault(winner, 0) + 1);
        }

        return electionResults;
    }

    private static ArrayList<Candidate> getOverallWinners(HashMap<Candidate, Integer> electionResults) {
        int currentCount = 0;
        ArrayList<Candidate> currentWinners = new ArrayList<Candidate>();

        for (Map.Entry<Candidate, Integer> electionCount : electionResults.entrySet()) {
            int candidateCount = electionCount.getValue();

            if (candidateCount >= currentCount) {
                if (candidateCount > currentCount)
                    currentWinners.clear();
                currentWinners.add(electionCount.getKey());
                currentCount = candidateCount;
            }
        }

        return currentWinners;
    }

    private static void handleMenu() {
        String option = getStringInput("Would you like to?\na) Exit\nb) Run the same election many times\n>");

        while (true)
        {
            System.out.println();
            switch(option.toLowerCase()) {
                case "b":
                    ArrayList<Candidate> electableCandidates = getCandidates();
                    HashMap<Candidate, Integer> electionResults = getElectionResults(electableCandidates);
                    ArrayList<Candidate> electionWinners = getOverallWinners(electionResults);
                    
                    System.out.print("\nThe most common winner(s) is/are ");
                    for (int i = 0; i < electionWinners.size(); i++) {
                        System.out.print(electionWinners.get(i).getName());
                        if (i < electionWinners.size() - 1) {
                            System.out.print(", ");
                        }
                    }
                    break;
                default:
                    return;
            }
            option = getStringInput("\n\nWould you like to?\na) Exit\nb) Run the same election many times\n>");
        }
    }

    public static void main(String[] args) {
        handleMenu();
    }

    public String getName() {
        return "Conor";
    }

    public String getSlogan() {
        return "Your Average C# Enjoyer";
    }

    public Candidate vote(Candidate[] candidates) {
        // No candidates to choose from, so choose myself!
        if (candidates.length == 0)
            return new Candidate_ec22992();

        // Choose a candidate based on the number of programming languages we enjoy in common.
        Candidate currentCandidate = null;
        int progLanguageCount = 0;

        for (Candidate candidate : candidates) {
            // Track how many programming languages the candidate and I both enjoy.
            int currentCount = 0;
            for (String progLanguage : progLanguages) {
                if (candidate.getSlogan().toLowerCase().contains(progLanguage)) {
                    currentCount++;
                }
            }

            // If the candidate has more languages in common with me than the last, they should be voted for!
            if (currentCount > progLanguageCount) {
                currentCandidate = candidate;
                progLanguageCount = currentCount;
            }
        }

        // None of them like any *actually good* programming languages, so just vote the first guy!
        return currentCandidate == null ? candidates[0] : currentCandidate;
    }

    public Candidate selectWinner(Candidate[] votes) {
        // If there are no candidates, I nominate myself and I win!
        if (votes.length == 0) 
            return new Candidate_ec22992();

        // Fill a hashmap with candidate entries mapped with their number of votes.
        Map<Candidate, Integer> candidateVotes = new HashMap<Candidate, Integer>();
        for (Candidate candidate : votes) {
            candidateVotes.put(candidate, candidateVotes.getOrDefault(candidate, 0) + 1);
        }

        // Enumerate the hashmap, updating the current top candidate if they have a higher number of votes.
        Map.Entry<Candidate, Integer> topCandidate = null;
        for (Map.Entry<Candidate, Integer> entry : candidateVotes.entrySet()) {
            if (topCandidate == null || entry.getValue() > topCandidate.getValue())
                topCandidate = entry;
        }

        // Return the instance of the top voted candidate.
        return topCandidate.getKey();
    }
}