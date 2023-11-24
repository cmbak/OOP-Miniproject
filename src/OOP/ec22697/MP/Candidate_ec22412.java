package OOP.ec22697.MP;

import java.util.Scanner;
import java.util.Random;

class Candidate_ec22412 extends Candidate {
    public String getName() {
        return "Hopeful";
    }

    public String getSlogan() {
        return "Miracle if this gets voted";
    }

    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0) {
            return new Candidate_ec22412();
        }

        for (Candidate c : candidates) {
            if (c.getName().equals("Hopeful")) {
                return c;
            }
        }

        for (Candidate c : candidates) {
            if (c.getSlogan().equals("Miracle if this gets voted")) {
                return c;
            }
        }

        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c]; 
    }

    public Candidate selectWinner(Candidate[] votes) {
        if (votes.length == 0) {
            return new Candidate_ec22412();
        }

        Candidate currentWinner = votes[0];
        int highestCount = 0;

        for (Candidate c : votes) {
            int count = 0;
            for (Candidate x : votes) {
                if (x == c) {
                    count++;
                }
            }
            if (count >= highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }
        return currentWinner;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String option = "";

        Candidate[] candidates = A3.getCandidateArray();

        while (option != "a") {
            System.out.println("Would you like to");
            System.out.println("a) exit ");
            System.out.println("b) random 1-election ");
            System.out.println("c) random 2-election");

            option = scanner.nextLine();

            if (option.equals("a")) {
                option = "a";
            }
            else if (option.equals("b")) {
                election(candidates);
            }
            else if (option.equals("c")) {
                twoelection(candidates);
            }
        }
    }

    private static void election (Candidate[] allCandidates) {
        for (int i=0;i<10;i++) {

            Candidate winner = (new Candidate_ec22412()).selectWinner(getVotes(allCandidates));

            System.out.println("The winner is "+ allCandidates[i].getName() + allCandidates[i].getSlogan());
        }
    }

    private static void twoelection (Candidate[] allCandidates) {
        for (int i=0;i<10;i++) {

            Candidate winner = (new Candidate_ec22412()).selectWinner(getVotes(allCandidates));

            System.out.println("The winner is "+ allCandidates[i].getName() + allCandidates[i].getSlogan());
        }

        for (int i=0;i<10;i++) {

            Candidate winner = (new Candidate_ec22412()).selectWinner(getVotes(allCandidates));

            System.out.println("The winner is "+ allCandidates[i].getName() + allCandidates[i].getSlogan());
        }
    }

    private static Candidate[] getVotes(Candidate[] candidates) { 

        Candidate[] temp = new Candidate[candidates.length];    
        int count = 0;

        Candidate[] votes = new Candidate[count];    
        for (int i=0; i<count; i++) {
          votes[i] = temp[i];
        }

        return votes;
    }
}
