package OOP.ec22697.MP;// File Candidate_ec22857.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22857 extends Candidate {
    
    public String getName() {
        return "Kal";
    }
    
    public String getSlogan() {
        return "March!";
    }
    
    public Candidate vote(Candidate[] votes) {

        // If array is empty, return instance of friend's class.
        if (votes.length == 0)
            return new Candidate_ec22857();

        int middleVote = votes.length/2;
        return votes[middleVote];
    }

    
     public Candidate selectWinner(Candidate[] votes) {

        int topVote = 0;
        Candidate TopWinner = new Candidate_ec22857();

        // If array is empty, return instance of friend's class.
        if (votes.length == 0)
        {
            return new Candidate_ec22857();
        }

        // Default to first vote, but this will be over-written.



        for (Candidate x : votes) {

            int count = 0;

            for (Candidate y : votes)
                if (x == y)
                {
                    count = count + 1;
                }
            if (count > topVote) {
                topVote = count;
                TopWinner = x;
            }
        }

        return TopWinner;
    }

}
