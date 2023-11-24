package OOP.ec22697.MP;// File Candidate_ec22547.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22547 extends Candidate {
    
    public String getName() {
        return "Mr Best";
    }
    
    public String getSlogan() {
        return "Better than the rest!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22547();
        int longestSloganLength = 0;
        
        //only if the candidate list is not empty, will the the candidate with the longest slogan be voted
        if (candidates.length != 0)
        {
            r = candidates[0];
            for (int i=0; i < candidates.length; i++)
            {
                String currentSlogan = candidates[i].getSlogan();
                if ( currentSlogan.length() > longestSloganLength )
                {
                    longestSloganLength = currentSlogan.length();
                    r = candidates[i];
                }
            }
        }
  
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22547();
        
        if (votes.length != 0) // if the list is empty then a new instance of my candidate will win the vote
        {
            r = votes[0];      // otherwise the candidate the winner is defaulted to the first vote and the candidate with the highest number of votes wins
 
            // Add code that either counts the votes
            // or uses some other way to return an element 
            // of the array vote as the winner of an election.
        
            int highestCount = 0;
            for (int c=0; c < votes.length; c++)
            {
                int count = 0;
                for (int x=0; x < votes.length; x++)
                {
                    if (votes[x] == votes[c])
                        count++;
                }
                if (count >= highestCount)
                {
                    highestCount = count;
                    r = votes[c];
                }
             }

        }
        
        return r;
    }
    
}
