package OOP.ec22697.MP;


// File Candidate_ec22485.java
//
// Machine generated stub for Assignment 2
import java.util.Random;

class Candidate_ec22485 extends Candidate {
    
    public String getName() {
        return "Mickey";
    }
    
    public String getSlogan() {
        return "Free education for everyone!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        //self given name is searched from the list 
        for (Candidate c: candidates)
            if (c.getName().equals ("Mickey"))
                return c;
        
        //a friend is searched
        for (Candidate c : candidates)
            if (c.getName().equals("Mr. Bean"))
                return c;
        
        //or else, randomly chosen
        Random person = new Random();
        int p = person.nextInt(candidates.length);
        return candidates[p];
    }
    
    public static int numberOfTimes (Candidate x, Candidate[] a) {
        int r = 0;
        
        for (int i=0; i<a.length; i++) {
            if (x==a[i]) {
                r=r+1;
            }
        }
        
        return r;
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        Candidate r = votes [0];
        int largestCountSoFar = 0;
    
        for (int i=0; i<votes.length; i++)
        {
            if (numberOfTimes (votes[i], votes) > largestCountSoFar)
            {
                r = votes[i];
                largestCountSoFar = largestCountSoFar +1;
            }
        }
        
        return r;
    }
    
}
