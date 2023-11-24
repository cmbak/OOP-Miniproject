package OOP.ec22697.MP;// File Candidate_ec22818.java
//   
// Machine generated stub for Assignment 3

class Candidate_ec22818 extends Candidate {

    public static void main(String[] args) {

        Candidate[] allContributions = A3.getCandidateArray();
        System.out.println(allContributions[42].getSlogan());
        System.out.println(A3.getByUsername("ec22818", allContributions).getSlogan());
        System.out.println(allContributions[479].un);

    }

    public String getName() {
        return "Andrei Neagoe";
    }

    public String getSlogan() {
        return "Progress and Unity!";
    }

    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22818();

        if (candidates.length != 0) {
            int maxVotes = -1;
            for (Candidate c : candidates) {
                int count = 0;
                for (Candidate x : candidates) {
                    if (x == c) {
                        count++;
                    }
                }
                if (count > maxVotes) {
                    maxVotes = count;
                    r = c;
                }
            }
        }

        return r;
    }

    public Candidate selectWinner(Candidate[] votes) {

        // If array is empty, return instance of this class.
        if (votes.length == 0)
            return new Candidate_ec22818();

        // Default to first vote, but this will be over-written.
        Candidate currentWinner = votes[0];

        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {

            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;

            if (count > highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }

        return currentWinner;
    }

}
