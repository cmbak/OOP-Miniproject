package OOP.ec22697.MP;// File Candidate_ec22514.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22514 extends Candidate {
    
    public String getName() {
        return "Rares-Gabriel";
    }
    
    public String getSlogan() {
        return "Empowering the future, together.";
    }
    
    public Candidate vote(Candidate[] candidates) {
        Candidate r = new Candidate_ec22514();
        
        if (candidates.length == 0) 
            return r;
        else
            r = candidates[0];
 
        // Add code that inspects the array candidates
        // calling the various methods of the Candidate objects
        // and finds a Candidate object to vote for.
        for (int i=0; i<candidates.length; i++){
            if (candidates[i].getName().equals("Rares-Gabriel"))
                return r;
        }
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = new Candidate_ec22514();
        
        if (votes.length == 0)
            return r;
        else 
            r = votes[0];
        int highestCount = 0;
        for (int i = 0; i < votes.length; i++) {
            Candidate c = votes[i];
            int count = 0;
            for (int j = 0; j < votes.length; j++) {
                Candidate x = votes[j];
                if (x == c) {
                count++;
                }       
            }
            if (count > highestCount) {
                highestCount = count;
                r = c;
            }
        }

        // Add code that either counts the votes
        // or uses some other way to return an element 
        // of the array vote as the winner of an election.
        return r;
    }
    
}
