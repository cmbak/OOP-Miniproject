package OOP.ec22697.MP;// File Candidate_ec22701.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22701 extends Candidate {

    public String getName() {
        return "Hamood Habibi";
    }

    public String getSlogan() {
        return "Wake up Hamoody!";
    }

    public Candidate vote(Candidate[] candidates) {

        // If array is empty, return instance of friend's class.
        if (candidates.length == 0) 
            return new Candidate_ec22701();

        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Wake up Hamoody!"))
                return c;

        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Ahmed Habibi"))
                return c;

        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }

    public Candidate selectWinner(Candidate[] votes) {

        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22701();

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
