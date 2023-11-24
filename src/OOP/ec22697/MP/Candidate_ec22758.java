package OOP.ec22697.MP;// File Candidate_ec22758.java
//
// Machine generated stub for Assignment 2

//heloooooooo
class Candidate_ec22758 extends Candidate {
    
    public String getName() {
        return "Mr.Producer";
    }
    
    public String getSlogan() {
        return "More Tax!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
    
        if (candidates.length == 0) 
            return new Candidate_ec22758();
        
        
        for (Candidate c : candidates)
            if (c.getSlogan().equals("More Tax!"))
                return c;
        
     
        for (Candidate c : candidates)
            if (c.getName().equals("John Cena"))
                return c;
        
       
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        // If array is empty, return instance of friend's class.
        if (votes.length == 0) 
            return new Candidate_ec22758();
        
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
