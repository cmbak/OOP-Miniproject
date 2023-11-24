package OOP.ec22697.MP;// File Candidate_ec22592.java
//
// Machine generated stub for Assignment 2

import java.util.Random;

class Candidate_ec22592 extends Candidate {

    public String getName() {
        return "Messi";
    }

    public String getSlogan() {
        return "Ankara Messi!";
    }

    public Candidate vote(Candidate[] candidates) {
        if (candidates.length == 0)
            return new Candidate_ec22592();

        for (Candidate c: candidates) {
            if (c.getSlogan().equals("GOAT")) {
                return c;
            }
        }

        for (Candidate c: candidates) {
            if (c.getName().equals("Messi")) {
                return c;
            }
        }

        Random r = new Random();
        int c = r.nextInt(candidates.length);
        return candidates[c];
    }

    public Candidate selectWinner(Candidate[] votes) {

        if (votes.length == 0)
            return new Candidate_ec22592();

        int highest = 0;
        Candidate winner = votes[0];
        for (Candidate c: votes) {
            int count = 0;
            for (Candidate z: votes) {
                if (z == c) {
                    count++;
                }
            }

            if (count >= highest) {
                highest = count;
                winner = c;
            }
        }

        return winner;
    }
}
