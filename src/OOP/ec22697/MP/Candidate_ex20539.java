package OOP.ec22697.MP;

import java.util.Scanner;

public class Candidate_ex20539 extends Candidate {


public String getName() {
    return "No name (ex20539)";
}

public String getSlogan() {
    return "No slogan (ex20539)";
}
public int voteIndex(Candidate[] candidates) {
    for (int i = 0; i < candidates.length; i++) {
        if (candidates[i] == this) {
            return i;
        }
    }
    return -1;
}



public Candidate vote(Candidate[] candidates) {
//    Scanner input = new Scanner(System.in);
//    Random random = new Random();
//
//    // Allow the user to add a specific candidate
//    System.out.println("Add a specific candidate? y/n");
//    if (input.nextLine().equals("y")) {
//        System.out.println("Enter the name of the candidate:");
//        String name = input.nextLine();
//        System.out.println("Enter the slogan of the candidate:");
//        String slogan = input.nextLine();
//        Candidate newCandidate = new Candidate() {
//            public String getName() {
//                return name;
//            }
//            public String getSlogan() {
//                return slogan;
//            }
//            public Candidate vote(Candidate[] candidates) {
//                return this;
//            }
//            public Candidate selectWinner(Candidate[] votes) {
//                return this;
//            }
//        };
//        return newCandidate;
//    }
//
//    // Add a candidate at random
//    System.out.println("Add a candidate at random? y/n");
//    if (input.nextLine().equals("y")) {
//        Candidate newCandidate = candidates[random.nextInt(candidates.length)];
//        return newCandidate;
//    }
//
//    // Allow the user to vote
//    System.out.println("Cast your vote:");
//    String name = input.nextLine();
//    for (Candidate candidate : candidates) {
//        if (candidate.getName().equals(name)) {
//            return candidate;
//        }
//    }
    
    // If the user's vote is not valid, return a vote for self
    return new Candidate_ex20539();
}

public Candidate selectWinner(Candidate[] votes) {
    Candidate winner = null;
    int maxVotes = 0;
    for (Candidate candidate : votes) {
        int count = 0;
        for (Candidate vote : votes) {
            if (vote == candidate) {
                count++;
            }
        }
        if (count > maxVotes) {
            winner = candidate;
            maxVotes = count;
        } else if (count == maxVotes && winner != null && !winner.equals(candidate)) {
            winner = null;
        }
    }
    return winner;
}

public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Candidate[] allCandidates = A3.getCandidateArray();
    Candidate[] votes = new Candidate[allCandidates.length];
    Candidate_ex20539 counter = new Candidate_ex20539();
    String inputStr;
    int howManyTimes = 0;
    
    do {
        // Offer the user options
        System.out.println("Add a candidate or run the election? c/r");
        inputStr = input.nextLine();

        if (inputStr.equals("c")) {
            Candidate newCandidate = counter.vote(allCandidates);
            if (newCandidate != counter) {
                int voteIndex = counter.voteIndex(allCandidates);
                votes[voteIndex] = newCandidate;
            }
            System.out.println("Current candidates:");
            for (Candidate candidate : votes) {
                if (candidate != null) {
                    System.out.println(candidate.getName() + ": " + candidate.getSlogan());
                }
            }
        } else if (inputStr.equals("r")) {
            // Allow the user to choose who counts the votes
            System.out.println("Who should count the votes?");
            Candidate counterCandidate = counter.vote(allCandidates);
            System.out.println("How many times shall we run the election?");
            howManyTimes = Integer.parseInt(input.nextLine());
            
            // Run the election multiple times and print the winner
            Candidate[] winners = new Candidate[howManyTimes];
            for (int i = 0; i < howManyTimes; i++) {
                Candidate[] roundVotes = new Candidate[allCandidates.length];
                for (int j = 0; j < allCandidates.length; j++) {
                    roundVotes[j] = allCandidates[j].vote(allCandidates);
                }
                winners[i] = counterCandidate.selectWinner(roundVotes);
            }
            Candidate finalWinner = counterCandidate.selectWinner(winners);
            System.out.println("The winner is: " + finalWinner.getName() + " - " + finalWinner.getSlogan());
            break;
        }
    } while (true);
}

}
