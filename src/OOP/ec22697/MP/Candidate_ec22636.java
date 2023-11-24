package OOP.ec22697.MP;

import java.util.Random;
import java.util.Scanner;

class Candidate_ec22636 extends Candidate {
    public String getName() {
        return "Griffin";
    }

    public String getSlogan() {
        return "OOP > Functional";
    }

    public Candidate vote(Candidate[] candidates) {
        if (candidates == null || candidates.length == 0) return null;
        Candidate r;
        r = candidates[0];
// Search for a GOAT'd user
        for (Candidate c : candidates) {
            if (c == null) continue;
            if (c.getSlogan() == null) continue;
            if (c.getSlogan().equals("OOP > Functional")) {
                return c;
            }
        }
// Vote for myself if there are no GOATs
        return r;
    }

    public Candidate selectWinner(Candidate[] votesCast) {
        if (votesCast == null || votesCast.length == 0) return null;
        int[] voteCount = new int[votesCast.length];
// If the current index is the same index as votesCast, then +1 is added to current index for voteCount
        for (int i = 0; i < votesCast.length; i++) {
            if (votesCast[i] == null) continue;
            for (int j = 0; j < votesCast.length; j++) {
                if (votesCast[j] == null) continue;
                if (votesCast[i].equals(votesCast[j])) {
                    voteCount[i]++;
                }
            }
        }
        int maxVotes = 0;
        int maxIndex = 0;
// If current voteCount > maxVotes, then maxVotes = voteCount & maxIndex = current index.
        for (int i = 0; i < voteCount.length; i++) {
            if (voteCount[i] > maxVotes) {
                maxVotes = voteCount[i];
                maxIndex = i;
            }
        }
        return votesCast[maxIndex];
    }

    public static void main(String[] args) {
        Candidate[] candidateOld = A3.getCandidateArray();
        int numberCandidates = 0;
        Candidate[] candidateNew = new Candidate[candidateOld.length];
        boolean valid = false;
        while (!valid) {
            System.out.println("Candidates are: ");
            if (numberCandidates != 0) {
                for (int i = 0; i < numberCandidates; i++) {
                    System.out.print(candidateNew[i] + "\n");
                }
            }
            if (numberCandidates == 0) {
                System.out.println("0, there are no candidates.");
            }
            char options = inputCharacter("a) add a specific candidate b) add a candidate at random c) run the election?");
            if (options == 'a') {
                addToNew(candidateNew, candidateOld, numberCandidates);
                numberCandidates++;
            } else if (options == 'b') {
                randomCandidate(candidateNew, candidateOld, numberCandidates);
                numberCandidates++;
            } else if (options == 'c') {
                election(candidateNew, candidateOld, numberCandidates);
                boolean condition = false;
                while (!condition) {
                    char option = inputCharacter("Would you like to a) start another election b) quit?");
                    if (option == 'b') {
                        valid = true;
                        condition = true;
                    } else
                        System.out.println("Invalid option. Please try again.");
                }
            } else
                System.out.println("Invalid option. Please try again.");
        }
    }

    public static void addToNew(Candidate[] candidateNew, Candidate[] candidateOld, int candidateIndex) {
        String name = inputString("Please enter a username. eg \"ec22636\"");
        Candidate newCandidateChoice = A3.getByUsername(name, candidateOld);
        if (newCandidateChoice == null) {
            System.out.println("We could not find this candidate.");
        } else {
            candidateNew[candidateIndex] = newCandidateChoice;
        }
    }

    public static void randomCandidate(Candidate[] newRandom, Candidate[] candidateOld, int candidateIndex) {
        Random randomRandom = new Random();
        int randomInt = randomRandom.nextInt(candidateOld.length);
        Candidate randomCandidate = candidateOld[randomInt];
        newRandom[candidateIndex] = randomCandidate;
    }

    public static void election(Candidate[] candidatesOld, Candidate[] allCandidates, int length) {
        Candidate[] candidatesNew = new Candidate[length];
        System.arraycopy(candidatesOld, 0, candidatesNew, 0, length);
        String voteCounterName = inputString("Who should count the votes?");
        Candidate chosen = A3.getByUsername(voteCounterName, allCandidates);
        while (chosen == null) {
            System.out.println("Could not find this candidate.");
            voteCounterName = inputString("Who should count the votes?");
            chosen = A3.getByUsername(voteCounterName, allCandidates);
        }
        int iterations = inputInt("How many times do you want to run the election?");
        Candidate[] winners = new Candidate[iterations];
        for (int i = 0; i < iterations; i++) {
            Candidate[] vote = new Candidate[allCandidates.length];
            for (int j = 0; j < allCandidates.length; j++) {
                try {
                    vote[j] = allCandidates[j].vote(candidatesNew);
                } catch (Exception e) {
                    System.out.println("Error: " + allCandidates[j].getName() + " did not vote.");
                }
            }
            winners[i] = chosen.selectWinner(vote);
        }
        int highestCount = 0;
        Candidate[] winner = new Candidate[candidatesNew.length];
        int winnerCount = 1;
        for (int i = 0; i < candidatesNew.length; i++) {
            int counter = 0;
            for (int j = 0; j < winners.length; j++) {
                if (winners[j].equals(candidatesNew[i])) {
                    counter++;
                }
            }
            if (counter > highestCount) {
                highestCount = counter;
                winner[0] = candidatesOld[i];
                winnerCount = 1;
            } else if (counter == highestCount) {
                winnerCount++;
                winner[winnerCount - 1] = candidatesOld[i];
            }
        }
        if (winnerCount == 1) {
            System.out.println("The most common winner is " + winner[0] + ".");
            System.out.println("There were no other winners.");
        } else {
            System.out.println("The most common winners are:");
            for (int i = 0; i < winnerCount; i++) {
                System.out.println(winner[i]);
            }
        }
        ;
    }

    public static String inputString(String string) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(string);
        return scanner.nextLine();
    }

    public static int inputInt(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return Integer.parseInt(scanner.nextLine());
    }

    public static char inputCharacter(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        char input = scanner.nextLine().charAt(0);
        return input;
    }
}
