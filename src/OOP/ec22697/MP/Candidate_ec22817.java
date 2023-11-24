package OOP.ec22697.MP;// File Candidate_ec22817.java
//
// Machine generated stub for Assignment 2

class Candidate_ec22817 extends Candidate {
    
    public String getName() {
        return "Midoriya";
    }
    
    public String getSlogan() {
        return "Plus Ultra!";
    }
    
    public Candidate vote(Candidate[] candidates) {
        
        if (candidates.length == 0)
            return new Candidate_ec22817();
 
        for (Candidate c : candidates){
            if (c.getSlogan().equals("Plus Ultra!")) {
                return c;
            }
        }
        
        for (Candidate c : candidates){
            if (c.getName().equals("Midoriya")) {
                return c;
            }
        }
       
        return candidates[candidates.length-1];
    }
    
    public Candidate selectWinner(Candidate[] votes) {
        
        if (votes.length == 0) 
            return new Candidate_ec22817();

        Candidate currentWinner = votes[0];


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

