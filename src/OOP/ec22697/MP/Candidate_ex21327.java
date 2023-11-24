package OOP.ec22697.MP;

class Candidate_ex21327 extends Candidate {

    public String getName() {
        return "Keyaa";
    }

    public String getSlogan() {
        return "Finger Lickin' Good!";
    }

    public Candidate vote(Candidate[] candidates) {

        // If array is empty, return instance of friend's class.
        if (candidates.length == 0)
            return new Candidate_ex21327();

        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("No more University Fees!"))
                return c;

        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getName().equals("Yunus Khan"))
                return c;

        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }

    public Candidate selectWinner(Candidate[] votes) {

        // If array is empty, return instance of friend's class.
        if (votes.length == 0)
            return new Candidate_ex21327();

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
