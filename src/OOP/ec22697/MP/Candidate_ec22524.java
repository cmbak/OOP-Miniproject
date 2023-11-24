package OOP.ec22697.MP;// File Candidate_ec22524.java

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22524 extends Candidate {

    // return name
    public String getName() {
        return "Ethan";
    }

    // return slogan
    public String getSlogan() {
        return "Let's Go!";
    }

    // voting for a candidate.
    public Candidate vote(Candidate[] candidates) {

        if (candidates.length == 0)
            return new Candidate_ec22524();

        for (Candidate c : candidates)
            if (c.getSlogan().equals("Chicago is the Rome of America."))
                return c;

        for (Candidate c : candidates)
            if (c.getName().equals("Walter"))
                return c;

        Random rand = new Random();
        int c = rand.nextInt(candidates.length);
        return candidates[c];
    }

    // selecting a winner at random
    public Candidate selectWinner(Candidate[] votes) {

        if (votes.length == 0)
            return new Candidate_ec22524();

        Random rand = new Random();
        int c = rand.nextInt(votes.length);
        return votes[c];
    }

    // add a specific candidate to the election
    public static Candidate[] addCandidate(Candidate[] electionCandidates, Candidate[] allContributions, int indexPos) {
        Scanner scanner = new Scanner(System.in);
        boolean found = false;
        System.out.println("Enter a Username to add them to the Election: ");
        String username = scanner.nextLine();
        Candidate toAdd = A3.getByUsername(username, allContributions);
        for (Candidate allContribution : allContributions) {
            if (toAdd == allContribution) {
                try {
                    electionCandidates[indexPos] = toAdd;
                    System.out.println("Candidate Added.\n");

                } catch (Exception e) {
                    System.out.println("Something went wrong.");
                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Candidate entered Not Found.\n");
        }
        return electionCandidates;
    }

    // add a random candidate to the election
    public static Candidate[] addRandomCandidate(Candidate[] electionCandidates, Candidate[] allContributions, int indexPos) {
        Random random = new Random();
        int randInt = random.nextInt(allContributions.length);
        electionCandidates[indexPos] = allContributions[randInt];
        return electionCandidates;
    }

    // conduct an election
    public static void conductElection(Candidate[] electionCandidates, Candidate[] allContributions) {
        try {
            Scanner scanner = new Scanner(System.in);
            boolean done1 = false;
            int indexPos2 = 0;
            while (!done1) {
                if (electionCandidates[indexPos2] == null) {
                    done1 = true;
                } else {
                    indexPos2 = indexPos2 + 1;
                }
            }
            System.out.println("Check1");

            Candidate[] electionCandidatesArray = new Candidate[indexPos2 + 1];

            System.arraycopy(electionCandidates, 0, electionCandidatesArray, 0, indexPos2);

            Candidate electionCounter = null;
            boolean found1 = false;
            while (!found1) {
                System.out.println("Who should count the votes?");
                String userAnswer1 = scanner.nextLine();
                electionCounter = A3.getByUsername(userAnswer1, allContributions);
                for (Candidate allContribution : allContributions) {
                    if (electionCounter == allContribution) {
                        found1 = true;
                        break;
                    }

                }
                if (!found1) {
                    System.out.println("Election Counter Not Found.");
                }
            }
            System.out.println("How many times shall we run the election?");
            int runs = Integer.parseInt(scanner.nextLine());
            Candidate[] winners = new Candidate[runs];

            assert electionCounter != null;
            for (int k = 0; k < runs; k++) {
                winners[k] = electionCounter.selectWinner(electionCandidatesArray);
            }

            Candidate mostOccurringCandidate = null;
            int mostOccurrences = 0;
            for (int i = 0; i < winners.length; i++) {
                int occurrences = 1;
                for (int j = i + 1; j < winners.length; j++) {
                    if (winners[i] == winners[j]) {
                        occurrences++;
                    }
                }
                if (occurrences > mostOccurrences) {
                    mostOccurrences = occurrences;
                    mostOccurringCandidate = winners[i];
                }
            }
            assert mostOccurringCandidate != null;
            System.out.println("The most commonly occurring candidate was: " + mostOccurringCandidate.getName());
        }
        catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }

    public static void main(String[] args) {
        Candidate[] allContributions = A3.getCandidateArray();
        int totalContributions = allContributions.length;
        Candidate[] electionCandidates = new Candidate[totalContributions];
        int indexPos = 0;

        int done = 0;
        while (done == 0){
            System.out.println("Would you like to: \na) Add a Specific Candidate.\nb) Add a Candidate at Random.\nc) Run the Election?\nd) Exit.");
            Scanner scanner = new Scanner(System.in);
            String userAnswer = scanner.nextLine();

            if (userAnswer.equals("a")) {
                addCandidate(electionCandidates, allContributions, indexPos);
                indexPos++;
            } else if (userAnswer.equals("b")) {
                addRandomCandidate(electionCandidates, allContributions, indexPos);
                System.out.println("Random Candidate Added.\n");
                indexPos++;
            } else if (userAnswer.equals("c")) {
                conductElection(electionCandidates, allContributions);
            } else if (userAnswer.equals("d")) {
                done = 1;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }

}
