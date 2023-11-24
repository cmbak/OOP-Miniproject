package OOP.ec22697.MP;// File Candidate_ec21403.java
//
// Machine generated stub for Assignment 2

class Candidate_ec21403 extends Candidate {
    
    public String getName() {
        return "Harry Potter";
    }
    
    public String getSlogan() {
        return "Hogwarts";
    }
    
    public Candidate vote(Candidate[] candidates) 
    {
        Candidate r = new Candidate_ec21403();
        
        if (candidates.length == 0) 
            return new Candidate_ec21403();

        // Search for a like minded candidate.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Hogwarts"))
                return c;

        // Otherwise, search for a friend.
        for (Candidate c : candidates)
            if (c.getSlogan().equals("Wakanda Forever"))
                return c;

        // Otherwise, default to last candidate on list.
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) 
    {
        Candidate r = new Candidate_ec21403();
        
        if (votes.length == 0) 
            return new Candidate_ec21403();

        // Default to first vote
        Candidate currentWinner = votes[0];

        // Count the votes for each object in the array,
        // selecting one with the most.
        int highestCount = 0;
        for (Candidate c : votes) {

            int count = 0;
            for (Candidate x : votes)
                if (x == c)
                    count++;
            if (count >= highestCount) {
                highestCount = count;
                currentWinner = c;
            }
        }

        return currentWinner;
    }
    
}
